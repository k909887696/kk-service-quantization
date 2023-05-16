package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Weekly;
import com.kk.business.quantization.model.vo.WeeklyListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股周线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface WeeklyMapper extends RootMapper<Weekly> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, WeeklyListVo weeklyListVo);
}
