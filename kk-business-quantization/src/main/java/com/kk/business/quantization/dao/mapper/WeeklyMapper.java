package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Weekly;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
/**
 * <p>
 * 个股周线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface WeeklyMapper extends RootMapper<Weekly> {
     /**
     * 查询个股周线行情列表
     */
     Page<WeeklyListResVo> selectWeeklyPageList(Page page, WeeklyListReqVo weeklyListReqVo);
}
