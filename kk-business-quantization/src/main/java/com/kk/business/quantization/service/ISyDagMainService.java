package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagMain;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG主表 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagMainService  {

    /**
    * 分批批量插入DAG主表
    * @param list 数据列表
    * @return
    */
    void insertSyDagMainBatchSomeColumn(List<SyDagMain> list);
    /**
    * 单条插入DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagMain(SyDagMainAddReqVo vo);
    /**
    * 更新DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagMain(SyDagMainEditReqVo vo);
    /**
    * 单条查询DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagMainResVo selectSyDagMainById(SyDagMainDetailsReqVo vo);
    /**
    * 删除DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagMainById(SyDagMainDeleteReqVo vo);
    /**
    * 分页获取DAG主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagMainListResVo>  selectSyDagMainPageList(SyDagMainListReqVo vo);
}

