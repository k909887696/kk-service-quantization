package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
/**
 * <p>
 * 个人自选股 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface SecuritySelectionMapper extends RootMapper<SecuritySelection> {
     /**
     * 查询个人自选股列表
     */
     Page<SecuritySelectionListResVo> selectSecuritySelectionPageList(Page page, SecuritySelectionListReqVo securitySelectionListReqVo);
}
