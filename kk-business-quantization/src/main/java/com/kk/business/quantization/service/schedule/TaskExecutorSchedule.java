package com.kk.business.quantization.service.schedule;

import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.handler.TaskExecutorHandler;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
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
    public void policySchedule()
    {

        log.info("{}|{}"
                ,"任务策略调度开始执行"
                ,"policySchedule");
        List<CollectionPolicy> list = collectionPolicyService.getPreExecutePolicy(40);

        if(list != null && list.size() >0) {//没有需要执行策略

            for (CollectionPolicy p : list) {
                try {
                    log.info("{}|{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "开始执行"
                            , "policySchedule;" + p.getPolicyId());
                    taskExecutorHandler.handlerPolicy(p);
                    log.info("{}|{}|{}",
                            p.getName() + "(" + p.getPolicyId() + ")"
                            , "执行完成"
                            , "policySchedule;" + p.getPolicyId());
                } catch (Exception e) {
                    log.error("{}|{}|{}|{}",
                            "任务策略调度执行异常：" + p.getName() + "(" + p.getPolicyId() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "policySchedule;" + p.getPolicyId());

                    collectionPolicyService.updateExMsgAndRunCount(p.getPolicyId(), e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                }
            }
        }
        log.info("{}|{}"
                ,"任务策略调度执行完成"
                ,"policySchedule");
    }

    /**
     * 手动执行任务策略
     * @param ids
     * @return
     */
    public String policyScheduleByHand(List<String> ids)
    {

        if(ids ==null || ids.size() <=0) return "ids 为空！";
        StringBuilder success = new StringBuilder("执行成功： "),fail=new StringBuilder("执行失败： "),mission = new StringBuilder("不存在： ");
        log.info("{}|{}"
                ,"任务策略手动开始执行|"+JsonUtil.getJSONString(ids)
                ,"policyScheduleByHand");

        List<CollectionPolicy> list = collectionPolicyService.getPolicyByIds(ids);

        if(list == null || list.size() <=0) return "没有找到对应执行策略";//没有需要执行策略

        log.info("{}|{}"
                ,"任务策略手动开始执行|"+JsonUtil.getJSONString(list)
                ,"policyScheduleByHand");

        for (CollectionPolicy p :list) {
            try {
                ids.remove(p.getPolicyId());
                log.info("{}|{}|{}",
                        p.getName()+"("+p.getPolicyId()+")"
                        ,"（手动）开始执行"
                        ,"policyScheduleByHand;"+p.getPolicyId());
                taskExecutorHandler.handlerPolicy(p);
                log.info("{}|{}|{}",
                        p.getName()+"("+p.getPolicyId()+")"
                        ,"（手动）执行完成"
                        ,"policyScheduleByHand;"+p.getPolicyId());
                success.append(p.getPolicyId()+ ",");
            }catch (Exception e)
            {
                fail.append(String.format("[%s(%s):%s],",p.getName(), p.getPolicyId(),e.getMessage()));
                log.error("{}|{}|{}|{}",
                        "任务策略手动执行异常："+p.getName()+"("+p.getPolicyId()+")"
                        ,e.getMessage(), ExceptionUtils.getStackTrace(e)
                        ,"policyScheduleByHand;"+p.getPolicyId());

                collectionPolicyService.updateExMsgAndRunCount(p.getPolicyId(),e.getMessage()+"|"+ExceptionUtils.getStackTrace(e));
            }
        }
        log.info("{}|{}"
                ,"任务策略手动执行完成"
                ,"policyScheduleByHand");
        mission.append(String.join(",",ids));
        return String.format(" %s | %s | %s",success.toString(),fail.toString(),mission.toString()).toString();
    }

    /**
     * 执行需要处理的任务
     */
    public void taskSchedule()
    {

        log.info("{}|{}"
                ,"任务调度开始执行"
                ,"taskSchedule");
        List<CollectionTask> list = collectionTaskService.getPreExecuteTask(20);

        if(list != null && list.size() >0) {//没有需要执行任务

            for (CollectionTask p : list) {
                try {
                    log.info("{}|{}|{}",
                            p.getName() + "(" + p.getTaskId() + ")"
                            , "开始执行"
                            , "taskSchedule;" + p.getTaskId());
                    taskExecutorHandler.handlerTask(p);
                    log.info("{}|{}|{}",
                            p.getName() + "(" + p.getTaskId() + ")"
                            , "执行完成"
                            , "taskSchedule;" + p.getTaskId());
                } catch (Exception e) {
                    log.error("{}|{}|{}|{}",
                            "任务调度执行异常：" + p.getName() + "(" + p.getTaskId() + ")"
                            , e.getMessage(), ExceptionUtils.getStackTrace(e)
                            , "taskSchedule;" + p.getTaskId());
                    p.setExMsg(e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
                    p.setRunCount(p.getRunCount() +1);
                    p.setRunTime(new Date());
                    p.setPreRunTime(DateUtil.addDate(p.getPreRunTime(), Calendar.MINUTE,p.getRunCount()*(p.getRunCount()-1)));
                    collectionTaskService.update(p);
                }
            }
        }
        log.info("{}|{}"
                ,"任务调度执行完成"
                ,"taskSchedule");
    }

    /**
     * 手动执行任务
     * @param ids
     * @return
     */
    public String taskScheduleByHand(List<String> ids)
    {

        if(ids ==null || ids.size() <=0) return "ids 为空！";
        StringBuilder success = new StringBuilder("执行成功： "),fail=new StringBuilder("执行失败： "),mission = new StringBuilder("不存在： ");
        log.info("{}|{}"
                ,"任务手动开始执行|"+JsonUtil.getJSONString(ids)
                ,"taskScheduleByHand");

        List<CollectionTask> list = collectionTaskService.getTaskByIds(ids);

        if(list == null || list.size() <=0) return "没有找到对应执行任务";//没有需要执行策略

        log.info("{}|{}"
                ,"任务手动开始执行|"+JsonUtil.getJSONString(list)
                ,"taskScheduleByHand");

        for (CollectionTask p :list) {
            try {
                ids.remove(p.getTaskId());
                log.info("{}|{}|{}",
                        p.getName()+"("+p.getTaskId()+")"
                        ,"（手动）开始执行"
                        ,"taskScheduleByHand;"+p.getTaskId());
                taskExecutorHandler.handlerTask(p);
                log.info("{}|{}|{}",
                        p.getName()+"("+p.getTaskId()+")"
                        ,"（手动）执行完成"
                        ,"taskScheduleByHand;"+p.getTaskId());
                success.append(p.getTaskId()+ ",");
            }catch (Exception e)
            {
                fail.append(String.format("[%s(%s):%s],",p.getName(), p.getTaskId(),e.getMessage()));
                log.error("{}|{}|{}|{}",
                        "任务手动执行异常："+p.getName()+"("+p.getTaskId()+")"
                        ,e.getMessage(), ExceptionUtils.getStackTrace(e)
                        ,"taskScheduleByHand;"+p.getTaskId());

                collectionTaskService.updateExMsgAndRunCount(p.getTaskId(),e.getMessage()+"|"+ExceptionUtils.getStackTrace(e));
            }
        }
        log.info("{}|{}"
                ,"任务手动执行完成"
                ,"taskScheduleByHand");
        mission.append(String.join(",",ids));
        return String.format(" %s | %s | %s",success.toString(),fail.toString(),mission.toString()).toString();
    }
}
