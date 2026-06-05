package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MarketBasic;
import com.kk.business.quantization.service.IMarketBasicService;
import com.kk.business.quantization.serviceapi.MarketBasicServiceApi;
import com.kk.business.quantization.model.vobase.req.MarketBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicListResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 市场基本信息 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class MarketBasicServiceView   {

    @Resource
    public MarketBasicServiceApi marketBasicServiceApi;



}
