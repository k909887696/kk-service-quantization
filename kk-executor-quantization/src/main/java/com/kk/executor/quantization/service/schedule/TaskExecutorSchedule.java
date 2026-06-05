package com.kk.executor.quantization.service.schedule;

import com.kk.business.quantization.constant.CollectionHandType;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.JobParamVo;
import com.kk.business.quantization.model.vo.PreExecutePolicyVo;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.handler.TaskExecutorHandler;

import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.LogUtil;
import com.kk.executor.quantization.util.LogUtils;
import com.xxl.job.core.context.XxlJobHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/17 16:10
 * 任务执行器处理调度
 */

@Component
public class TaskExecutorSchedule {

    @Resource
    public ICollectionPolicyService collectionPolicyService;
    @Resource
    public ICollectionTaskService collectionTaskService;
    @Resource
    public TaskExecutorHandler taskExecutorHandler;

    public int MaxRunTaskCount=500;
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(TaskExecutorSchedule.class);    /**
     * 执行需要处理的任务策略
     */
    public void policySchedule( )
    {

        String gLogkey = "policySchedule;";
        LogUtils.logInfoXxlAnd4j("{}"
                ,"任务策略调度开始执行");
        SelectPreExecutePolicyVo vo = new SelectPreExecutePolicyVo();
        JobParamVo jobParamVo = new JobParamVo();
        vo.setPageIndex(1);
        vo.setPageSize(40);
        if(!StringUtils.isBlank(XxlJobHelper.getJobParam()))
        {
            jobParamVo = ((JobParamVo) JsonUtil.parseObject(XxlJobHelper.getJobParam(), JobParamVo.class));
        }
        vo.setChannel(jobParamVo.getChannel());
        List<CollectionPolicy> list = collectionPolicyService.getPreExecutePolicy(vo);

        if(list != null && list.size() > 0) {//没有需要执行策略

            for (CollectionPolicy p : list) {
                String logkey = gLogkey + p.getPolicyId();
                try {
                    LogUtil.logInfoXxlAnd4j(logger,logkey,"{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "开始执行" );
                    taskExecutorHandler.handlerPolicy(p, CollectionHandType.BySchedule);
                    LogUtil.logInfoXxlAnd4j(logger,logkey,"{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "执行完成");
                } catch (Exception e) {
                    LogUtil.logErrorXxlAnd4j(logger,logkey,"{}|{}|{}",
                            "任务策略调度执行异常：" + p.getName() + "(" + p.getPolicyId() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e));

                    collectionPolicyService.updateExMsgAndRunCount(p.getPolicyId(), e.getMessage() );
                }
            }
        }
        LogUtil.logInfoXxlAnd4j(logger,gLogkey,"{}"
                ,"任务策略调度执行完成");
    }


    /**
     * 执行需要处理的任务
     */
    public void taskSchedule()
    {
        String gLogkey = "taskSchedule;";
        LogUtil.logInfoXxlAnd4j(logger,gLogkey,"{}"
                ,"任务调度开始执行");
        SelectPreExecuteTaskVo vo = new SelectPreExecuteTaskVo();
        vo.setPageIndex(1);
        vo.setPageSize(50);
        JobParamVo jobParamVo = new JobParamVo();
        if(!StringUtils.isBlank(XxlJobHelper.getJobParam()))
        {
            jobParamVo = ((JobParamVo)JsonUtil.parseObject(XxlJobHelper.getJobParam(), JobParamVo.class));
        }
        vo.setChannel(jobParamVo.getChannel());
        PageResult<CollectionTask> list = collectionTaskService.getPreExecuteTask(vo);
        int runedCount=0;
        while(list != null && list.getResult()!= null && list.getResult().size() > 0) {
            String logkey = gLogkey + runedCount;
            LogUtil.logInfoXxlAnd4j(logger,logkey,"{}|{}"
                    ,"待执行任务数量",list.getResult().size());
            if (list != null && list.getResult() != null && list.getResult().size() > 0) {//没有需要执行任务

                for (CollectionTask p : list.getResult()) {
                    String itemLogkey = gLogkey + p.getTaskId();
                    try {

                        LogUtil.logInfoXxlAnd4j(logger,itemLogkey,"{}|{}",
                                p.getName() + "(" + p.getTaskId() + ")"
                                , "开始执行");
                        taskExecutorHandler.handlerTask(p);
                        LogUtil.logInfoXxlAnd4j(logger,itemLogkey,"{}|{}",
                                p.getName() + "(" + p.getTaskId() + ")"
                                , "执行完成");
                        runedCount++;
                    } catch (Exception e) {
                        LogUtil.logErrorXxlAnd4j(logger,itemLogkey,"{}|{}|{}",
                                "任务调度执行异常：" + p.getName() + "(" + p.getTaskId() + ")"
                                , e.getMessage(), ExceptionUtils.getStackTrace(e));

                        p.setExMsg(e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                        p.setRunCount(p.getRunCount() + 1);
                        p.setRunTime(new Date());
                        p.setPreRunTime(DateUtil.addDate(p.getPreRunTime(), Calendar.MINUTE, p.getRunCount() * (p.getRunCount() - 1)));
                        CollectionTaskEditReqVo collectionTaskEditReqVo = new CollectionTaskEditReqVo();
                        BeanUtil.copyProperties(p, collectionTaskEditReqVo);
                        collectionTaskService.updateCollectionTask(collectionTaskEditReqVo);
                    }
                }
                if(runedCount>=MaxRunTaskCount)
                {
                    break;
                }
                list = collectionTaskService.getPreExecuteTask(vo);
            }else {
                break;
            }
        }
        LogUtil.logInfoXxlAnd4j(logger,gLogkey,"{}"
                ,"任务调度执行完成");
    }


}
