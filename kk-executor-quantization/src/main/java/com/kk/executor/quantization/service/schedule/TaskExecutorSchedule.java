package com.kk.executor.quantization.service.schedule;

import com.kk.business.quantization.constant.CollectionHandType;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.JobParamVo;
import com.kk.business.quantization.model.vo.PreExecutePolicyVo;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.handler.TaskExecutorHandler;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.executor.quantization.util.LogUtils;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/17 16:10
 * 任务执行器处理调度
 */
@Slf4j
@Component
public class TaskExecutorSchedule {

    @Resource
    public ICollectionPolicyService collectionPolicyService;
    @Resource
    public ICollectionTaskService collectionTaskService;
    @Resource
    public TaskExecutorHandler taskExecutorHandler;

    /**
     * 执行需要处理的任务策略
     */
    public void policySchedule( )
    {


        LogUtils.logInfoXxlAnd4j("{}|{}"
                ,"任务策略调度开始执行"
                ,"policySchedule");
        SelectPreExecutePolicyVo vo = new SelectPreExecutePolicyVo();
        JobParamVo jobParamVo = new JobParamVo();
        vo.setPageIndex(1);
        vo.setPageSize(40);
        if(!StringUtils.isBlank(XxlJobHelper.getJobParam()))
        {
            jobParamVo = ((JobParamVo)JsonUtil.parseObject(XxlJobHelper.getJobParam(), JobParamVo.class));
        }
        vo.setChannel(jobParamVo.getChannel());
        List<CollectionPolicy> list = collectionPolicyService.getPreExecutePolicy(vo);

        if(list != null && list.size() > 0) {//没有需要执行策略

            for (CollectionPolicy p : list) {
                try {
                    LogUtils.logInfoXxlAnd4j("{}|{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "开始执行"
                            , "policySchedule;" + p.getPolicyId());
                    taskExecutorHandler.handlerPolicy(p, CollectionHandType.BySchedule);
                    LogUtils.logInfoXxlAnd4j("{}|{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "执行完成"
                            , "policySchedule;" + p.getPolicyId());
                } catch (Exception e) {
                    LogUtils.logErrorXxlAnd4j("{}|{}|{}|{}",
                            "任务策略调度执行异常：" + p.getName() + "(" + p.getPolicyId() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "policySchedule;" + p.getPolicyId());

                    collectionPolicyService.updateExMsgAndRunCount(p.getPolicyId(), e.getMessage() );
                }
            }
        }
        LogUtils.logInfoXxlAnd4j("{}|{}"
                ,"任务策略调度执行完成"
                ,"policySchedule");
    }


    /**
     * 执行需要处理的任务
     */
    public void taskSchedule()
    {

        LogUtils.logInfoXxlAnd4j("{}|{}"
                ,"任务调度开始执行"
                ,"taskSchedule");
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
        while(list != null && list.getResult()!= null && list.getResult().size() > 0) {
            LogUtils.logInfoXxlAnd4j("{}|{}"
                    ,"待执行任务数量",list.getResult().size()
                    ,"taskSchedule");
            if (list != null && list.getResult() != null && list.getResult().size() > 0) {//没有需要执行任务

                for (CollectionTask p : list.getResult()) {
                    try {
                        LogUtils.logInfoXxlAnd4j("{}|{}|{}",
                                p.getName() + "(" + p.getTaskId() + ")"
                                , "开始执行"
                                , "taskSchedule;" + p.getTaskId());
                        taskExecutorHandler.handlerTask(p);
                        LogUtils.logInfoXxlAnd4j("{}|{}|{}",
                                p.getName() + "(" + p.getTaskId() + ")"
                                , "执行完成"
                                , "taskSchedule;" + p.getTaskId());
                    } catch (Exception e) {
                        LogUtils.logErrorXxlAnd4j("{}|{}|{}|{}",
                                "任务调度执行异常：" + p.getName() + "(" + p.getTaskId() + ")"
                                , e.getMessage(), ExceptionUtils.getStackTrace(e)
                                , "taskSchedule;" + p.getTaskId());

                        p.setExMsg(e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                        p.setRunCount(p.getRunCount() + 1);
                        p.setRunTime(new Date());
                        p.setPreRunTime(DateUtil.addDate(p.getPreRunTime(), Calendar.MINUTE, p.getRunCount() * (p.getRunCount() - 1)));
                        collectionTaskService.update(p);
                    }
                }
                list = collectionTaskService.getPreExecuteTask(vo);
            }else {
                break;
            }
        }
        LogUtils.logInfoXxlAnd4j("{}|{}"
                ,"任务调度执行完成"
                ,"taskSchedule");
    }


}
