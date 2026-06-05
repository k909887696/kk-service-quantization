package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
/**
 * <p>
 * 申万行业明细 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IndexMemberMapper extends RootMapper<IndexMember> {
     /**
     * 查询申万行业明细列表
     */
     Page<IndexMemberListResVo> selectIndexMemberPageList(Page page, IndexMemberListReqVo indexMemberListReqVo);
}
