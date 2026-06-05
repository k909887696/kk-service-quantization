package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.service.ICollectionTaskHistoryService;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 系统设置-数据任务-历史 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionTaskHistoryServiceApi   {

    @Resource
    public ICollectionTaskHistoryService collectionTaskHistoryService;

    /**
    * 分批批量插入系统设置-数据任务-历史
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertCollectionTaskHistoryBatchSomeColumn(List<CollectionTaskHistory> list)
    {
        collectionTaskHistoryService.insertCollectionTaskHistoryBatchSomeColumn(list);
    }
    /**
    * 单条插入系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertCollectionTaskHistory(CollectionTaskHistoryAddReqVo vo)
    {
        collectionTaskHistoryService.insertCollectionTaskHistory(vo);
    }
    /**
    * 更新系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateCollectionTaskHistory(CollectionTaskHistoryEditReqVo vo)
    {
        return collectionTaskHistoryService.updateCollectionTaskHistory(vo);
    }
    /**
    * 单条查询系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public CollectionTaskHistoryResVo selectCollectionTaskHistoryById(CollectionTaskHistoryDetailsReqVo vo)
    {
        return collectionTaskHistoryService.selectCollectionTaskHistoryById(vo);
    }
    /**
    * 删除系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteCollectionTaskHistoryById(CollectionTaskHistoryDeleteReqVo vo)
    {
        return collectionTaskHistoryService.deleteCollectionTaskHistoryById(vo);
    }
    /**
    * 分页获取系统设置-数据任务-历史结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<CollectionTaskHistoryListResVo>  selectCollectionTaskHistoryPageList(CollectionTaskHistoryListReqVo vo){
        return collectionTaskHistoryService.selectCollectionTaskHistoryPageList(vo);
    }



}
