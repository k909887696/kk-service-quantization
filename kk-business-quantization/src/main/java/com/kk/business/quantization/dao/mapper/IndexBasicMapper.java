package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
/**
 * <p>
 * 指数基本信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IndexBasicMapper extends RootMapper<IndexBasic> {
     /**
     * 查询指数基本信息列表
     */
     Page<IndexBasicListResVo> selectIndexBasicPageList(Page page, IndexBasicListReqVo indexBasicListReqVo);
}
