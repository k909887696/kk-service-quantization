package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyResVo;
import com.kk.business.quantization.model.vobase.req.DailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股日线行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyServiceApi   {

    @Resource
    public IDailyService dailyService;

    /**
    * 分批批量插入个股日线行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertDailyBatchSomeColumn(List<Daily> list)
    {
        dailyService.insertDailyBatchSomeColumn(list);
    }
    /**
    * 单条插入个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertDaily(DailyAddReqVo vo)
    {
        dailyService.insertDaily(vo);
    }
    /**
    * 更新个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateDaily(DailyEditReqVo vo)
    {
        return dailyService.updateDaily(vo);
    }
    /**
    * 单条查询个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public DailyResVo selectDailyById(DailyDetailsReqVo vo)
    {
        return dailyService.selectDailyById(vo);
    }
    /**
    * 删除个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteDailyById(DailyDeleteReqVo vo)
    {
        return dailyService.deleteDailyById(vo);
    }
    /**
    * 分页获取个股日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<DailyListResVo>  selectDailyPageList(DailyListReqVo vo){
        return dailyService.selectDailyPageList(vo);
    }



}
