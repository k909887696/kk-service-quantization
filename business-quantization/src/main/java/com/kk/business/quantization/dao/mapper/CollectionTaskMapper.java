package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据任务 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-16
 */

public interface CollectionTaskMapper extends RootMapper<CollectionTask> {

    /**
     * 更新异常信息
     * @param taskId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String taskId,String exMsg);

}
