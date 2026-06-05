package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.DailyTime;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.DailyTimeListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeListResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股分钟行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IDailyTimeService  {

    /**
    * 分批批量插入个股分钟行情
    * @param list 数据列表
    * @return
    */
    void insertDailyTimeBatchSomeColumn(List<DailyTime> list);
    /**
    * 单条插入个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertDailyTime(DailyTimeAddReqVo vo);
    /**
    * 更新个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateDailyTime(DailyTimeEditReqVo vo);
    /**
    * 单条查询个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    DailyTimeResVo selectDailyTimeById(DailyTimeDetailsReqVo vo);
    /**
    * 删除个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteDailyTimeById(DailyTimeDeleteReqVo vo);
    /**
    * 分页获取个股分钟行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<DailyTimeListResVo>  selectDailyTimePageList(DailyTimeListReqVo vo);
}

