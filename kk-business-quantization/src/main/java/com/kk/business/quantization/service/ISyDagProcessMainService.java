package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagProcessMain;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例主表 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagProcessMainService  {

    /**
    * 分批批量插入DAG执行实例主表
    * @param list 数据列表
    * @return
    */
    void insertSyDagProcessMainBatchSomeColumn(List<SyDagProcessMain> list);
    /**
    * 单条插入DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagProcessMain(SyDagProcessMainAddReqVo vo);
    /**
    * 更新DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagProcessMain(SyDagProcessMainEditReqVo vo);
    /**
    * 单条查询DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagProcessMainResVo selectSyDagProcessMainById(SyDagProcessMainDetailsReqVo vo);
    /**
    * 删除DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagProcessMainById(SyDagProcessMainDeleteReqVo vo);
    /**
    * 分页获取DAG执行实例主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagProcessMainListResVo>  selectSyDagProcessMainPageList(SyDagProcessMainListReqVo vo);
}

