package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyDagNode;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SyDagNodeListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行节点 服务类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface ISyDagNodeService  {

    /**
    * 分批批量插入DAG执行节点
    * @param list 数据列表
    * @return
    */
    void insertSyDagNodeBatchSomeColumn(List<SyDagNode> list);
    /**
    * 单条插入DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyDagNode(SyDagNodeAddReqVo vo);
    /**
    * 更新DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyDagNode(SyDagNodeEditReqVo vo);
    /**
    * 单条查询DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    SyDagNodeResVo selectSyDagNodeById(SyDagNodeDetailsReqVo vo);
    /**
    * 删除DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyDagNodeById(SyDagNodeDeleteReqVo vo);
    /**
    * 分页获取DAG执行节点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyDagNodeListResVo>  selectSyDagNodePageList(SyDagNodeListReqVo vo);
}

