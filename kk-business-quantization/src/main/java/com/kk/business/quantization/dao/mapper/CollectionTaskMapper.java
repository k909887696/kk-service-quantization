package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
/**
 * <p>
 * 系统设置-数据任务 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface CollectionTaskMapper extends RootMapper<CollectionTask> {
     /**
     * 查询系统设置-数据任务列表
     */
     Page<CollectionTaskListResVo> selectCollectionTaskPageList(Page page, CollectionTaskListReqVo collectionTaskListReqVo);
     /**
     * 获取需要处理任务
     */
     Page<CollectionTask>  selectPreExecuteTask(Page page, SelectPreExecuteTaskVo vo);

     /**
      * 更新异常信息
      * @param taskId
      * @param exMsg
      * @return
      */
     int updateExMsgAndRunCount(String taskId,String exMsg);
}
