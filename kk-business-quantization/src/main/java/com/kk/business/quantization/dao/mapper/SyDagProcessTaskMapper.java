package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagProcessTask;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
/**
 * <p>
 * DAG执行实例任务 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagProcessTaskMapper extends RootMapper<SyDagProcessTask> {
     /**
     * 查询DAG执行实例任务列表
     */
     Page<SyDagProcessTaskListResVo> selectSyDagProcessTaskPageList(Page page, SyDagProcessTaskListReqVo syDagProcessTaskListReqVo);
}
