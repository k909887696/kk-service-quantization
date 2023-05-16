package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.model.vo.SecuritySelectionListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个人自选股 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface SecuritySelectionMapper extends RootMapper<SecuritySelection> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, SecuritySelectionListVo securitySelectionListVo);
}
