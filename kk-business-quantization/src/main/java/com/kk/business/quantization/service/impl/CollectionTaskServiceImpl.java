package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.entity.CollectionTask;
import com.kk.business.quantization.dao.mapper.CollectionTaskMapper;
import com.kk.business.quantization.model.vo.SearchTaskListVo;
import com.kk.business.quantization.model.vo.SelectPreExecuteTaskVo;
import com.kk.business.quantization.service.ICollectionTaskService;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据任务 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class CollectionTaskServiceImpl extends MppServiceImpl<CollectionTaskMapper, CollectionTask> implements ICollectionTaskService {

    @Resource
    public CollectionTaskMapper mapper;

    /**
     * 更新异常信息(每次执行失败，隔2min 再执行)
     * @param taskId
     * @param exMsg
     * @return
     */
    public int updateExMsgAndRunCount(String taskId,String exMsg)
    {
        return mapper.updateExMsgAndRunCount(taskId,exMsg);
    }
    /**
     * 更新
     * @param task
     * @return
     */
    public int update(CollectionTask task)
    {
        return mapper.updateById(task);
    }
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CollectionTask> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        List<String> selialNoList = SerialNoUtil.getMultiNextId(SerialNoType.COLLECTION_TASK, DateUtil.PATTERN_STANDARD04W,10,totalCount);

        for (int i=0;i<totalCount;i++
             ) {
            list.get(i).setTaskId(selialNoList.get(i));
        }

        for(;index<=totalPage;index++)
        {
            List<CollectionTask> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CollectionTask>  getCollectionTaskPageResult(SearchTaskListVo vo){

        LambdaQueryWrapper<CollectionTask> query = new LambdaQueryWrapper<>();
        IPage<CollectionTask> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件
        if(StringUtils.isNotBlank(vo.getTaskId())) {
            query.eq(CollectionTask::getTaskId, vo.getTaskId());
        }
        if(StringUtils.isNotBlank(vo.getPolicyId())) {
            query.eq(CollectionTask::getPolicyId, vo.getPolicyId());
        }
        if(StringUtils.isNotBlank(vo.getName())) {
            query.like(CollectionTask::getName, vo.getName());
        }
        if(StringUtils.isNotBlank(vo.getInvokeCode())) {
            query.eq(CollectionTask::getInvokeCode, vo.getInvokeCode());
        }
        query.orderByDesc(CollectionTask::getTaskId);
        page = mapper.selectPage(page,query);
        PageResult<CollectionTask>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 获取需要处理的任务
     * @param vo
     * @return 任务集合
     */
    public List<CollectionTask> getPreExecuteTask(SelectPreExecuteTaskVo vo)
    {
        QueryWrapper<CollectionTask> query = new QueryWrapper<>();
        IPage<CollectionTask> page = new Page<>(vo.getPageIndex(),vo.getPageSize() <=0 ? 20:vo.getPageSize());

        vo.setRunCount(vo.getRunCount() == null || vo.getRunCount()<=0 ? 10 : vo.getRunCount());
        vo.setPreRunTimeEnd(vo.getPreRunTimeEnd()==null ? new Date() : vo.getPreRunTimeEnd());
        page = this.baseMapper.selectPreExecuteTask(page,vo);
        return page.getRecords();
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

        List<CollectionTask> list = mapper.selectList(query);
        return list;
    }
}
