package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.dao.mapper.CollectionPolicyMapper;
import com.kk.business.quantization.model.vo.PreExecutePolicyVo;
import com.kk.business.quantization.model.vo.SearchPolicyListVo;
import com.kk.business.quantization.model.vo.SearchPolicyVo;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
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
 * 数据策略 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class CollectionPolicyServiceImpl extends MppServiceImpl<CollectionPolicyMapper, CollectionPolicy> implements ICollectionPolicyService {

    @Resource
    public CollectionPolicyMapper collectionPolicyMapper;

    /**
     * 查询单个实体
     * @param vo
     * @return
     */
    public CollectionPolicy  getPolicy(SearchPolicyVo vo){


        //这里开始编写查询条件
        if(vo == null)
            throw new BusinessException("参数错误");
        if(StringUtils.isBlank(vo.getPolicyId()))
            throw new BusinessException("策略编号不能为空");

        return collectionPolicyMapper.selectById(vo.getPolicyId());
    }
    /**
     * 新增数据
     * @param vo 数据
     * @return
     */
    public String insert(CollectionPolicy vo)
    {

        if(vo==null)
            throw new BusinessException("参数错误");

        if(StringUtils.isBlank(vo.getInvokeCode()))
        {
            throw new BusinessException("执行器不能为空！");
        }
        if(StringUtils.isBlank(vo.getName()))
        {
            throw new BusinessException("名称不能为空！");
        }
        if(StringUtils.isBlank(vo.getInvokeCycle()))
        {
            throw new BusinessException("调用周期不能为空！");
        }
        if(vo.getInvokeCycle()==null) {
            vo.setInvokeCycleTime("1");
        }
        vo.setCreateTime(new Date());
        if(StringUtils.isBlank(vo.getInvokeParams()))
        {
            vo.setInvokeParams("{}");
        }

        String serialNo = SerialNoUtil.getSingleNextId(SerialNoType.COLLECTION_POLICY, DateUtil.PATTERN_STANDARD02W);
        vo.setPolicyId(serialNo);
        collectionPolicyMapper.insert(vo);
        return serialNo;

    }


    /**
     * 更新任务策略
     * @param vo
     * @return
     */
    public String update(CollectionPolicy vo)
    {

        if(vo==null)
            throw new BusinessException("参数错误");
        if(StringUtils.isBlank(vo.getPolicyId()))
        {
            throw new BusinessException("策略编号不能为空！");
        }
        CollectionPolicy policy = collectionPolicyMapper.selectById(vo.getPolicyId());
        if(policy==null)
            throw new BusinessException(vo.getPolicyId()+"策略不存在！");
        if(StringUtils.isBlank(vo.getInvokeCode()))
        {
            throw new BusinessException("执行器不能为空！");
        }
        if(StringUtils.isBlank(vo.getName()))
        {
            throw new BusinessException("名称不能为空！");
        }
        if(StringUtils.isBlank(vo.getInvokeCycle()))
        {
            throw new BusinessException("调用周期不能为空！");
        }
        if(vo.getInvokeCycle()==null) {
            vo.setInvokeCycleTime("1");
        }

        if(StringUtils.isBlank(vo.getInvokeParams()))
        {
            vo.setInvokeParams("{}");
        }

        LambdaUpdateWrapper<CollectionPolicy> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(CollectionPolicy::getInvokeCode,vo.getInvokeCode())
                .set(CollectionPolicy::getInvokeCycle,vo.getInvokeCycle())
                .set(CollectionPolicy::getInvokeCycleTime,vo.getInvokeCycleTime())
                .set(CollectionPolicy::getInvokeParams,vo.getInvokeParams())
                .set(CollectionPolicy::getInvokeMethod,vo.getInvokeMethod())
                .set(CollectionPolicy::getName,vo.getName())
                .set(CollectionPolicy::getPreRunTime,vo.getPreRunTime())
                .set(CollectionPolicy::getRunCount,vo.getRunCount())
                .eq(CollectionPolicy::getPolicyId,vo.getPolicyId());
        collectionPolicyMapper.update(null,updateWrapper);
        return vo.getPolicyId();

    }

    /**
    * 更新异常信息
    * @param policyId
    * @param exMsg
    * @return
    */
    public int updateExMsgAndRunCount(String policyId,String exMsg)
    {
        return collectionPolicyMapper.updateExMsgAndRunCount(policyId,exMsg);
    }
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CollectionPolicy> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        List<String> selialNoList = SerialNoUtil.getMultiNextId(SerialNoType.COLLECTION_POLICY, DateUtil.PATTERN_STANDARD06W,10,totalCount);

        for (int i=0;i<totalCount;i++
        ) {
            list.get(i).setPolicyId(selialNoList.get(i));
        }

        for(;index<=totalPage;index++)
        {
            List<CollectionPolicy> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            collectionPolicyMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CollectionPolicy>  getCollectionPolicyPageResult(SearchPolicyListVo vo){

        LambdaQueryWrapper<CollectionPolicy> query = new LambdaQueryWrapper<>();
        IPage<CollectionPolicy> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件

        if(StringUtils.isNotBlank(vo.getInvokeCode()))
        {
            query.eq(CollectionPolicy::getInvokeCode,vo.getInvokeCode());
        }
        if(StringUtils.isNotBlank(vo.getPolicyId()))
        {
            query.eq(CollectionPolicy::getPolicyId,vo.getPolicyId());
        }
        if(StringUtils.isNotBlank(vo.getName()))
        {
            query.like(CollectionPolicy::getName,vo.getName());
        }


        query.orderByDesc(CollectionPolicy::getPolicyId);
        page = collectionPolicyMapper.selectPage(page,query);
        PageResult<CollectionPolicy>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }
    /**
     * 获取需要处理的任务策略
     * @param vo 前 limit 条
     * @return 任务策略集合
     */
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
     * 根据ids查询任务策略列表
     * @param ids
     * @return
     */
    public List<CollectionPolicy> getPolicyByIds(List<String> ids)
    {
        QueryWrapper<CollectionPolicy> query = new QueryWrapper<>();

        query.in("policy_id",ids);

        List<CollectionPolicy> list = collectionPolicyMapper.selectList(query);
        return list;
    }

}
