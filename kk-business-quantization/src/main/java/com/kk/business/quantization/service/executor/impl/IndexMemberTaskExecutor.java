package com.kk.business.quantization.service.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.dao.mapper.IndexMemberMapper;
import com.kk.business.quantization.model.dto.IndexClassifyListDto;
import com.kk.business.quantization.model.po.tushare.IndexClassifyVo;
import com.kk.business.quantization.model.po.tushare.IndexMemberVo;
import com.kk.business.quantization.model.po.tushare.TushareData;
import com.kk.business.quantization.model.vo.IndexClassifyListVo;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.kk.business.quantization.service.IIndexMemberService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
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
 * 概念基本信息任务执行器
 */
@Service("IndexMemberTaskExecutor")
@Slf4j
public class IndexMemberTaskExecutor implements ITaskExecutor {

    @Resource
    public ITushareDataApi tushareDataApi;
    @Resource
    public IIndexMemberService indexMemberService;
    @Resource
    public IndexMemberMapper indexMemberMapper;
    @Resource
    public IIndexClassifyService indexClassifyService;


    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        IndexMemberVo vo = (IndexMemberVo) JsonUtil.parseObject(params,IndexMemberVo.class);
        List<IndexMember> data = new ArrayList<>();

        if((vo.getIds()==null || vo.getIds().size()<=0) && StringUtils.isBlank(vo.getIndexCode()))
            return ;
        if(vo.getIds() ==null)
        {
            vo.setIds(new ArrayList<>());
        }
        if(StringUtils.isNotBlank(vo.getIndexCode()))
            vo.getIds().add(vo.getIndexCode());
        for (String id : vo.getIds())
        {
            if(StringUtils.isBlank(id))
                continue;
            vo.setIndexCode(id);
            TushareData<IndexMember> res =  tushareDataApi.indexMember(vo);
            data.addAll(res.getData());
        }

        if("cover".equals(vo.getUpdateType()))
        {
            QueryWrapper<IndexMember> query = new QueryWrapper<>();
            query.in("index_code",vo.getIds());
            indexMemberMapper.delete(query);
        }

        //插入db
        indexMemberService.insertIgnoreBatch(data);

    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        IndexMemberVo vo = (IndexMemberVo) JsonUtil.parseObject(policy.getInvokeParams(),IndexMemberVo.class);

        List<CollectionTask> list = new ArrayList<>();
        if((vo.getIds() == null || vo.getIds().size() <= 0 )&& StringUtils.isBlank( vo.getIndexCode()))
        {
            IndexClassifyListVo icvo = new IndexClassifyListVo();
            icvo.setSrc(vo.getSrc());
            int pageSize = 5000,pageIndex =1;

            icvo.setPageIndex(pageIndex);
            icvo.setPageSize(pageSize);
            PageResult<IndexClassifyListDto> cdPageList = indexClassifyService.selectPageList(icvo);
            if(cdPageList !=null && cdPageList.getTotalCount()>0 )
            {
                List<IndexClassifyListDto> cdList = cdPageList.getResult();
                if(cdPageList.getTotalCount()>pageSize) {
                    long sbPage = cdPageList.getTotalCount() / pageSize;
                    if (sbPage * pageSize < cdPageList.getTotalCount()) {
                        sbPage++;
                    }
                    for (pageIndex++; pageIndex <= sbPage; pageIndex++) {
                        icvo.setPageIndex(pageIndex);
                        icvo.setPageSize(pageSize);
                        PageResult<IndexClassifyListDto> tempS = indexClassifyService.selectPageList(icvo);
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
                            .map(IndexClassifyListDto::getIndexCode).collect(Collectors.toList());
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
