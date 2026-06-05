package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
/**
 * <p>
 * 指数日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IndexDailyMapper extends RootMapper<IndexDaily> {
     /**
     * 查询指数日线行情列表
     */
     Page<IndexDailyListResVo> selectIndexDailyPageList(Page page, IndexDailyListReqVo indexDailyListReqVo);
}
