package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.business.quantization.service.IMaxPctChgService;
import com.kk.business.quantization.serviceapi.MaxPctChgServiceApi;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgAddReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgEditReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 各个市场涨跌幅限制 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class MaxPctChgServiceView   {

    @Resource
    public MaxPctChgServiceApi maxPctChgServiceApi;



}
