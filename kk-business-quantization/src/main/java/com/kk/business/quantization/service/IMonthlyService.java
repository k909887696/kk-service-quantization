package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Monthly;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.MonthlyListReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyListResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyAddReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyEditReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股月线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IMonthlyService  {

    /**
    * 分批批量插入个股月线行情
    * @param list 数据列表
    * @return
    */
    void insertMonthlyBatchSomeColumn(List<Monthly> list);
    /**
    * 单条插入个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertMonthly(MonthlyAddReqVo vo);
    /**
    * 更新个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateMonthly(MonthlyEditReqVo vo);
    /**
    * 单条查询个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    MonthlyResVo selectMonthlyById(MonthlyDetailsReqVo vo);
    /**
    * 删除个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteMonthlyById(MonthlyDeleteReqVo vo);
    /**
    * 分页获取个股月线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MonthlyListResVo>  selectMonthlyPageList(MonthlyListReqVo vo);
}

