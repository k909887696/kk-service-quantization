package com.kk.business.quantization.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 调度类型周期
 */
public class BaseDataItemMapType {

    /**
     *  调度周期类型
     */
    public static final String INVOKECYCLETYPE = "invokecycletype";





    /********
     * flow type code-value key map
     */
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put(INVOKECYCLETYPE,"调度周期类型"
        );

    }

}

