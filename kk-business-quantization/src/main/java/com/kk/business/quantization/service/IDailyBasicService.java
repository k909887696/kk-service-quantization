package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.DailyBasic;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.DailyBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicListResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股每日指标 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IDailyBasicService  {

    /**
    * 分批批量插入个股每日指标
    * @param list 数据列表
    * @return
    */
    void insertDailyBasicBatchSomeColumn(List<DailyBasic> list);
    /**
    * 单条插入个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    void insertDailyBasic(DailyBasicAddReqVo vo);
    /**
    * 更新个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    int updateDailyBasic(DailyBasicEditReqVo vo);
    /**
    * 单条查询个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    DailyBasicResVo selectDailyBasicById(DailyBasicDetailsReqVo vo);
    /**
    * 删除个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteDailyBasicById(DailyBasicDeleteReqVo vo);
    /**
    * 分页获取个股每日指标结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<DailyBasicListResVo>  selectDailyBasicPageList(DailyBasicListReqVo vo);
}

