package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.business.quantization.model.vo.MaxPctChgListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 各个市场涨跌幅限制	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface MaxPctChgMapper extends RootMapper<MaxPctChg> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, MaxPctChgListVo maxPctChgListVo);
}
