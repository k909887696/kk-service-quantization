package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.CollectionTask;
import java.util.List;

import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据任务 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ICollectionTaskService  {

    /**
    * 分批批量插入系统设置-数据任务
    * @param list 数据列表
    * @return
    */
    void insertCollectionTaskBatchSomeColumn(List<CollectionTask> list);
    /**
    * 单条插入系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    void insertCollectionTask(CollectionTaskAddReqVo vo);
    /**
    * 更新系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    int updateCollectionTask(CollectionTaskEditReqVo vo);
    /**
    * 单条查询系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    CollectionTaskResVo selectCollectionTaskById(CollectionTaskDetailsReqVo vo);
    /**
    * 删除系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteCollectionTaskById(CollectionTaskDeleteReqVo vo);
    /**
    * 分页获取系统设置-数据任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CollectionTaskListResVo>  selectCollectionTaskPageList(CollectionTaskListReqVo vo);

    /**
     * 获取需要处理的任务
     * @param vo
     * @return 任务集合
     */
    PageResult<CollectionTask>  getPreExecuteTask(SelectPreExecuteTaskVo vo);
    /**
     * 根据ids查询任务列表
     * @param ids
     * @return
     */
    List<CollectionTask> getTaskByIds(List<String> ids);

    /**
     * 重新执行一次任务
     * @param taskId 任务编号
     * @return
     */
    void retryExecuteTask(String taskId);
}

