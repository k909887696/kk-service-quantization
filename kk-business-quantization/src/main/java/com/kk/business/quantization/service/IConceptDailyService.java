package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptDaily;
import java.util.List;

import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IConceptDailyService  {

    /**
    * 分批批量插入概念日线行情
    * @param list 数据列表
    * @return
    */
    void insertConceptDailyBatchSomeColumn(List<ConceptDaily> list);
    /**
    * 单条插入概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertConceptDaily(ConceptDailyAddReqVo vo);
    /**
    * 更新概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateConceptDaily(ConceptDailyEditReqVo vo);
    /**
    * 单条查询概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptDailyResVo selectConceptDailyById(ConceptDailyDetailsReqVo vo);
    /**
    * 删除概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteConceptDailyById(ConceptDailyDeleteReqVo vo);
    /**
    * 分页获取概念日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptDailyListResVo>  selectConceptDailyPageList(ConceptDailyListReqVo vo);

    /**
     * 查询区间涨幅最大概念
     * @param vo
     * @return
     */
    PageResult<DailyLeaderDto> selectConceptLeaderListByRange(SearchDailyLeaderVo vo);
}

