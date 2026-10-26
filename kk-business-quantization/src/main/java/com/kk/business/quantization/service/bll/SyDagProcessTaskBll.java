package com.kk.business.quantization.service.bll;


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
/**
 * <p>
 * DAG执行实例任务 Bll业务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskBll   {

    @Resource
    public ISyDagProcessTaskService syDagProcessTaskService;

    /**
    * 分批批量插入DAG执行实例任务
    * @param list 数据列表
    * @return
    */
    public void insertSyDagProcessTaskBatchSomeColumn(List<SyDagProcessTask> list)
    {
        syDagProcessTaskService.insertSyDagProcessTaskBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    public void insertSyDagProcessTask(SyDagProcessTaskAddReqVo vo)
    {
        syDagProcessTaskService.insertSyDagProcessTask(vo);
    }
    /**
    * 更新DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    public int updateSyDagProcessTask(SyDagProcessTaskEditReqVo vo)
    {
        return syDagProcessTaskService.updateSyDagProcessTask(vo);
    }
    /**
    * 单条查询DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    public SyDagProcessTaskResVo selectSyDagProcessTaskById(SyDagProcessTaskDetailsReqVo vo)
    {
        return syDagProcessTaskService.selectSyDagProcessTaskById(vo);
    }
    /**
    * 删除DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteSyDagProcessTaskById(SyDagProcessTaskDeleteReqVo vo)
    {
        return syDagProcessTaskService.deleteSyDagProcessTaskById(vo);
    }
    /**
    * 分页获取DAG执行实例任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyDagProcessTaskListResVo>  selectSyDagProcessTaskPageList(SyDagProcessTaskListReqVo vo){
        return syDagProcessTaskService.selectSyDagProcessTaskPageList(vo);
    }



}

