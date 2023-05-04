package com.kk.business.quantization.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 调度类型周期
 */
public class InvokeCycleType  {

    /**
     *  分钟
     */
    public static final String MIN = "min";
    /**
     * 小时
     */
    public static final String HOUR = "hour";
    /**
     * 天
     */
    public static final String DAY = "day";
    /**
     * 周
     */
    public static final String WEEK = "week";
    /**
     * 月
     */
    public static final String MONTH = "month";
    /**
     * 年
     */
    public static final String YEAR = "year";




    /********
     * flow type code-value key map
     */
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put(MIN,"分钟"
        );
        CODE_VALUE_MAP.put(HOUR,"小时"
        );

        CODE_VALUE_MAP.put(DAY,"天"
        );
        CODE_VALUE_MAP.put(WEEK,"周"
        );
        CODE_VALUE_MAP.put(MONTH,"月"
        );
        CODE_VALUE_MAP.put(YEAR,"年"
        );

    }

}

