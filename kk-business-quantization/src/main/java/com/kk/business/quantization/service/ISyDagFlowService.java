package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagFlow;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行流 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagFlowService  {

    /**
    * 分批批量插入DAG执行流
    * @param list 数据列表
    * @return
    */
    void insertSyDagFlowBatchSomeColumn(List<SyDagFlow> list);
    /**
    * 单条插入DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagFlow(SyDagFlowAddReqVo vo);
    /**
    * 更新DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagFlow(SyDagFlowEditReqVo vo);
    /**
    * 单条查询DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagFlowResVo selectSyDagFlowById(SyDagFlowDetailsReqVo vo);
    /**
    * 删除DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagFlowById(SyDagFlowDeleteReqVo vo);
    /**
    * 分页获取DAG执行流结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagFlowListResVo>  selectSyDagFlowPageList(SyDagFlowListReqVo vo);
    /**
    * 根据dagId查询DAG执行流列表
    * @param dagId DAG主表ID
    * @return DAG执行流列表
    */
    List<SyDagFlowListResVo> selectSyDagFlowListByDagId(String dagId);
    /**
    * 根据dagId删除DAG执行流
    * @param dagId DAG主表ID
    * @return 删除条数
    */
    int deleteSyDagFlowByDagId(String dagId);
}

