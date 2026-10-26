package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagProcessTask;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例任务 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagProcessTaskService  {

    /**
    * 分批批量插入DAG执行实例任务
    * @param list 数据列表
    * @return
    */
    void insertSyDagProcessTaskBatchSomeColumn(List<SyDagProcessTask> list);
    /**
    * 单条插入DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagProcessTask(SyDagProcessTaskAddReqVo vo);
    /**
    * 更新DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagProcessTask(SyDagProcessTaskEditReqVo vo);
    /**
    * 单条查询DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagProcessTaskResVo selectSyDagProcessTaskById(SyDagProcessTaskDetailsReqVo vo);
    /**
    * 删除DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagProcessTaskById(SyDagProcessTaskDeleteReqVo vo);
    /**
    * 分页获取DAG执行实例任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagProcessTaskListResVo>  selectSyDagProcessTaskPageList(SyDagProcessTaskListReqVo vo);
}

