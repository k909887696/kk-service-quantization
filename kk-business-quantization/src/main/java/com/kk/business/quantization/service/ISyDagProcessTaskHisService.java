package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagProcessTaskHis;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例历史任务 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagProcessTaskHisService  {

    /**
    * 分批批量插入DAG执行实例历史任务
    * @param list 数据列表
    * @return
    */
    void insertSyDagProcessTaskHisBatchSomeColumn(List<SyDagProcessTaskHis> list);
    /**
    * 单条插入DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagProcessTaskHis(SyDagProcessTaskHisAddReqVo vo);
    /**
    * 更新DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagProcessTaskHis(SyDagProcessTaskHisEditReqVo vo);
    /**
    * 单条查询DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagProcessTaskHisResVo selectSyDagProcessTaskHisById(SyDagProcessTaskHisDetailsReqVo vo);
    /**
    * 删除DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagProcessTaskHisById(SyDagProcessTaskHisDeleteReqVo vo);
    /**
    * 分页获取DAG执行实例历史任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagProcessTaskHisListResVo>  selectSyDagProcessTaskHisPageList(SyDagProcessTaskHisListReqVo vo);
}

