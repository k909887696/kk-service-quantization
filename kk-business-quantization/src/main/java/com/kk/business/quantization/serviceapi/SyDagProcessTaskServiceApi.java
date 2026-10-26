package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessTask;
import com.kk.business.quantization.service.ISyDagProcessTaskService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * DAG执行实例任务 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskServiceApi   {

    @Resource
    public ISyDagProcessTaskService syDagProcessTaskService;

    /**
    * 分批批量插入DAG执行实例任务
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertSyDagProcessTaskBatchSomeColumn(List<SyDagProcessTask> list)
    {
        syDagProcessTaskService.insertSyDagProcessTaskBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertSyDagProcessTask(SyDagProcessTaskAddReqVo vo)
    {
        syDagProcessTaskService.insertSyDagProcessTask(vo);
    }
    /**
    * 更新DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateSyDagProcessTask(SyDagProcessTaskEditReqVo vo)
    {
        return syDagProcessTaskService.updateSyDagProcessTask(vo);
    }
    /**
    * 单条查询DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public SyDagProcessTaskResVo selectSyDagProcessTaskById(SyDagProcessTaskDetailsReqVo vo)
    {
        return syDagProcessTaskService.selectSyDagProcessTaskById(vo);
    }
    /**
    * 删除DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteSyDagProcessTaskById(SyDagProcessTaskDeleteReqVo vo)
    {
        return syDagProcessTaskService.deleteSyDagProcessTaskById(vo);
    }
    /**
    * 分页获取DAG执行实例任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<SyDagProcessTaskListResVo>  selectSyDagProcessTaskPageList(SyDagProcessTaskListReqVo vo){
        return syDagProcessTaskService.selectSyDagProcessTaskPageList(vo);
    }



}
