package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Weekly;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyAddReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyEditReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股周线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IWeeklyService  {

    /**
    * 分批批量插入个股周线行情
    * @param list 数据列表
    * @return
    */
    void insertWeeklyBatchSomeColumn(List<Weekly> list);
    /**
    * 单条插入个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertWeekly(WeeklyAddReqVo vo);
    /**
    * 更新个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateWeekly(WeeklyEditReqVo vo);
    /**
    * 单条查询个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    WeeklyResVo selectWeeklyById(WeeklyDetailsReqVo vo);
    /**
    * 删除个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteWeeklyById(WeeklyDeleteReqVo vo);
    /**
    * 分页获取个股周线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<WeeklyListResVo>  selectWeeklyPageList(WeeklyListReqVo vo);
}

