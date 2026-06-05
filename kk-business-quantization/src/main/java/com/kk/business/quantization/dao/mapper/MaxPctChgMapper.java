package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
/**
 * <p>
 * 各个市场涨跌幅限制 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface MaxPctChgMapper extends RootMapper<MaxPctChg> {
     /**
     * 查询各个市场涨跌幅限制列表
     */
     Page<MaxPctChgListResVo> selectMaxPctChgPageList(Page page, MaxPctChgListReqVo maxPctChgListReqVo);
}
