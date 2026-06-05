package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.DailyTimeListResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeListReqVo;
/**
 * <p>
 * 个股分钟行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface DailyTimeMapper extends RootMapper<DailyTime> {
     /**
     * 查询个股分钟行情列表
     */
     Page<DailyTimeListResVo> selectDailyTimePageList(Page page, DailyTimeListReqVo dailyTimeListReqVo);
}
