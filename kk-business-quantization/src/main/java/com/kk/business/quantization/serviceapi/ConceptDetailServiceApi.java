package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.service.IConceptDetailService;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 概念明细 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptDetailServiceApi   {

    @Resource
    public IConceptDetailService conceptDetailService;

    /**
    * 分批批量插入概念明细
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertConceptDetailBatchSomeColumn(List<ConceptDetail> list)
    {
        conceptDetailService.insertConceptDetailBatchSomeColumn(list);
    }
    /**
    * 单条插入概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertConceptDetail(ConceptDetailAddReqVo vo)
    {
        conceptDetailService.insertConceptDetail(vo);
    }
    /**
    * 更新概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateConceptDetail(ConceptDetailEditReqVo vo)
    {
        return conceptDetailService.updateConceptDetail(vo);
    }
    /**
    * 单条查询概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public ConceptDetailResVo selectConceptDetailById(ConceptDetailDetailsReqVo vo)
    {
        return conceptDetailService.selectConceptDetailById(vo);
    }
    /**
    * 删除概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteConceptDetailById(ConceptDetailDeleteReqVo vo)
    {
        return conceptDetailService.deleteConceptDetailById(vo);
    }
    /**
    * 分页获取概念明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<ConceptDetailListResVo>  selectConceptDetailPageList(ConceptDetailListReqVo vo){
        return conceptDetailService.selectConceptDetailPageList(vo);
    }



}
