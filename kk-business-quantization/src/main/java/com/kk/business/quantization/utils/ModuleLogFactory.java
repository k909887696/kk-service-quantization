package com.kk.business.quantization.utils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.*;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Map;

public class ModuleLogFactory {

    private static final String rootLogDir = "logs" ;

    private static final String logPatternLayout="%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight{%-5level} [%t] [%X{traceId}] %highlight{%c{1.}.%M(%L)}: %msg%n";
    private static final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    private static final Configuration config = ctx.getConfiguration();
    private static final Map<String, Logger> logModuleMap = new HashedMap();

    private ModuleLogFactory() {
    }
    public static void start(String logModuleName) {
        String logFileName = logModuleName;
        Layout layout = PatternLayout.createLayout(logPatternLayout,null,
                config, null, null, true, false, null, null);
        //单个日志文件大小
        TimeBasedTriggeringPolicy tbtp = TimeBasedTriggeringPolicy.createPolicy(null, null);
        TriggeringPolicy tp = SizeBasedTriggeringPolicy.createPolicy("50MB");// 大小分割
        CompositeTriggeringPolicy policyComposite = CompositeTriggeringPolicy.createPolicy(tbtp, tp);
        DefaultRolloverStrategy strategy = DefaultRolloverStrategy.createStrategy(
                "100000", "1", null, null, null, false, config);
       RollingFileAppender appender = RollingFileAppender.createAppender( rootLogDir + File.separator + logModuleName+ File.separator + logModuleName+".log"
               ,rootLogDir + File.separator + logModuleName+ File.separator  + logModuleName+"-%d{yyyy-MM-dd-HH}-" + "%i.log"
       ,"true",logModuleName,"true","",null,policyComposite,strategy,layout,null,"","","",config);

       appender.start();
        config.addAppender(appender);
        AppenderRef ref = AppenderRef.createAppenderRef( logModuleName, null, null);
        AppenderRef[] refs = new AppenderRef[]{ref};
        LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.ALL,  logModuleName,
                "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger(logModuleName, loggerConfig);
        ctx.updateLoggers();
    }
    public static void stop(String logModuleName) {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.getAppender( logModuleName).stop();
        config.getLoggerConfig(logModuleName).removeAppender( logModuleName);
        config.removeLogger( logModuleName);
        ctx.updateLoggers();
    }
    /**
     * 获取Logger
     *
     * 如果不想使用slf4j,那这里改成直接返回Log4j的Logger即可
     * @param logModuleName
     * @return
     */
    public static Logger createLogger(String  logModuleName ){
       // LoggerConfig tempConfig =  config.getLoggers().get(logModuleName);
        if(!logModuleMap.keySet().contains(logModuleName))
        {
            start(logModuleName);
            Logger logger= LoggerFactory.getLogger(logModuleName);
            logModuleMap.put(logModuleName,logger);
        }
        return logModuleMap.get(logModuleName);
    }
}
