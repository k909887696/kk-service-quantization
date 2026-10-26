package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagProcessTaskHis;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
/**
 * <p>
 * DAG执行实例历史任务 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagProcessTaskHisMapper extends RootMapper<SyDagProcessTaskHis> {
     /**
     * 查询DAG执行实例历史任务列表
     */
     Page<SyDagProcessTaskHisListResVo> selectSyDagProcessTaskHisPageList(Page page, SyDagProcessTaskHisListReqVo syDagProcessTaskHisListReqVo);
}
