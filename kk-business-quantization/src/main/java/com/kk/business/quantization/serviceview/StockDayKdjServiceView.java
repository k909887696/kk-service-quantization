package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.business.quantization.serviceapi.StockDayKdjServiceApi;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股kdj数据 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class StockDayKdjServiceView   {

    @Resource
    public StockDayKdjServiceApi stockDayKdjServiceApi;



}
