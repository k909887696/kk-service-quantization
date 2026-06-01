package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.model.vo.IndexClassifyListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 申万行业分类	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IndexClassifyMapper extends RootMapper<IndexClassify> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, IndexClassifyListVo indexClassifyListVo);
}
