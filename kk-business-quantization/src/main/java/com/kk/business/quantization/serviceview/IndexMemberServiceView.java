package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.business.quantization.service.IIndexMemberService;
import com.kk.business.quantization.serviceapi.IndexMemberServiceApi;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业明细 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class IndexMemberServiceView   {

    @Resource
    public IndexMemberServiceApi indexMemberServiceApi;



}
