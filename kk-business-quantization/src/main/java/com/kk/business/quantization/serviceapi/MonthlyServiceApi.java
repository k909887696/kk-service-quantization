package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.business.quantization.service.IMonthlyService;
import com.kk.business.quantization.model.vobase.req.MonthlyListReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyListResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyAddReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyEditReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股月线行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MonthlyServiceApi   {

    @Resource
    public IMonthlyService monthlyService;

    /**
    * 分批批量插入个股月线行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertMonthlyBatchSomeColumn(List<Monthly> list)
    {
        monthlyService.insertMonthlyBatchSomeColumn(list);
    }
    /**
    * 单条插入个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertMonthly(MonthlyAddReqVo vo)
    {
        monthlyService.insertMonthly(vo);
    }
    /**
    * 更新个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateMonthly(MonthlyEditReqVo vo)
    {
        return monthlyService.updateMonthly(vo);
    }
    /**
    * 单条查询个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public MonthlyResVo selectMonthlyById(MonthlyDetailsReqVo vo)
    {
        return monthlyService.selectMonthlyById(vo);
    }
    /**
    * 删除个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteMonthlyById(MonthlyDeleteReqVo vo)
    {
        return monthlyService.deleteMonthlyById(vo);
    }
    /**
    * 分页获取个股月线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<MonthlyListResVo>  selectMonthlyPageList(MonthlyListReqVo vo){
        return monthlyService.selectMonthlyPageList(vo);
    }



}
