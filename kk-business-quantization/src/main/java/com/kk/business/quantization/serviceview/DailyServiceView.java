package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.serviceapi.DailyServiceApi;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyResVo;
import com.kk.business.quantization.model.vobase.req.DailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股日线行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class DailyServiceView   {

    @Resource
    public DailyServiceApi dailyServiceApi;



}
