package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
/**
 * <p>
 * 系统设置-数据任务-历史 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface CollectionTaskHistoryMapper extends RootMapper<CollectionTaskHistory> {
     /**
     * 查询系统设置-数据任务-历史列表
     */
     Page<CollectionTaskHistoryListResVo> selectCollectionTaskHistoryPageList(Page page, CollectionTaskHistoryListReqVo collectionTaskHistoryListReqVo);
}
