package com.kk.business.quantization.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 14:59
 */
public class DfcfFieldsMap {

    /**
     *  东方财富 概念
     */
    public static final String DFCF_CONCEPT = "dfcf_concept";
    /**
     * 东方财富 概念明细
     */
    public static final String DFCF_CONCEPT_DETAIL = "dfcf_concept_detail";
    /**
     * 东方财富 概念日线行情
     */
    public static final String DFCF_CONCEPT_DAILY = "dfcf_concept_daily";
    /**
     * 东方财富 概念资金流向
     */
    public static final String DFCF_CONCEPT_MONEY_FLOW = "dfcf_concept_money_flow";
    /**
     * 东方财富 日线行情
     */
    public static final String DFCF_DAILY = "dfcf_daily";




    /********
     * flow type code-value key map
     */
    public static final Map<String, HashMap<String,String>> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put(DFCF_CONCEPT,new HashMap<String,String>(){{
                    put("f12","code");
                    put("f14","name");
                    put("f13","src");
                }}
        );
        CODE_VALUE_MAP.put(DFCF_CONCEPT_DETAIL,new HashMap<String,String>(){{
                    put("f12","symbol");

                    put("f14","name");
                }}
        );
        CODE_VALUE_MAP.put(DFCF_CONCEPT_DAILY,new LinkedHashMap<String,String>(){{
                    put("tradeDate","");
                    put("open","");
                    put("close","");
                    put("high","");
                    put("low","");
                    put("vol","");
                    put("amount","");
                   /* put("f4","change");
                    put("f3","pctChg");
                    put("f5","vol");
                    put("f6","amount");*/
                }}
        );
        CODE_VALUE_MAP.put(DFCF_CONCEPT_MONEY_FLOW,new LinkedHashMap<String,String>(){{
                    put("tradeDate","");
                    put("null","");
                    put("buySmAmount","");
                    put("buyMdAmount","");
                    put("buyLgAmount","");
                    put("buyElgAmount","");

                }}
        );
        CODE_VALUE_MAP.put(DFCF_DAILY,new HashMap<String,String>(){{
                    put("f12","ts_code");
                    put("f14","name");
                }}
        );

    }

}
