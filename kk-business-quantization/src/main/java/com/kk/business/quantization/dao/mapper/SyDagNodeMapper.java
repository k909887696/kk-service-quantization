package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyDagNode;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.SyDagNodeListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeListReqVo;
/**
 * <p>
 * DAG执行节点 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
public interface SyDagNodeMapper extends RootMapper<SyDagNode> {
     /**
     * 查询DAG执行节点列表
     */
     Page<SyDagNodeListResVo> selectSyDagNodePageList(Page page, SyDagNodeListReqVo syDagNodeListReqVo);
}
