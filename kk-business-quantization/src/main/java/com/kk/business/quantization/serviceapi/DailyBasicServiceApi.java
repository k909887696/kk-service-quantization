package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.service.IDailyBasicService;
import com.kk.business.quantization.model.vobase.req.DailyBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicListResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股每日指标 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyBasicServiceApi   {

    @Resource
    public IDailyBasicService dailyBasicService;

    /**
    * 分批批量插入个股每日指标
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertDailyBasicBatchSomeColumn(List<DailyBasic> list)
    {
        dailyBasicService.insertDailyBasicBatchSomeColumn(list);
    }
    /**
    * 单条插入个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertDailyBasic(DailyBasicAddReqVo vo)
    {
        dailyBasicService.insertDailyBasic(vo);
    }
    /**
    * 更新个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateDailyBasic(DailyBasicEditReqVo vo)
    {
        return dailyBasicService.updateDailyBasic(vo);
    }
    /**
    * 单条查询个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public DailyBasicResVo selectDailyBasicById(DailyBasicDetailsReqVo vo)
    {
        return dailyBasicService.selectDailyBasicById(vo);
    }
    /**
    * 删除个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteDailyBasicById(DailyBasicDeleteReqVo vo)
    {
        return dailyBasicService.deleteDailyBasicById(vo);
    }
    /**
    * 分页获取个股每日指标结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<DailyBasicListResVo>  selectDailyBasicPageList(DailyBasicListReqVo vo){
        return dailyBasicService.selectDailyBasicPageList(vo);
    }



}
