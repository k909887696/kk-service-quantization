package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 概念资金流向 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptMoneyFlowServiceApi   {

    @Resource
    public IConceptMoneyFlowService conceptMoneyFlowService;

    /**
    * 分批批量插入概念资金流向
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertConceptMoneyFlowBatchSomeColumn(List<ConceptMoneyFlow> list)
    {
        conceptMoneyFlowService.insertConceptMoneyFlowBatchSomeColumn(list);
    }
    /**
    * 单条插入概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertConceptMoneyFlow(ConceptMoneyFlowAddReqVo vo)
    {
        conceptMoneyFlowService.insertConceptMoneyFlow(vo);
    }
    /**
    * 更新概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateConceptMoneyFlow(ConceptMoneyFlowEditReqVo vo)
    {
        return conceptMoneyFlowService.updateConceptMoneyFlow(vo);
    }
    /**
    * 单条查询概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public ConceptMoneyFlowResVo selectConceptMoneyFlowById(ConceptMoneyFlowDetailsReqVo vo)
    {
        return conceptMoneyFlowService.selectConceptMoneyFlowById(vo);
    }
    /**
    * 删除概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteConceptMoneyFlowById(ConceptMoneyFlowDeleteReqVo vo)
    {
        return conceptMoneyFlowService.deleteConceptMoneyFlowById(vo);
    }
    /**
    * 分页获取概念资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<ConceptMoneyFlowListResVo>  selectConceptMoneyFlowPageList(ConceptMoneyFlowListReqVo vo){
        return conceptMoneyFlowService.selectConceptMoneyFlowPageList(vo);
    }



}
