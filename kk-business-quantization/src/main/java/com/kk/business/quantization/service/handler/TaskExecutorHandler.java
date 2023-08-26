package com.kk.business.quantization.service.handler;

import com.kk.business.quantization.constant.InvokeCycleType;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.dao.mapper.CollectionPolicyMapper;
import com.kk.business.quantization.dao.mapper.CollectionTaskHistoryMapper;
import com.kk.business.quantization.dao.mapper.CollectionTaskMapper;
import com.kk.business.quantization.dao.mapper.InvokeTypeMapper;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


/**
 * @Author: kk
 * @Date: 2021/12/16 16:32
 * 任务执行处理器
 */
@Slf4j
@Service
public class TaskExecutorHandler {


    @Resource
    public InvokeTypeMapper invokeTypeMapper;

    @Resource
    public CollectionPolicyMapper collectionPolicyMapper;
    @Resource
    public ICollectionTaskService collectionTaskService;
    @Resource
    public CollectionTaskMapper collectionTaskMapper;
    @Resource
    public CollectionTaskHistoryMapper collectionTaskHistoryMapper;

    @Resource
    public MapperUtils mapperUtils;
    @Resource
    private ApplicationContext applicationContext;

    /**
     * 任务策略处理
     * @param policy
     */
    public void handlerPolicy(CollectionPolicy policy)
    {
        if(StringUtils.isBlank(policy.getInvokeCode()))
            throw new BusinessException("任务策略任务执行器编码为空！");
        InvokeType invokeType =invokeTypeMapper.selectById(policy.getInvokeCode());

        if(invokeType==null)
            throw new BusinessException("任务执行器不存在，请配置！");
        ITaskExecutor taskExecutor =null;
        // 分流不同渠道
        try {

                taskExecutor = (ITaskExecutor) applicationContext
                        .getBean(policy.getInvokeCode());

        } catch (Exception e) {
            log.info("{}|{}|{}","任务执行器初始化异常",
                    e.getMessage()+"|"+ExceptionUtils.getStackTrace(e)
                    ,"handlerPolicy;"+policy.getInvokeCode()+":"+policy.getPolicyId());
            throw new BusinessException("任务执行器初始化异常:"+e.getMessage());
        }

        List<CollectionTask> taskList = taskExecutor.splitTask(policy);

        if(taskList == null )//执行器没有重写任务拆分
        {
            taskList = new ArrayList<>();
            CollectionTask task = generateTask(policy);
            task.setInvokeParams(taskExecutor.paramsHandler(task.getInvokeParams(),policy));
            taskList.add(task);
        }

        //保存任务
        collectionTaskService.insertIgnoreBatch(taskList);

        //计算更新下次执行时间 且清空异常信息 与 执行错误次数
        collectionPolicyMapper.updatePreRunTime(policy.getPolicyId(),generateNextRuntime(policy));

    }

    /**
     * 计算任务策略下次执行时间
     * @param policy
     * @return
     */
    public Date generateNextRuntime(CollectionPolicy policy)
    {
        Date d = policy.getPreRunTime();
        if( d == null)
        {
            d = new Date();
        }
        int cycleTime = Integer.parseInt( policy.getInvokeCycleTime());
        switch (policy.getInvokeCycle())
        {
            case InvokeCycleType.MIN:
                d = DateUtil.addDate(d, Calendar.MINUTE,cycleTime);
                break;
            case InvokeCycleType.HOUR:
                d = DateUtil.addDate(d, Calendar.HOUR,cycleTime);
                break;
            case InvokeCycleType.DAY:
                d = DateUtil.addDate(d, Calendar.DATE,cycleTime);
                break;
            case InvokeCycleType.WEEK:
                d = DateUtil.addDate(d, Calendar.DATE,cycleTime * 7);
                break;
            case InvokeCycleType.MONTH:
                d = DateUtil.addDate(d, Calendar.MONTH,cycleTime);
                break;
            case InvokeCycleType.YEAR:
                d = DateUtil.addDate(d, Calendar.YEAR,cycleTime);
                break;
            default:
                d = DateUtil.addDate(d, Calendar.YEAR,cycleTime * 100);
                break;
        }

        return d;
    }

    /**
     * 根据策略生成任务
     * @param policy
     * @return
     */
    public CollectionTask generateTask(CollectionPolicy policy)
    {
        Date d = new Date();
        CollectionTask task = new CollectionTask();

        task.setInvokeCode(policy.getInvokeCode());
        task.setInvokeMethod(policy.getInvokeMethod());
        task.setInvokeParams(policy.getInvokeParams());

        task.setPolicyId(policy.getPolicyId());
        task.setPreRunTime(d);
        task.setName(policy.getName()+"-"+ DateUtil.date2String(d,DateUtil.PATTERN_STANDARD12W));
        task.setRunCount(0);
        
        task.setCreateId("system");
        task.setCreateName("system");
        task.setCreateTime(d);

        return task;

    }


    /**
     * 任务处理
     * @param task
     */
    public void handlerTask(CollectionTask task)
    {

        collectionTaskMapper.deleteById(task.getTaskId());//删除任务

        task.setRunTime(new Date());//记录执行时间
        if(StringUtils.isBlank(task.getInvokeCode()))
            throw new BusinessException("任务执行器编码为空！");
        InvokeType invokeType =invokeTypeMapper.selectById(task.getInvokeCode());

        if(invokeType==null)
            throw new BusinessException("任务执行器不存在，请配置！");
        ITaskExecutor taskExecutor =null;
        // 分流不同渠道
        try {

                taskExecutor = (ITaskExecutor) applicationContext
                        .getBean(task.getInvokeCode());

        } catch (Exception e) {
            log.info("{}|{}|{}","任务执行器初始化异常",
                    e.getMessage()+"|"+ExceptionUtils.getStackTrace(e)
                    ,"handlerPolicy;"+task.getInvokeCode()+":"+task.getTaskId());
            throw new BusinessException("任务执行器初始化异常:"+e.getMessage());
        }
        //真正执行任务
        taskExecutor.executeTask(task.getInvokeParams());
        //记录完成时间
        task.setFinishTime(new Date());

        //迁移到历史表
        collectionTaskHistoryMapper.insert(mapperUtils.map(task, CollectionTaskHistory.class));
    }
}
