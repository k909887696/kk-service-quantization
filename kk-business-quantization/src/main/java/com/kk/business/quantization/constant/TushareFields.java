package com.kk.business.quantization.constant;

/**
 * @Author: kk
 * @Date: 2021/12/11 14:39
 */
public enum TushareFields {

    /**
     * 概念股分类
     */
    CONCEPT("concept","code,name,src"),
    /**
     * 同花顺概念和行业指数
     */
    THS_INDEX("ths_index","ts_code,name,count,exchange,list_date,type"),
    /**
     * 同花顺概念板块成分
     */
    THS_MEMBER("ths_member","ts_code,code,weight,name,in_date,out_date"),
    /**
     * 概念股明细
     */
    CONCEPT_DETAIL("concept_detail","id,concept_name,ts_code,name,in_date,out_date"),
    /**
     * 股票列表
     */
    STOCK_BASIC("stock_basic","ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs"),
    /**
     * 资金流向
     */
    MONEYFLOW("moneyflow","ts_code,trade_date,buy_sm_vol,buy_sm_amount,sell_sm_vol,sell_sm_amount,buy_md_vol,buy_md_amount,sell_md_vol,sell_md_amount,buy_lg_vol,buy_lg_amount,sell_lg_vol,sell_lg_amount,buy_elg_vol,buy_elg_amount,sell_elg_vol,sell_elg_amount,net_mf_vol,net_mf_amount"),
    /**
     * 交易日历
     */
    TRADE_CAL("trade_cal","exchange,cal_date,is_open,pretrade_date"),
    /**
     * 申万市场分类
     */
    INDEX_CLASSIFY("index_classify","index_code,industry_name,level,industry_code,src,parent_code,is_pub"),
    /**
     * 申万市场成分
     */
    INDEX_MEMBER("index_member","index_code,index_name,con_code,con_name,in_date,out_date,is_new"),
    /**
     * 指数基本信息
     */
    INDEX_BASIC("index_basic","ts_code,name,fullname,market,publisher,index_type,category,base_date,base_point,list_date,weight_rule,exp_date"),
    /**
     * 指数日线行情
     */
    INDEX_DAILY("index_daily","ts_code,trade_date,open,high,low,close,pre_close,change,pct_chg,vol,amount"),
    /**
     * 国内货币总量
     */
    CN_M("cn_m","month,m0,m0_yoy,m0_mom,m1,m1_yoy,m1_mom,m2,m2_yoy,m2_mom"),
    /**
     * 日线行情
     */
    DAILY("daily","ts_code,trade_date,open,high,low,close,pre_close,change,pct_chg,vol,amount"),
    /**
     * 个股每日指标
     */
    DAILY_BASIC("daily_basic","ts_code,trade_date,close,turnover_rate,turnover_rate_f,volume_ratio,pe,pe_ttm,pb,ps,ps_ttm,dv_ratio,dv_ttm,total_share,float_share,free_share,total_mv,circ_mv");

    public String ID;
    public String VALUE;
    TushareFields(String id,String value){
        ID = id;
        VALUE = value;
    }


}
