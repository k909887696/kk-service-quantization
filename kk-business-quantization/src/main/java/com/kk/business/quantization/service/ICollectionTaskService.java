package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.SearchTaskListVo;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 数据任务 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface ICollectionTaskService extends IMppService<CollectionTask> {

    /**
     * 更新异常信息
     * @param taskId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String taskId,String exMsg);

    /**
     * 更新
     * @param task
     * @return
     */
    int update(CollectionTask task);
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<CollectionTask> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CollectionTask> getCollectionTaskPageResult(SearchTaskListVo vo);

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
