package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.business.quantization.model.vo.MoneyFlowListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 个股资金流向	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface MoneyFlowMapper extends RootMapper<MoneyFlow> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, MoneyFlowListVo moneyFlowListVo);
}
