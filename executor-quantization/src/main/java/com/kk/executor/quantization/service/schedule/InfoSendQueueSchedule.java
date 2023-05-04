package com.kk.executor.quantization.service.schedule;

import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.IInfoSendQueueService;
import com.kk.business.quantization.service.executor.InfoSendQueueHandler;
import com.kk.business.quantization.service.executor.TaskExecutorHandler;
import com.kk.common.utils.JsonUtil;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/17 16:10
 * 任务执行器处理调度
 */
@Slf4j
@Component
public class InfoSendQueueSchedule {


    @Resource
    public IInfoSendQueueService infoSendQueueService;
    @Resource
    public InfoSendQueueHandler infoSendQueueHandler;

    /**
     * 整合xxl-job 与 log4j
     * @param appendLogPattern
     * @param appendLogArguments
     */
    public  void log4xxlAndLog4j(String appendLogPattern, Object... appendLogArguments)
    {
        XxlJobHelper.log(appendLogPattern
                ,appendLogArguments);
        log.info(appendLogPattern
                ,appendLogArguments);
    }

    /**
     * 执行需要处理的消息
     */
    public void infoSendQueueSchedule()
    {

        log4xxlAndLog4j("{}|{}"
                ,"消息队列调度开始执行"
                ,"infoSendQueueSchedule");
        List<InfoSendQueue> list = infoSendQueueService.getPreSendList(20);

        if(list != null && list.size() > 0) {//没有需要执行任务

            for (InfoSendQueue p : list) {
                try {
                    log4xxlAndLog4j("{}|{}|{}",
                            p.getInfoId() + "(" + p.getInfoType() + ")"
                            , "开始执行"
                            , "infoSendQueueSchedule;" + p.getInfoId());
                    infoSendQueueHandler.handlerInfoSendQueue(p);
                    log4xxlAndLog4j("{}|{}|{}",
                            p.getInfoId() + "(" + p.getInfoType() + ")"
                            , "执行完成"
                            , "infoSendQueueSchedule;" + p.getInfoId());
                } catch (Exception e) {
                    XxlJobHelper.log("{}|{}|{}|{}",
                            "消息队列调度执行异常：" + p.getInfoId() + "(" + p.getInfoType() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "infoSendQueueSchedule;" + p.getInfoId());
                    log.error("{}|{}|{}|{}",
                            "消息队列调度执行异常：" + p.getInfoId() + "(" + p.getInfoType() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "infoSendQueueSchedule;" + p.getInfoId());

                    infoSendQueueService.updateExMsgAndRunCount(p.getInfoId(), e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                }
            }
        }
        log4xxlAndLog4j("{}|{}"
                ,"消息队列调度执行完成"
                ,"infoSendQueueSchedule");
    }


}
