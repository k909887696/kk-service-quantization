package com.kk.business.quantization.service.executor;

import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.common.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务执行器
 */
public interface ITaskExecutor {

    /**
     * 执行任务
     * @param params
     */
    void executeTask(String params);

    /**
     * 拆分任务
     * @param policy
     * @return
     */
    default List<CollectionTask> splitTask(CollectionPolicy policy)
    {

        return null;
    };

    default CollectionTask generateTask(CollectionPolicy policy)
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
     * 任务参数处理
     * @param params
     * @return
     */
    default String paramsHandler(String params,CollectionPolicy policy){
        return params;
    };


    /**
     * 下载数据
     * @param obj
     * @return
     */
    default Object downloadData(Object obj){
        return null;
    };

    /**
     * 保存数据
     * @param data
     */
    default  void saveDataToDB(Object data){

    };



}
