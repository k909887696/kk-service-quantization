package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
/**
 * <p>
 * 个股资金流向 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface MoneyFlowMapper extends RootMapper<MoneyFlow> {
     /**
     * 查询个股资金流向列表
     */
     Page<MoneyFlowListResVo> selectMoneyFlowPageList(Page page, MoneyFlowListReqVo moneyFlowListReqVo);
}
