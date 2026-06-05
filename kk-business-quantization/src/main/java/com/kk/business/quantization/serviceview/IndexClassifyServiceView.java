package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.kk.business.quantization.serviceapi.IndexClassifyServiceApi;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业分类 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class IndexClassifyServiceView   {

    @Resource
    public IndexClassifyServiceApi indexClassifyServiceApi;



}
