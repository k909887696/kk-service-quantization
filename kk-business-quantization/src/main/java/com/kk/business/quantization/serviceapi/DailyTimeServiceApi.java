package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.business.quantization.service.IDailyTimeService;
import com.kk.business.quantization.model.vobase.req.DailyTimeListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeListResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股分钟行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyTimeServiceApi   {

    @Resource
    public IDailyTimeService dailyTimeService;

    /**
    * 分批批量插入个股分钟行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertDailyTimeBatchSomeColumn(List<DailyTime> list)
    {
        dailyTimeService.insertDailyTimeBatchSomeColumn(list);
    }
    /**
    * 单条插入个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertDailyTime(DailyTimeAddReqVo vo)
    {
        dailyTimeService.insertDailyTime(vo);
    }
    /**
    * 更新个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateDailyTime(DailyTimeEditReqVo vo)
    {
        return dailyTimeService.updateDailyTime(vo);
    }
    /**
    * 单条查询个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public DailyTimeResVo selectDailyTimeById(DailyTimeDetailsReqVo vo)
    {
        return dailyTimeService.selectDailyTimeById(vo);
    }
    /**
    * 删除个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteDailyTimeById(DailyTimeDeleteReqVo vo)
    {
        return dailyTimeService.deleteDailyTimeById(vo);
    }
    /**
    * 分页获取个股分钟行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<DailyTimeListResVo>  selectDailyTimePageList(DailyTimeListReqVo vo){
        return dailyTimeService.selectDailyTimePageList(vo);
    }



}
