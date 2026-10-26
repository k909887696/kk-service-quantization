package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagFlow;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
/**
 * <p>
 * DAG执行流 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagFlowMapper extends RootMapper<SyDagFlow> {
     /**
     * 查询DAG执行流列表
     */
     Page<SyDagFlowListResVo> selectSyDagFlowPageList(Page page, SyDagFlowListReqVo syDagFlowListReqVo);
}
