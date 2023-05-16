package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.model.vo.StockDayKdjListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股kdj数据 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface StockDayKdjMapper extends RootMapper<StockDayKdj> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, StockDayKdjListVo stockDayKdjListVo);
}
