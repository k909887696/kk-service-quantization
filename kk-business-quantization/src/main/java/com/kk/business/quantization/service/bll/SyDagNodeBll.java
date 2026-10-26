package com.kk.business.quantization.service.bll;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagNode;
import com.kk.business.quantization.service.ISyDagNodeService;
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
 * DAG执行节点 Bll业务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagNodeBll   {

    @Resource
    public ISyDagNodeService syDagNodeService;

    /**
    * 分批批量插入DAG执行节点
    * @param list 数据列表
    * @return
    */
    public void insertSyDagNodeBatchSomeColumn(List<SyDagNode> list)
    {
        syDagNodeService.insertSyDagNodeBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    public void insertSyDagNode(SyDagNodeAddReqVo vo)
    {
        syDagNodeService.insertSyDagNode(vo);
    }
    /**
    * 更新DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    public int updateSyDagNode(SyDagNodeEditReqVo vo)
    {
        return syDagNodeService.updateSyDagNode(vo);
    }
    /**
    * 单条查询DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    public SyDagNodeResVo selectSyDagNodeById(SyDagNodeDetailsReqVo vo)
    {
        return syDagNodeService.selectSyDagNodeById(vo);
    }
    /**
    * 删除DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteSyDagNodeById(SyDagNodeDeleteReqVo vo)
    {
        return syDagNodeService.deleteSyDagNodeById(vo);
    }
    /**
    * 分页获取DAG执行节点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyDagNodeListResVo>  selectSyDagNodePageList(SyDagNodeListReqVo vo){
        return syDagNodeService.selectSyDagNodePageList(vo);
    }



}

