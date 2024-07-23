package com.kk.business.quantization.service.executor.impl;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.dao.mapper.ConceptDailyMapper;
import com.kk.business.quantization.model.po.dfcf.DfcfConceptDailyVo;
import com.kk.business.quantization.model.po.dfcf.DfcfData;
import com.kk.business.quantization.model.po.tushare.ConceptVo;
import com.kk.business.quantization.service.IConceptDailyService;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.third.IDfcfDataApi;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 东方财富概念日线行情任务执行器
 */
@Service("DfcfConceptDailyTaskExecutor")
@Slf4j
public class DfcfConceptDailyTaskExecutor implements ITaskExecutor {

    @Resource
    public IDfcfDataApi dfcfDataApi;
    @Resource
    public IConceptDailyService conceptDailyService;
    @Resource
    public IConceptService conceptService;
    @Resource
    public ITradeCalService tradeCalService;

    /**
     * 下载数据
     * @param params
     */
    @Override
    public void executeTask(String params) {
        if(StringUtils.isBlank(params)) return ;
        DfcfConceptDailyVo vo = (DfcfConceptDailyVo) JsonUtil.parseObject(params,DfcfConceptDailyVo.class);
        List<ConceptDaily> data = new ArrayList<>();

        if((vo.getIds()==null || vo.getIds().size()<=0) && StringUtils.isBlank(vo.getConceptId()))
            return ;
        if(vo.getIds() ==null)
        {
            vo.setIds(new ArrayList<>());
        }
        if(StringUtils.isNotBlank(vo.getConceptId()))
            vo.getIds().add(vo.getConceptId());
        for (String id : vo.getIds())
        {
            if(StringUtils.isBlank(id))
                continue;
            vo.setConceptId(id);
            DfcfData<ConceptDaily> res =  dfcfDataApi.conceptDaily(vo);
            if(res!=null) {
                data.addAll(res.getData());
            }
        }

        //插入db
        conceptDailyService.insertIgnoreBatch(data);
    }

    @Override
    public List<CollectionTask> splitTask(CollectionPolicy policy) {
        DfcfConceptDailyVo vo = (DfcfConceptDailyVo) JsonUtil.parseObject(policy.getInvokeParams(),DfcfConceptDailyVo.class);

        List<CollectionTask> list = new ArrayList<>();
        if((vo.getIds() == null || vo.getIds().size() <= 0 )&& StringUtils.isBlank( vo.getConceptId()))
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

    @Override
    public String paramsHandler(String params, CollectionPolicy policy) {
        if(StringUtils.isBlank(params)) return params;
        DfcfConceptDailyVo vo = (DfcfConceptDailyVo) JsonUtil.parseObject(params,DfcfConceptDailyVo.class);
        String nowDate = DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W);
        TradeCal nowOpenTradeCal = tradeCalService.getRecentlyOpenByDay(nowDate,1,"desc");
        String nowPreOpenDate = nowOpenTradeCal==null ?nowDate:nowOpenTradeCal.getPretradeDate();
        if("today".equals(vo.getTradeDate()))
        {
            vo.setTradeDate(nowPreOpenDate);
            vo.setStartDate(nowPreOpenDate);
            vo.setEndDate(nowDate);
        }
        if("today".equals(vo.getStartDate()))
        {
            vo.setStartDate(nowPreOpenDate);
            vo.setEndDate(nowDate);
        }
        return JsonUtil.getJSONString(vo);
    }
}
