package com.kk.business.quantization.constant;

import java.util.HashMap;
import java.util.Map;

public class SerialNoType {

    /**
     *  任务
     */
    public static final String COLLECTION_TASK = "TK";
    /**
     * 任务策略
     */
    public static final String COLLECTION_POLICY = "P";

    /**
     *  消息发送队列
     */
    public static final String INFO_SEND_QUEUE = "INFO";





    /********
     * flow type code-value key map
     */
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put(COLLECTION_TASK,"任务"
        );
        CODE_VALUE_MAP.put(COLLECTION_POLICY,"任务策略"
        );



    }

}

