package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.DailyBasicListResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicListReqVo;
/**
 * <p>
 * 个股每日指标 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface DailyBasicMapper extends RootMapper<DailyBasic> {
     /**
     * 查询个股每日指标列表
     */
     Page<DailyBasicListResVo> selectDailyBasicPageList(Page page, DailyBasicListReqVo dailyBasicListReqVo);
}
