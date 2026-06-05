package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Concept;
import java.util.List;

import com.kk.business.quantization.model.po.tushare.ConceptVo;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念分类 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IConceptService  {

    /**
    * 分批批量插入概念分类
    * @param list 数据列表
    * @return
    */
    void insertConceptBatchSomeColumn(List<Concept> list);
    /**
    * 单条插入概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    void insertConcept(ConceptAddReqVo vo);
    /**
    * 更新概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    int updateConcept(ConceptEditReqVo vo);
    /**
    * 单条查询概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptResVo selectConceptById(ConceptDetailsReqVo vo);
    /**
    * 删除概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteConceptById(ConceptDeleteReqVo vo);
    /**
    * 分页获取概念分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptListResVo>  selectConceptPageList(ConceptListReqVo vo);

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<Concept> getConceptPageResult(ConceptVo vo);
}

