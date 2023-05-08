package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.dao.mapper.ConceptDetailMapper;
import com.kk.business.quantization.model.dfcf.DfcfData;
import com.kk.business.quantization.model.tushare.ConceptDetailVo;
import com.kk.business.quantization.model.tushare.ConceptVo;
import com.kk.business.quantization.model.tushare.TushareData;
import com.kk.business.quantization.service.IConceptDetailService;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.IDfcfDataApi;
import com.kk.business.quantization.third.ITushareDataApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 东方财富概念明细基本信息任务执行器
 */
@Service("DfcfConceptDetailTaskExecutor")
@Slf4j
public class DfcfConceptDetailTaskExecutor implements ITaskExecutor {

    @Resource
    public IDfcfDataApi dfcfDataApi;
    @Resource
    public IConceptDetailService conceptDetailService;
    @Resource
    public ConceptDetailMapper conceptDetailMapper;
    @Resource
    public IConceptService conceptService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        ConceptDetailVo vo = (ConceptDetailVo) JsonUtil.parseObject(params,ConceptDetailVo.class);
        List<ConceptDetail> data = new ArrayList<>();

        if((vo.getIds()==null || vo.getIds().size()<=0) && StringUtils.isBlank(vo.getId()))
            return ;
        if(vo.getIds() ==null)
        {
            vo.setIds(new ArrayList<>());
        }
        if(StringUtils.isNotBlank(vo.getId()))
            vo.getIds().add(vo.getId());
        for (String id : vo.getIds())
        {
            if(StringUtils.isBlank(id))
                continue;
            vo.setId(id);
            DfcfData<ConceptDetail> res =  dfcfDataApi.conceptDetail(vo);
            data.addAll(res.getData());
        }

        if("cover".equals(vo.getUpdateType()))
        {
            LambdaQueryWrapper<ConceptDetail> query = new LambdaQueryWrapper<>();
            query.in(ConceptDetail::getConceptId,vo.getIds());
            conceptDetailMapper.delete(query);
        }

        //插入db
        conceptDetailService.insertIgnoreBatch(data);
        conceptDetailMapper.updateTsCodeByConceptIds(vo.getIds());
        conceptDetailMapper.updateConceptNameByConceptIds(vo.getIds());
    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        ConceptDetailVo vo = (ConceptDetailVo) JsonUtil.parseObject(policy.getInvokeParams(),ConceptDetailVo.class);

        List<CollectionTask> list = new ArrayList<>();
        if((vo.getIds() == null || vo.getIds().size() <= 0 )&& StringUtils.isBlank( vo.getId()))
        {
            ConceptVo icvo = new ConceptVo();
            icvo.setSrc("90");
            int pageSize = 5000,pageIndex =1;

            icvo.setPageIndex(pageIndex);
            icvo.setPageSize(pageSize);
            PageResult<Concept> cdPageList = conceptService.getConceptPageResult(icvo);
            if(cdPageList !=null && cdPageList.getTotalCount()>0 )
            {
                List<Concept> cdList = cdPageList.getResult();
                if(cdPageList.getTotalCount()>pageSize) {
                    long sbPage = cdPageList.getTotalCount() / pageSize;
                    if (sbPage * pageSize < cdPageList.getTotalCount()) {
                        sbPage++;
                    }
                    for (pageIndex++; pageIndex <= sbPage; pageIndex++) {
                        icvo.setPageIndex(pageIndex);
                        icvo.setPageSize(pageSize);
                        PageResult<Concept> tempS = conceptService.getConceptPageResult(icvo);
                        if(tempS!=null && tempS.getTotalCount()>0)
                        {
                            cdList.addAll(tempS.getResult());
                        }
                    }
                }
                int splitPage = cdList.size() / vo.getPageSize();
                if(splitPage * vo.getPageSize() < cdList.size())
                {
                    splitPage ++;
                }
                for (int i =0;i<splitPage;i++)
                {
                    List<String> tsCodeList = cdList.stream()
                            .skip(i*vo.getPageSize())
                            .limit(vo.getPageSize())
                            .map(Concept::getCode).collect(Collectors.toList());
                    vo.setIds(tsCodeList);
                    vo.setPageIndex(i+1);
                    CollectionTask task = generateTask(policy);
                    task.setInvokeParams(this.paramsHandler(JsonUtil.getJSONString(vo),policy));
                    list.add(task);
                }
            }
        }else {
            CollectionTask task = generateTask(policy);
            task.setInvokeParams(this.paramsHandler(task.getInvokeParams(),policy));
            list.add(task);
        }

        return list;
    }

}
