package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.dto.IndexWeightListDto;
import com.kk.business.quantization.model.vo.IndexWeightListVo;
/**
 * <p>
 * 指数成分权重 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
public interface IndexWeightMapper extends RootMapper<IndexWeight> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, IndexWeightListVo indexWeightListVo);
}
