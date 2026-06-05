package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Daily;
import java.util.List;

import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyResVo;
import com.kk.business.quantization.model.vobase.req.DailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IDailyService  {

    /**
    * 分批批量插入个股日线行情
    * @param list 数据列表
    * @return
    */
    void insertDailyBatchSomeColumn(List<Daily> list);
    /**
    * 单条插入个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertDaily(DailyAddReqVo vo);
    /**
    * 更新个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateDaily(DailyEditReqVo vo);
    /**
    * 单条查询个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    DailyResVo selectDailyById(DailyDetailsReqVo vo);
    /**
    * 删除个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteDailyById(DailyDeleteReqVo vo);
    /**
    * 分页获取个股日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<DailyListResVo>  selectDailyPageList(DailyListReqVo vo);

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyKdjDto>  getPageResultEx(SearchDailyVo vo);
    /**
     * 获取区间涨幅最好的概念
     * @param vo
     * @return
     */
    PageResult<DailyLeaderDto> selectStockLeader(SearchDailyLeaderVo vo);
}

