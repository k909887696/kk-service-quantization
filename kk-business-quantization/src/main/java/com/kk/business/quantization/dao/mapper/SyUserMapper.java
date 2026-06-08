package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyUser;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyUserListResVo;
import com.kk.business.quantization.model.vobase.req.SyUserListReqVo;
/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
public interface SyUserMapper extends RootMapper<SyUser> {
     /**
     * 查询用户信息列表
     */
     Page<SyUserListResVo> selectSyUserPageList(Page page, SyUserListReqVo syUserListReqVo);
}
