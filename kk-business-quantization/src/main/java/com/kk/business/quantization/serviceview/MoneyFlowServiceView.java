package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.business.quantization.service.IMoneyFlowService;
import com.kk.business.quantization.serviceapi.MoneyFlowServiceApi;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股资金流向 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class MoneyFlowServiceView   {

    @Resource
    public MoneyFlowServiceApi moneyFlowServiceApi;



}
