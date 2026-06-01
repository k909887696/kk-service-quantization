package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.business.quantization.model.vo.KdjCrossListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * kdj交叉点	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface KdjCrossMapper extends RootMapper<KdjCross> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, KdjCrossListVo kdjCrossListVo);
}
