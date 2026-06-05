package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptDetail;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念明细 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IConceptDetailService  {

    /**
    * 分批批量插入概念明细
    * @param list 数据列表
    * @return
    */
    void insertConceptDetailBatchSomeColumn(List<ConceptDetail> list);
    /**
    * 单条插入概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    void insertConceptDetail(ConceptDetailAddReqVo vo);
    /**
    * 更新概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    int updateConceptDetail(ConceptDetailEditReqVo vo);
    /**
    * 单条查询概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptDetailResVo selectConceptDetailById(ConceptDetailDetailsReqVo vo);
    /**
    * 删除概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteConceptDetailById(ConceptDetailDeleteReqVo vo);
    /**
    * 分页获取概念明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptDetailListResVo>  selectConceptDetailPageList(ConceptDetailListReqVo vo);
}

