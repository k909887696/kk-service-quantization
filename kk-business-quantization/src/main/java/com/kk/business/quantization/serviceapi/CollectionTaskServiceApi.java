package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 系统设置-数据任务 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionTaskServiceApi   {

    @Resource
    public ICollectionTaskService collectionTaskService;

    /**
    * 分批批量插入系统设置-数据任务
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertCollectionTaskBatchSomeColumn(List<CollectionTask> list)
    {
        collectionTaskService.insertCollectionTaskBatchSomeColumn(list);
    }
    /**
    * 单条插入系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertCollectionTask(CollectionTaskAddReqVo vo)
    {
        collectionTaskService.insertCollectionTask(vo);
    }
    /**
    * 更新系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateCollectionTask(CollectionTaskEditReqVo vo)
    {
        return collectionTaskService.updateCollectionTask(vo);
    }
    /**
    * 单条查询系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public CollectionTaskResVo selectCollectionTaskById(CollectionTaskDetailsReqVo vo)
    {
        return collectionTaskService.selectCollectionTaskById(vo);
    }
    /**
    * 删除系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteCollectionTaskById(CollectionTaskDeleteReqVo vo)
    {
        return collectionTaskService.deleteCollectionTaskById(vo);
    }
    /**
    * 分页获取系统设置-数据任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<CollectionTaskListResVo>  selectCollectionTaskPageList(CollectionTaskListReqVo vo){
        return collectionTaskService.selectCollectionTaskPageList(vo);
    }



}
