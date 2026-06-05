package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.business.quantization.service.IDailyTimeService;
import com.kk.business.quantization.serviceapi.DailyTimeServiceApi;
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
 * 个股分钟行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class DailyTimeServiceView   {

    @Resource
    public DailyTimeServiceApi dailyTimeServiceApi;



}
