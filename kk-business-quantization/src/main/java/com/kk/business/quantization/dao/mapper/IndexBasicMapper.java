package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.model.vo.IndexBasicListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 指数基本信息	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IndexBasicMapper extends RootMapper<IndexBasic> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, IndexBasicListVo indexBasicListVo);
}
