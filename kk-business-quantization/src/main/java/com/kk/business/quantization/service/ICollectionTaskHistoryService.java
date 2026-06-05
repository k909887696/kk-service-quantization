package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据任务-历史 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ICollectionTaskHistoryService  {

    /**
    * 分批批量插入系统设置-数据任务-历史
    * @param list 数据列表
    * @return
    */
    void insertCollectionTaskHistoryBatchSomeColumn(List<CollectionTaskHistory> list);
    /**
    * 单条插入系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    void insertCollectionTaskHistory(CollectionTaskHistoryAddReqVo vo);
    /**
    * 更新系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    int updateCollectionTaskHistory(CollectionTaskHistoryEditReqVo vo);
    /**
    * 单条查询系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    CollectionTaskHistoryResVo selectCollectionTaskHistoryById(CollectionTaskHistoryDetailsReqVo vo);
    /**
    * 删除系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteCollectionTaskHistoryById(CollectionTaskHistoryDeleteReqVo vo);
    /**
    * 分页获取系统设置-数据任务-历史结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CollectionTaskHistoryListResVo>  selectCollectionTaskHistoryPageList(CollectionTaskHistoryListReqVo vo);
}

