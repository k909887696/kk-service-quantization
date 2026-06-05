package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.serviceapi.TradeCalServiceApi;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalAddReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalEditReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 交易日历 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class TradeCalServiceView   {

    @Resource
    public TradeCalServiceApi tradeCalServiceApi;



}
