package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Weekly;
import com.kk.business.quantization.service.IWeeklyService;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyAddReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyEditReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股周线行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class WeeklyServiceApi   {

    @Resource
    public IWeeklyService weeklyService;

    /**
    * 分批批量插入个股周线行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertWeeklyBatchSomeColumn(List<Weekly> list)
    {
        weeklyService.insertWeeklyBatchSomeColumn(list);
    }
    /**
    * 单条插入个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertWeekly(WeeklyAddReqVo vo)
    {
        weeklyService.insertWeekly(vo);
    }
    /**
    * 更新个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateWeekly(WeeklyEditReqVo vo)
    {
        return weeklyService.updateWeekly(vo);
    }
    /**
    * 单条查询个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public WeeklyResVo selectWeeklyById(WeeklyDetailsReqVo vo)
    {
        return weeklyService.selectWeeklyById(vo);
    }
    /**
    * 删除个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteWeeklyById(WeeklyDeleteReqVo vo)
    {
        return weeklyService.deleteWeeklyById(vo);
    }
    /**
    * 分页获取个股周线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<WeeklyListResVo>  selectWeeklyPageList(WeeklyListReqVo vo){
        return weeklyService.selectWeeklyPageList(vo);
    }



}
