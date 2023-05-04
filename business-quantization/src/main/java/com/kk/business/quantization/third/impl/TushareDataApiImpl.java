package com.kk.business.quantization.third.impl;

import com.kk.business.quantization.config.ThirdDataConfig;
import com.kk.business.quantization.constant.TushareFields;
import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.tushare.*;
import com.kk.business.quantization.third.ITushareDataApi;

import com.kk.business.quantization.utils.ThridDataUtils;

import com.kk.common.exception.BusinessException;
import com.kk.common.utils.MapperUtils;
import com.kk.common.utils.httpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:26
 */
@Service
@Slf4j
public class TushareDataApiImpl implements ITushareDataApi {

    @Resource
    public ThirdDataConfig thirdDataConfig;
    @Resource
    public MapperUtils mapperUtils;

    private void validTushareData(TushareBaseRes resObj)
    {
        if(resObj == null)
            throw new BusinessException("返回数据为null");
        if(resObj!=null && !"0".equals(resObj.getCode()))
        {
            throw new BusinessException(resObj.getMsg());
        }
    }

    /**
     * 获取tushare个股行情
     * @param vo
     * @return
     */
    @Override
    public TushareData<Daily> daily(DailyVo vo) {

        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.DAILY.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.DAILY.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<Daily> list = mapperUtils.map(resMap,Daily.class);
        TushareData<Daily> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取tushare个股每日指标
     * @param vo
     * @return
     */
    public TushareData<DailyBasic> dailyBasic(DailyBasicVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.DAILY_BASIC.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.DAILY_BASIC.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<DailyBasic> list = mapperUtils.map(resMap,DailyBasic.class);
        TushareData<DailyBasic> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取tushare个股资金流向
     * @param vo
     * @return
     */
    public TushareData<MoneyFlow> moneyFlow(MoneyFlowVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.MONEYFLOW.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.MONEYFLOW.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<MoneyFlow> list = mapperUtils.map(resMap,MoneyFlow.class);
        TushareData<MoneyFlow> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取tushare个股基本信息
     * @param vo
     * @return
     */
    @Override
    public TushareData<StockBasic> stockBasic(StockBasicVo vo) {

        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.STOCK_BASIC.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.STOCK_BASIC.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<StockBasic> list = mapperUtils.map(resMap,StockBasic.class);
        TushareData<StockBasic> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取交易日历
     * @param vo
     * @return
     */
    @Override
    public TushareData<TradeCal> tradeCal(TradeCalVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.TRADE_CAL.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.TRADE_CAL.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<TradeCal> list = mapperUtils.map(resMap,TradeCal.class);
        TushareData<TradeCal> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取概念信息
     * @param vo
     * @return
     */
    @Override
    public TushareData<Concept> concept(ConceptVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.CONCEPT.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.CONCEPT.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<Concept> list = mapperUtils.map(resMap,Concept.class);
        TushareData<Concept> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取同花顺概念和行业指数信息
     * @param vo
     * @return
     */
    public TushareData<Concept> thsIndex(ConceptVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.THS_INDEX.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.THS_INDEX.VALUE);
        Map<String,String> fieldsMap = new HashMap<>();
        fieldsMap.put("ts_code","code");
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,fieldsMap);
        List<Concept> list = mapperUtils.map(resMap,Concept.class);
        TushareData<Concept> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取概念明细信息
     * @param vo
     * @return
     */
    public TushareData<ConceptDetail> conceptDetail(ConceptDetailVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.CONCEPT_DETAIL.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.CONCEPT_DETAIL.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        Map<String,String> fieldsMap = new HashMap<>();
        fieldsMap.put("id","concept_id");
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,fieldsMap);
        List<ConceptDetail> list = mapperUtils.map(resMap,ConceptDetail.class);
        TushareData<ConceptDetail> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }
    /**
     * 获取同花顺概念板块成分
     * @param vo
     * @return
     */
    public TushareData<ConceptDetail> thsMember(ConceptDetailVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.THS_MEMBER.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.THS_MEMBER.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        Map<String,String> fieldsMap = new HashMap<>();
        fieldsMap.put("ts_code","concept_id");
        fieldsMap.put("code","ts_code");
        fieldsMap.put("name","concept_name");
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,fieldsMap);
        List<ConceptDetail> list = mapperUtils.map(resMap,ConceptDetail.class);
        TushareData<ConceptDetail> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取申万行业
     * @param vo
     * @return
     */
    public TushareData<IndexClassify> indexClassify(IndexClassifyVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.INDEX_CLASSIFY.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.INDEX_CLASSIFY.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);

        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<IndexClassify> list = mapperUtils.map(resMap,IndexClassify.class);
        TushareData<IndexClassify> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取申万行业明细
     * @param vo
     * @return
     */
    public TushareData<IndexMember> indexMember(IndexMemberVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.INDEX_MEMBER.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.INDEX_MEMBER.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);

        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<IndexMember> list = mapperUtils.map(resMap,IndexMember.class);
        TushareData<IndexMember> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 获取国内人民币货币总量 按月
     * @param vo
     * @return
     */
    @Override
    public TushareData<CnM> cnm(CnmVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.CN_M.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.CN_M.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<CnM> list = mapperUtils.map(resMap,CnM.class);
        TushareData<CnM> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }

    /**
     * 指数基本信息
     * @param vo
     * @return
     */
    @Override
    public TushareData<IndexBasic> indexBasic(IndexBasicVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.INDEX_BASIC.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.INDEX_BASIC.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<IndexBasic> list = mapperUtils.map(resMap,IndexBasic.class);
        TushareData<IndexBasic> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }
    /**
     * 指数日线行情
     * @param vo
     * @return
     */
    @Override
    public TushareData<IndexDaily> indexDaily(IndexDailyVo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("api_name",TushareFields.INDEX_DAILY.ID);
        params.put("token",thirdDataConfig.getTushareToken());
        params.put("params",ThridDataUtils.tushareParamsHandler(vo));
        params.put("fields",TushareFields.INDEX_DAILY.VALUE);
        TushareBaseRes resObj = httpUtil.httpRestRequest(params,thirdDataConfig.getTushareApiUrl(),TushareBaseRes.class);
        validTushareData(resObj);
        List<Map<String,Object>> resMap = ThridDataUtils.tushareResultHandler(resObj,true,null);
        List<IndexDaily> list = mapperUtils.map(resMap,IndexDaily.class);
        TushareData<IndexDaily> res = new TushareData<>();
        res.setData(list);
        res.setHasMore(resObj.getHas_more());
        return res;
    }
}
