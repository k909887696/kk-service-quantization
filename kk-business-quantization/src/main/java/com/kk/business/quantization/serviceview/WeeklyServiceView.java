package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Weekly;
import com.kk.business.quantization.service.IWeeklyService;
import com.kk.business.quantization.serviceapi.WeeklyServiceApi;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyAddReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyEditReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股周线行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class WeeklyServiceView   {

    @Resource
    public WeeklyServiceApi weeklyServiceApi;



}
