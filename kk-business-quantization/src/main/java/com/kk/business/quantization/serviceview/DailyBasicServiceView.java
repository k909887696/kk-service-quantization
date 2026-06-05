package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.service.IDailyBasicService;
import com.kk.business.quantization.serviceapi.DailyBasicServiceApi;
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
 * 个股每日指标 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class DailyBasicServiceView   {

    @Resource
    public DailyBasicServiceApi dailyBasicServiceApi;



}
