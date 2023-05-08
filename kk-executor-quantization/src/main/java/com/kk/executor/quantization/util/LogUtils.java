package com.kk.executor.quantization.util;

import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志工具类
 */
@Slf4j
public class LogUtils {
    /**
     * 整合xxl-job 与 log4j
     * @param appendLogPattern
     * @param appendLogArguments
     */
    public static void logInfoXxlAnd4j(String appendLogPattern, Object... appendLogArguments)
    {
        XxlJobHelper.log(appendLogPattern
                ,appendLogArguments);
        log.info(appendLogPattern
                ,appendLogArguments);
    }
    public static void logErrorXxlAnd4j(String appendLogPattern, Object... appendLogArguments)
    {
        XxlJobHelper.log(appendLogPattern
                ,appendLogArguments);
        log.error(appendLogPattern
                ,appendLogArguments);
    }
}
