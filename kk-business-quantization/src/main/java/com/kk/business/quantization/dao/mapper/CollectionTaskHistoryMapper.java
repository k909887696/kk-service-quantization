package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.model.vo.CollectionTaskHistoryListVo;
import com.kk.common.dao.mapper.RootMapper;

/**
 * <p>
 * 系统设置-数据任务-历史	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface CollectionTaskHistoryMapper extends RootMapper<CollectionTaskHistory> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, CollectionTaskHistoryListVo collectionTaskHistoryListVo);
}
