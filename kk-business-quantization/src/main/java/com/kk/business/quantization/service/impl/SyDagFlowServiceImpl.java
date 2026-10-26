package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.business.quantization.dao.entity.SyDagFlow;
import com.kk.business.quantization.dao.mapper.SyDagFlowMapper;
import com.kk.business.quantization.service.ISyDagFlowService;
import com.kk.business.quantization.model.vobase.req.SyDagFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowResVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagFlowDeleteReqVo;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG执行流 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagFlowServiceImpl extends ServiceImpl<SyDagFlowMapper, SyDagFlow> implements ISyDagFlowService {


    /**
    * 分批批量插入DAG执行流
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagFlowBatchSomeColumn(List<SyDagFlow> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagFlow> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            for(SyDagFlow model : tempList){
                model.setDagFlowId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_FLOW, DateUtil.PATTERN_STANDARD02W));
            }
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagFlow(SyDagFlowAddReqVo vo)
    {
        SyDagFlow model = new SyDagFlow();
        BeanUtil.copyProperties(vo,model);
        model.setDagFlowId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_FLOW, DateUtil.PATTERN_STANDARD02W));
        this.baseMapper.insert(model);
    }
    /**
    * 更新DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagFlow(SyDagFlowEditReqVo vo)
    {
        SyDagFlow model = new SyDagFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行流更新失败!");
        }
        return r;
    }
    /**
    * 单条查询DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagFlowResVo selectSyDagFlowById(SyDagFlowDetailsReqVo vo)
    {
        SyDagFlow model = new SyDagFlow();
        BeanUtil.copyProperties(vo,model);
        SyDagFlow res = this.baseMapper.selectById(model);
        SyDagFlowResVo resVo = new SyDagFlowResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除DAG执行流
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagFlowById(SyDagFlowDeleteReqVo vo)
    {
        SyDagFlow model = new SyDagFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行流删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG执行流结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagFlowListResVo>  selectSyDagFlowPageList(SyDagFlowListReqVo vo){

        Page<SyDagFlowListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagFlowListResVo> results = this.baseMapper.selectSyDagFlowPageList(page,vo);
        PageResult<SyDagFlowListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }

    /**
    * 根据dagId查询DAG执行流列表
    * @param dagId DAG主表ID
    * @return DAG执行流列表
    */
    @Override
    public List<SyDagFlowListResVo> selectSyDagFlowListByDagId(String dagId)
    {
        List<SyDagFlow> flowList = this.baseMapper.selectList(new QueryWrapper<SyDagFlow>().eq("dag_id", dagId));
        List<SyDagFlowListResVo> flowResList = new java.util.ArrayList<>();
        if(flowList != null && !flowList.isEmpty())
        {
            for(SyDagFlow flow : flowList)
            {
                SyDagFlowListResVo flowResVo = new SyDagFlowListResVo();
                BeanUtil.copyProperties(flow, flowResVo);
                flowResList.add(flowResVo);
            }
        }
        return flowResList;
    }

    /**
    * 根据dagId删除DAG执行流
    * @param dagId DAG主表ID
    * @return 删除条数
    */
    @Override
    public int deleteSyDagFlowByDagId(String dagId)
    {
        return this.baseMapper.delete(new QueryWrapper<SyDagFlow>().eq("dag_id", dagId));
    }
}
