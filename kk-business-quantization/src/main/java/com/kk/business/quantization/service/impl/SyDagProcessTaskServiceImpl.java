package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyDagProcessTask;
import com.kk.business.quantization.dao.mapper.SyDagProcessTaskMapper;
import com.kk.business.quantization.service.ISyDagProcessTaskService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskDeleteReqVo;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG执行实例任务 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskServiceImpl extends ServiceImpl<SyDagProcessTaskMapper, SyDagProcessTask> implements ISyDagProcessTaskService {


    /**
    * 分批批量插入DAG执行实例任务
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagProcessTaskBatchSomeColumn(List<SyDagProcessTask> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagProcessTask> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            for(SyDagProcessTask model : tempList){
                model.setProcessTaskId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_PROCESS_TASK, DateUtil.PATTERN_STANDARD02W));
            }
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagProcessTask(SyDagProcessTaskAddReqVo vo)
    {
        SyDagProcessTask model = new SyDagProcessTask();
        BeanUtil.copyProperties(vo,model);
        model.setProcessTaskId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_PROCESS_TASK, DateUtil.PATTERN_STANDARD02W));
        this.baseMapper.insert(model);
    }
    /**
    * 更新DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagProcessTask(SyDagProcessTaskEditReqVo vo)
    {
        SyDagProcessTask model = new SyDagProcessTask();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例任务更新失败!");
        }
        return r;
    }
    /**
    * 单条查询DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagProcessTaskResVo selectSyDagProcessTaskById(SyDagProcessTaskDetailsReqVo vo)
    {
        SyDagProcessTask model = new SyDagProcessTask();
        BeanUtil.copyProperties(vo,model);
        SyDagProcessTask res = this.baseMapper.selectById(model);
        SyDagProcessTaskResVo resVo = new SyDagProcessTaskResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除DAG执行实例任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagProcessTaskById(SyDagProcessTaskDeleteReqVo vo)
    {
        SyDagProcessTask model = new SyDagProcessTask();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例任务删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG执行实例任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagProcessTaskListResVo>  selectSyDagProcessTaskPageList(SyDagProcessTaskListReqVo vo){

        Page<SyDagProcessTaskListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagProcessTaskListResVo> results = this.baseMapper.selectSyDagProcessTaskPageList(page,vo);
        PageResult<SyDagProcessTaskListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
