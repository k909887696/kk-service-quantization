package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.MonthlyListResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyListReqVo;
/**
 * <p>
 * 个股月线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface MonthlyMapper extends RootMapper<Monthly> {
     /**
     * 查询个股月线行情列表
     */
     Page<MonthlyListResVo> selectMonthlyPageList(Page page, MonthlyListReqVo monthlyListReqVo);
}
