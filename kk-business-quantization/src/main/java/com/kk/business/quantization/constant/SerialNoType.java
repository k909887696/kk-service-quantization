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
     * 消息发送队列
     */
    public static final String INFO_SEND_QUEUE = "INFO";

    /**
     * DAG主表
     */
    public static final String DAG_MAIN = "DM";

    /**
     * DAG执行流
     */
    public static final String DAG_FLOW = "DF";

    /**
     * DAG执行实例主表
     */
    public static final String DAG_PROCESS_MAIN = "DP";

    /**
     * DAG执行实例任务
     */
    public static final String DAG_PROCESS_TASK = "DT";





    /********
     * flow type code-value key map
     */
    public static final Map<String, String> CODE_VALUE_MAP = new HashMap<>();

    static {
        CODE_VALUE_MAP.put(COLLECTION_TASK,"任务"
        );
        CODE_VALUE_MAP.put(COLLECTION_POLICY,"任务策略"
        );
        CODE_VALUE_MAP.put(DAG_MAIN,"DAG主表"
        );
        CODE_VALUE_MAP.put(DAG_FLOW,"DAG执行流"
        );
        CODE_VALUE_MAP.put(DAG_PROCESS_MAIN,"DAG执行实例主表"
        );
        CODE_VALUE_MAP.put(DAG_PROCESS_TASK,"DAG执行实例任务"
        );

    }

}
