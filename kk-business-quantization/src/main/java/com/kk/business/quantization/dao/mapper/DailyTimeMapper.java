package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.business.quantization.model.vo.DailyTimeListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股分钟行情	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface DailyTimeMapper extends RootMapper<DailyTime> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, DailyTimeListVo dailyTimeListVo);
}
