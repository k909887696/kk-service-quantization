package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.business.quantization.service.IIndexDailyService;
import com.kk.business.quantization.serviceapi.IndexDailyServiceApi;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数日线行情 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class IndexDailyServiceView   {

    @Resource
    public IndexDailyServiceApi indexDailyServiceApi;



}
