package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.dao.mapper.CollectionTaskHistoryMapper;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.utils.DateUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.mapper.CollectionTaskMapper;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.model.vobase.req.CollectionTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 系统设置-数据任务 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionTaskServiceImpl extends ServiceImpl<CollectionTaskMapper, CollectionTask> implements ICollectionTaskService {

    @Resource
    public CollectionTaskHistoryMapper collectionTaskHistoryMapper;
    /**
    * 分批批量插入系统设置-数据任务
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertCollectionTaskBatchSomeColumn(List<CollectionTask> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CollectionTask> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertCollectionTask(CollectionTaskAddReqVo vo)
    {
        CollectionTask model = new CollectionTask();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateCollectionTask(CollectionTaskEditReqVo vo)
    {
        CollectionTask model = new CollectionTask();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据任务更新失败!");
        }
        return r;
    }
    /**
    * 单条查询系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public CollectionTaskResVo selectCollectionTaskById(CollectionTaskDetailsReqVo vo)
    {
        CollectionTask model = new CollectionTask();
        BeanUtil.copyProperties(vo,model);
        CollectionTask res = this.baseMapper.selectById(model);
        CollectionTaskResVo resVo = new CollectionTaskResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除系统设置-数据任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteCollectionTaskById(CollectionTaskDeleteReqVo vo)
    {
        CollectionTask model = new CollectionTask();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据任务删除失败!");
        }
        return r;
    }
    /**
    * 分页获取系统设置-数据任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<CollectionTaskListResVo>  selectCollectionTaskPageList(CollectionTaskListReqVo vo){

        Page<CollectionTaskListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<CollectionTaskListResVo> results = this.baseMapper.selectCollectionTaskPageList(page,vo);
        PageResult<CollectionTaskListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }
    /**
     * 获取需要处理的任务
     * @param vo
     * @return 任务集合
     */
    public PageResult<CollectionTask> getPreExecuteTask(SelectPreExecuteTaskVo vo)
    {
        QueryWrapper<CollectionTask> query = new QueryWrapper<>();
        Page<CollectionTask> page = new Page<>(vo.getPageIndex(),vo.getPageSize() <=0 ? 20:vo.getPageSize());

        vo.setRunCount(vo.getRunCount() == null || vo.getRunCount()<=0 ? 10 : vo.getRunCount());
        vo.setPreRunTimeEnd(vo.getPreRunTimeEnd()==null ? new Date() : vo.getPreRunTimeEnd());
        page = this.baseMapper.selectPreExecuteTask(page,vo);
        PageResult<CollectionTask>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }

    /**
     * 根据ids查询任务列表
     * @param ids
     * @return
     */
    public List<CollectionTask> getTaskByIds(List<String> ids)
    {
        QueryWrapper<CollectionTask> query = new QueryWrapper<>();

        query.in("task_id",ids);

        List<CollectionTask> list = this.baseMapper.selectList(query);
        return list;
    }

    /**
     * 重新执行一次任务
     * @param taskId 任务编号
     * @return
     */
    public void retryExecuteTask(String taskId)
    {
        CollectionTask task = this.baseMapper.selectById(taskId);
        CollectionTaskHistory history = null;
        if(task == null) {
            history = collectionTaskHistoryMapper.selectById(taskId);
            if(history==null) {
                throw new BusinessException("历史任务不存在");
            }
            BeanUtil.copyProperties(history,task);
            task.setTaskId(SerialNoUtil.getSingleNextId(SerialNoType.COLLECTION_TASK, DateUtil.PATTERN_STANDARD02W));
            task.setRunCount(0);
            task.setPreRunTime(new Date());
            this.baseMapper.insert(task);
        }else{
            task.setPreRunTime(new Date());
            task.setRunCount(0);
            this.baseMapper.updateById(task);
        }

    }


}
