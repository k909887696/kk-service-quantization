package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.constant.CollectionHandType;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.business.quantization.service.handler.TaskExecutorHandler;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.mapper.CollectionPolicyMapper;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 系统设置-数据策略 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionPolicyServiceImpl extends ServiceImpl<CollectionPolicyMapper, CollectionPolicy> implements ICollectionPolicyService {

    @Resource
    public TaskExecutorHandler taskExecutorHandler;

    /**
    * 分批批量插入系统设置-数据策略
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertCollectionPolicyBatchSomeColumn(List<CollectionPolicy> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CollectionPolicy> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertCollectionPolicy(CollectionPolicyAddReqVo vo)
    {
        CollectionPolicy model = new CollectionPolicy();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateCollectionPolicy(CollectionPolicyEditReqVo vo)
    {
        CollectionPolicy model = new CollectionPolicy();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据策略更新失败!");
        }
        return r;
    }
    /**
    * 单条查询系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public CollectionPolicyResVo selectCollectionPolicyById(CollectionPolicyDetailsReqVo vo)
    {
        CollectionPolicy model = new CollectionPolicy();
        BeanUtil.copyProperties(vo,model);
        CollectionPolicy res = this.baseMapper.selectById(model);
        CollectionPolicyResVo resVo = new CollectionPolicyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteCollectionPolicyById(CollectionPolicyDeleteReqVo vo)
    {
        CollectionPolicy model = new CollectionPolicy();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据策略删除失败!");
        }
        return r;
    }
    /**
    * 分页获取系统设置-数据策略结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<CollectionPolicyListResVo>  selectCollectionPolicyPageList(CollectionPolicyListReqVo vo){

        Page<CollectionPolicyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<CollectionPolicyListResVo> results = this.baseMapper.selectCollectionPolicyPageList(page,vo);
        PageResult<CollectionPolicyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }

    /**
     * 获取需要处理的任务策略
     * @param vo 前 limit 条
     * @return 任务策略集合
     */
    @Override
    public List<CollectionPolicy> getPreExecutePolicy(SelectPreExecutePolicyVo vo)
    {
        QueryWrapper<CollectionPolicy> query = new QueryWrapper<>();

        IPage<CollectionPolicy> page = new Page<>(vo.getPageIndex(),vo.getPageSize() <=0 ? 20:vo.getPageSize());
        vo.setRunCount(vo.getRunCount() == null || vo.getRunCount()<=0 ? 10 : vo.getRunCount());
        vo.setPreRunTimeEnd(vo.getPreRunTimeEnd()==null ? new Date() : vo.getPreRunTimeEnd());
        page = this.baseMapper.selectPreExecutePolicy(page,vo);

        return page.getRecords();
    }

    /**
     * 更新异常信息
     * @param policyId
     * @param exMsg
     * @return
     */
    public int updateExMsgAndRunCount(String policyId,String exMsg)
    {
        return this.baseMapper.updateExMsgAndRunCount(policyId,exMsg);
    }

    /**
     * 根据ids查询任务策略列表
     * @param ids
     * @return
     */
    public List<CollectionPolicy> getPolicyByIds(List<String> ids)
    {
        QueryWrapper<CollectionPolicy> query = new QueryWrapper<>();

        query.in("policy_id",ids);

        List<CollectionPolicy> list = this.baseMapper.selectList(query);
        return list;
    }

    /**
     * 手动执行策略
     * @param policyId 策略编号
     */
    public void executePolicyByHand(String policyId)
    {
        CollectionPolicy policy = this.baseMapper.selectById(policyId);
        if(policy==null)
            throw new BusinessException("该策略不存在！");
        taskExecutorHandler.handlerPolicy(policy, CollectionHandType.ByHand);
    }

}
