package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.business.quantization.model.vo.MonthlyListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股月线行情	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface MonthlyMapper extends RootMapper<Monthly> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, MonthlyListVo monthlyListVo);
}
