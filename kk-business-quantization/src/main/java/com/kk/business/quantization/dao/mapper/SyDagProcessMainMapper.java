package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagProcessMain;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
/**
 * <p>
 * DAG执行实例主表 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagProcessMainMapper extends RootMapper<SyDagProcessMain> {
     /**
     * 查询DAG执行实例主表列表
     */
     Page<SyDagProcessMainListResVo> selectSyDagProcessMainPageList(Page page, SyDagProcessMainListReqVo syDagProcessMainListReqVo);
}
