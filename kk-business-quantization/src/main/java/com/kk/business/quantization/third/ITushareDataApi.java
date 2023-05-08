package com.kk.business.quantization.third;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.tushare.*;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:22
 *  对接 tushare 数据接口
 */
public interface ITushareDataApi {

    /**
     * 获取tushare个股行情
     * @param vo
     * @return
     */
    TushareData<Daily> daily(DailyVo vo);
    /**
     * 获取tushare个股资金流向
     * @param vo
     * @return
     */
    TushareData<MoneyFlow> moneyFlow(MoneyFlowVo vo);
    /**
     * 获取tushare个股每日指标
     * @param vo
     * @return
     */
    TushareData<DailyBasic> dailyBasic(DailyBasicVo vo);
    /**
     * 获取tushare个股基本信息
     * @param vo
     * @return
     */
    TushareData<StockBasic> stockBasic(StockBasicVo vo);

    /**
     * 获取交易日历
     * @param vo
     * @return
     */
    TushareData<TradeCal> tradeCal(TradeCalVo vo);

    /**
     * 获取概念信息
     * @param vo
     * @return
     */
    TushareData<Concept> concept(ConceptVo vo);

    /**
     * 获取同花顺概念和行业指数信息
     * @param vo
     * @return
     */
    TushareData<Concept> thsIndex(ConceptVo vo);

    /**
     * 获取概念明细信息
     * @param vo
     * @return
     */
    TushareData<ConceptDetail> conceptDetail(ConceptDetailVo vo);

    /**
     * 获取同花顺概念板块成分
     * @param vo
     * @return
     */
    TushareData<ConceptDetail> thsMember(ConceptDetailVo vo);

    /**
     * 获取申万行业
     * @param vo
     * @return
     */
    TushareData<IndexClassify> indexClassify(IndexClassifyVo vo);

    /**
     * 获取申万行业明细
     * @param vo
     * @return
     */
    TushareData<IndexMember> indexMember(IndexMemberVo vo);

    /**
     * 获取国内人民币货币总量 按月
     * @param vo
     * @return
     */
    TushareData<CnM> cnm(CnmVo vo);
    /**
     * 指数基本信息
     * @param vo
     * @return
     */
    TushareData<IndexBasic> indexBasic(IndexBasicVo vo);

    /**
     * 指数日线行情
     * @param vo
     * @return
     */
    TushareData<IndexDaily> indexDaily(IndexDailyVo vo);
}
