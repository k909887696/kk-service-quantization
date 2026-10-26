package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyDagProcessTaskHis;
import com.kk.business.quantization.dao.mapper.SyDagProcessTaskHisMapper;
import com.kk.business.quantization.service.ISyDagProcessTaskHisService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG执行实例历史任务 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskHisServiceImpl extends ServiceImpl<SyDagProcessTaskHisMapper, SyDagProcessTaskHis> implements ISyDagProcessTaskHisService {


    /**
    * 分批批量插入DAG执行实例历史任务
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagProcessTaskHisBatchSomeColumn(List<SyDagProcessTaskHis> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagProcessTaskHis> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagProcessTaskHis(SyDagProcessTaskHisAddReqVo vo)
    {
        SyDagProcessTaskHis model = new SyDagProcessTaskHis();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagProcessTaskHis(SyDagProcessTaskHisEditReqVo vo)
    {
        SyDagProcessTaskHis model = new SyDagProcessTaskHis();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例历史任务更新失败!");
        }
        return r;
    }
    /**
    * 单条查询DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagProcessTaskHisResVo selectSyDagProcessTaskHisById(SyDagProcessTaskHisDetailsReqVo vo)
    {
        SyDagProcessTaskHis model = new SyDagProcessTaskHis();
        BeanUtil.copyProperties(vo,model);
        SyDagProcessTaskHis res = this.baseMapper.selectById(model);
        SyDagProcessTaskHisResVo resVo = new SyDagProcessTaskHisResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagProcessTaskHisById(SyDagProcessTaskHisDeleteReqVo vo)
    {
        SyDagProcessTaskHis model = new SyDagProcessTaskHis();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例历史任务删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG执行实例历史任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagProcessTaskHisListResVo>  selectSyDagProcessTaskHisPageList(SyDagProcessTaskHisListReqVo vo){

        Page<SyDagProcessTaskHisListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagProcessTaskHisListResVo> results = this.baseMapper.selectSyDagProcessTaskHisPageList(page,vo);
        PageResult<SyDagProcessTaskHisListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
