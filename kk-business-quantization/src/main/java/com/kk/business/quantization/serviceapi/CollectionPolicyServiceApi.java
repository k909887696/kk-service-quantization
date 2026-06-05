package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.service.ICollectionPolicyService;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 系统设置-数据策略 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionPolicyServiceApi   {

    @Resource
    public ICollectionPolicyService collectionPolicyService;

    /**
    * 分批批量插入系统设置-数据策略
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertCollectionPolicyBatchSomeColumn(List<CollectionPolicy> list)
    {
        collectionPolicyService.insertCollectionPolicyBatchSomeColumn(list);
    }
    /**
    * 单条插入系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertCollectionPolicy(CollectionPolicyAddReqVo vo)
    {
        collectionPolicyService.insertCollectionPolicy(vo);
    }
    /**
    * 更新系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateCollectionPolicy(CollectionPolicyEditReqVo vo)
    {
        return collectionPolicyService.updateCollectionPolicy(vo);
    }
    /**
    * 单条查询系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public CollectionPolicyResVo selectCollectionPolicyById(CollectionPolicyDetailsReqVo vo)
    {
        return collectionPolicyService.selectCollectionPolicyById(vo);
    }
    /**
    * 删除系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteCollectionPolicyById(CollectionPolicyDeleteReqVo vo)
    {
        return collectionPolicyService.deleteCollectionPolicyById(vo);
    }
    /**
    * 分页获取系统设置-数据策略结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<CollectionPolicyListResVo>  selectCollectionPolicyPageList(CollectionPolicyListReqVo vo){
        return collectionPolicyService.selectCollectionPolicyPageList(vo);
    }



}
