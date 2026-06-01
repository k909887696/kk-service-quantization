package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.model.vo.DailyBasicListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股每日指标	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface DailyBasicMapper extends RootMapper<DailyBasic> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, DailyBasicListVo dailyBasicListVo);
}
