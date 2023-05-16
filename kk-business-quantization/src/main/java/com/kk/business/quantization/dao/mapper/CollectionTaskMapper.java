package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.CollectionTaskListVo;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统设置-数据任务 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */

public interface CollectionTaskMapper extends RootMapper<CollectionTask> {
    /**
     * 查询列表
     */
    Page selectPageList(IPage page, CollectionTaskListVo collectionTaskListVo);
    /**
     * 更新异常信息
     * @param taskId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String taskId,String exMsg);

}
