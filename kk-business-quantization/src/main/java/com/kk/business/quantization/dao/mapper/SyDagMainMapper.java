package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagMain;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
/**
 * <p>
 * DAG主表 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagMainMapper extends RootMapper<SyDagMain> {
     /**
     * 查询DAG主表列表
     */
     Page<SyDagMainListResVo> selectSyDagMainPageList(Page page, SyDagMainListReqVo syDagMainListReqVo);
}
