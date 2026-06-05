package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.service.IConceptDailyService;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 概念日线行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptDailyServiceApi   {

    @Resource
    public IConceptDailyService conceptDailyService;

    /**
    * 分批批量插入概念日线行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertConceptDailyBatchSomeColumn(List<ConceptDaily> list)
    {
        conceptDailyService.insertConceptDailyBatchSomeColumn(list);
    }
    /**
    * 单条插入概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertConceptDaily(ConceptDailyAddReqVo vo)
    {
        conceptDailyService.insertConceptDaily(vo);
    }
    /**
    * 更新概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateConceptDaily(ConceptDailyEditReqVo vo)
    {
        return conceptDailyService.updateConceptDaily(vo);
    }
    /**
    * 单条查询概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public ConceptDailyResVo selectConceptDailyById(ConceptDailyDetailsReqVo vo)
    {
        return conceptDailyService.selectConceptDailyById(vo);
    }
    /**
    * 删除概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteConceptDailyById(ConceptDailyDeleteReqVo vo)
    {
        return conceptDailyService.deleteConceptDailyById(vo);
    }
    /**
    * 分页获取概念日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<ConceptDailyListResVo>  selectConceptDailyPageList(ConceptDailyListReqVo vo){
        return conceptDailyService.selectConceptDailyPageList(vo);
    }



}
