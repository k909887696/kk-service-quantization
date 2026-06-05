package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
/**
 * <p>
 * 申万行业分类 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IndexClassifyMapper extends RootMapper<IndexClassify> {
     /**
     * 查询申万行业分类列表
     */
     Page<IndexClassifyListResVo> selectIndexClassifyPageList(Page page, IndexClassifyListReqVo indexClassifyListReqVo);
}
