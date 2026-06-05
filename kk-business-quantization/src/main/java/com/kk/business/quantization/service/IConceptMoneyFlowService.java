package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念资金流向 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IConceptMoneyFlowService  {

    /**
    * 分批批量插入概念资金流向
    * @param list 数据列表
    * @return
    */
    void insertConceptMoneyFlowBatchSomeColumn(List<ConceptMoneyFlow> list);
    /**
    * 单条插入概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    void insertConceptMoneyFlow(ConceptMoneyFlowAddReqVo vo);
    /**
    * 更新概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    int updateConceptMoneyFlow(ConceptMoneyFlowEditReqVo vo);
    /**
    * 单条查询概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptMoneyFlowResVo selectConceptMoneyFlowById(ConceptMoneyFlowDetailsReqVo vo);
    /**
    * 删除概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteConceptMoneyFlowById(ConceptMoneyFlowDeleteReqVo vo);
    /**
    * 分页获取概念资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptMoneyFlowListResVo>  selectConceptMoneyFlowPageList(ConceptMoneyFlowListReqVo vo);
}

