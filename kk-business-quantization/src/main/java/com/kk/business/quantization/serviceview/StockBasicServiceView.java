package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.serviceapi.StockBasicServiceApi;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股基本信息 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class StockBasicServiceView   {

    @Resource
    public StockBasicServiceApi stockBasicServiceApi;



}
