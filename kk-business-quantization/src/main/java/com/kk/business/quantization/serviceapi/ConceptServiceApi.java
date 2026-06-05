package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 概念分类 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptServiceApi   {

    @Resource
    public IConceptService conceptService;

    /**
    * 分批批量插入概念分类
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertConceptBatchSomeColumn(List<Concept> list)
    {
        conceptService.insertConceptBatchSomeColumn(list);
    }
    /**
    * 单条插入概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertConcept(ConceptAddReqVo vo)
    {
        conceptService.insertConcept(vo);
    }
    /**
    * 更新概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateConcept(ConceptEditReqVo vo)
    {
        return conceptService.updateConcept(vo);
    }
    /**
    * 单条查询概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public ConceptResVo selectConceptById(ConceptDetailsReqVo vo)
    {
        return conceptService.selectConceptById(vo);
    }
    /**
    * 删除概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteConceptById(ConceptDeleteReqVo vo)
    {
        return conceptService.deleteConceptById(vo);
    }
    /**
    * 分页获取概念分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<ConceptListResVo>  selectConceptPageList(ConceptListReqVo vo){
        return conceptService.selectConceptPageList(vo);
    }



}
