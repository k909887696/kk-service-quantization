package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
/**
 * <p>
 * 人民币货币总量对象 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
public interface CnMMapper extends RootMapper<CnM> {
     /**
     * 查询人民币货币总量对象列表
     */
     Page<CnMListResVo> selectCnMPageList(Page page, CnMListReqVo cnMListReqVo);
}
