package com.kk.executor.quantization.service.schedule;

import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.IInfoSendQueueService;
import com.kk.business.quantization.service.handler.InfoSendQueueHandler;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.LogUtil;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
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


    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(InfoSendQueueSchedule.class);    /**

     /**
     * 执行需要处理的消息
     */
    public void infoSendQueueSchedule()
    {

        String gLogkey = "infoSendQueueSchedule;";
        LogUtil.logErrorXxlAnd4j(logger,gLogkey,"{}|{}"
                ,"消息队列调度开始执行"
                ,"infoSendQueueSchedule");
        List<InfoSendQueue> list = infoSendQueueService.getPreSendList(20);

        if(list != null && list.size() > 0) {//没有需要执行任务

            for (InfoSendQueue p : list) {
                String logkey = gLogkey + p.getInfoId();
                try {
                    LogUtil.logInfoXxlAnd4j(logger,logkey,"{}|{}|{}",
                            p.getInfoId() + "(" + p.getInfoType() + ")"
                            , "开始执行"
                            , "infoSendQueueSchedule;" + p.getInfoId());
                    infoSendQueueHandler.handlerInfoSendQueue(p);
                    LogUtil.logInfoXxlAnd4j(logger,logkey,"{}|{}|{}",
                            p.getInfoId() + "(" + p.getInfoType() + ")"
                            , "执行完成"
                            , "infoSendQueueSchedule;" + p.getInfoId());
                } catch (Exception e) {
                    LogUtil.logErrorXxlAnd4j(logger,logkey,"{}|{}|{}|{}",
                            "消息队列调度执行异常：" + p.getInfoId() + "(" + p.getInfoType() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "infoSendQueueSchedule;" + p.getInfoId());
                    infoSendQueueService.updateExMsgAndRunCount(p.getInfoId(), e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                }
            }
        }
        LogUtil.logInfoXxlAnd4j(logger,"{}|{}"
                ,"消息队列调度执行完成"
                ,"infoSendQueueSchedule");
    }


}
