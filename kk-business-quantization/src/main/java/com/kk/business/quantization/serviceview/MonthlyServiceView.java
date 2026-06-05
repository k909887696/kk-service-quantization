package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.business.quantization.service.IMonthlyService;
import com.kk.business.quantization.serviceapi.MonthlyServiceApi;
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
 * 个股月线行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class MonthlyServiceView   {

    @Resource
    public MonthlyServiceApi monthlyServiceApi;



}
