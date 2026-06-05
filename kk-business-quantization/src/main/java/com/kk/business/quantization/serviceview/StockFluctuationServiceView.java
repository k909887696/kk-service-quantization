package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.kk.business.quantization.service.IStockFluctuationService;
import com.kk.business.quantization.serviceapi.StockFluctuationServiceApi;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股异常波动信息 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class StockFluctuationServiceView   {

    @Resource
    public StockFluctuationServiceApi stockFluctuationServiceApi;



}
