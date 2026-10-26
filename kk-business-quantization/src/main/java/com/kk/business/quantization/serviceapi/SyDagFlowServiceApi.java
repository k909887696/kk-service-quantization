package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagFlow;
import com.kk.business.quantization.service.ISyDagFlowService;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * DAG执行流 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagFlowServiceApi   {

    @Resource
    public ISyDagFlowService syDagFlowService;

    /**
    * 分批批量插入DAG执行流
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertSyDagFlowBatchSomeColumn(List<SyDagFlow> list)
    {
        syDagFlowService.insertSyDagFlowBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertSyDagFlow(SyDagFlowAddReqVo vo)
    {
        syDagFlowService.insertSyDagFlow(vo);
    }
    /**
    * 更新DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateSyDagFlow(SyDagFlowEditReqVo vo)
    {
        return syDagFlowService.updateSyDagFlow(vo);
    }
    /**
    * 单条查询DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public SyDagFlowResVo selectSyDagFlowById(SyDagFlowDetailsReqVo vo)
    {
        return syDagFlowService.selectSyDagFlowById(vo);
    }
    /**
    * 删除DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteSyDagFlowById(SyDagFlowDeleteReqVo vo)
    {
        return syDagFlowService.deleteSyDagFlowById(vo);
    }
    /**
    * 分页获取DAG执行流结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<SyDagFlowListResVo>  selectSyDagFlowPageList(SyDagFlowListReqVo vo){
        return syDagFlowService.selectSyDagFlowPageList(vo);
    }



}
