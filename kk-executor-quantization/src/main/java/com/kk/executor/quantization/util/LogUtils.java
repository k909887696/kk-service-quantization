package com.kk.executor.quantization.util;

import com.kk.common.base.aspect.AspectLogAdvice;
import com.kk.common.utils.LogUtil;
import com.xxl.job.core.context.XxlJobHelper;


import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * 日志工具类
 */

public class LogUtils {

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(LogUtils.class);
    /**
     * 整合xxl-job 与 log4j
     * @param appendLogPattern
     * @param appendLogArguments
     */
    public static void logInfoXxlAnd4j(String logkey,String appendLogPattern, Object... appendLogArguments)
    {
       // XxlJobHelper.log(appendLogPattern ,appendLogArguments);
        LogUtil.info(logger,logkey,appendLogPattern,appendLogArguments);
    }
    public static void logErrorXxlAnd4j(String logkey,String appendLogPattern, Object... appendLogArguments)
    {
        //XxlJobHelper.log(appendLogPattern,appendLogArguments);
        LogUtil.error(logger,logkey,appendLogPattern,appendLogArguments);
    }
}
