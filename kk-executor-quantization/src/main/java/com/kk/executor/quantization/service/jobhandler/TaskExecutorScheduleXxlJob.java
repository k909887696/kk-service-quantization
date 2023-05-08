package com.kk.executor.quantization.service.jobhandler;

import com.kk.executor.quantization.service.schedule.TaskExecutorSchedule;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author kk 2021-12-23 21:52:51
 */
@Component
public class TaskExecutorScheduleXxlJob {
    private static Logger logger = LoggerFactory.getLogger(TaskExecutorScheduleXxlJob.class);
    @Resource
    public TaskExecutorSchedule taskExecutorSchedule;
    /**
     * 任务策略调度
     */
    @XxlJob(value = "policyJobHandler")
    public void policyJobHandler() throws Exception {
        String param = XxlJobHelper.getJobParam();

        taskExecutorSchedule.policySchedule();

        XxlJobHelper.handleSuccess(XxlJobHelper.getJobId()+"-success");

    }


    /**
     * 任务调度
     * @throws Exception
     */
    @XxlJob(value = "taskJobHandler")
    public void taskJobHandler() throws Exception {
        String param = XxlJobHelper.getJobParam();

        taskExecutorSchedule.taskSchedule();

        XxlJobHelper.handleSuccess(XxlJobHelper.getJobId()+"-success");

    }
    public void init(){
        XxlJobHelper.log("policyJobHandler-init");
    }
    public void destroy(){
        XxlJobHelper.log("policyJobHandler-destory");
    }


}
