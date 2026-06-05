package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.CollectionPolicy;
import java.util.List;

import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据策略 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ICollectionPolicyService  {

    /**
    * 分批批量插入系统设置-数据策略
    * @param list 数据列表
    * @return
    */
    void insertCollectionPolicyBatchSomeColumn(List<CollectionPolicy> list);
    /**
    * 单条插入系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    void insertCollectionPolicy(CollectionPolicyAddReqVo vo);
    /**
    * 更新系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    int updateCollectionPolicy(CollectionPolicyEditReqVo vo);
    /**
    * 单条查询系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    CollectionPolicyResVo selectCollectionPolicyById(CollectionPolicyDetailsReqVo vo);
    /**
    * 删除系统设置-数据策略
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteCollectionPolicyById(CollectionPolicyDeleteReqVo vo);
    /**
    * 分页获取系统设置-数据策略结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CollectionPolicyListResVo>  selectCollectionPolicyPageList(CollectionPolicyListReqVo vo);

    /**
     * 获取需要处理的任务策略
     * @param vo 请求参数
     * @return 任务策略集合
     */
    List<CollectionPolicy> getPreExecutePolicy(SelectPreExecutePolicyVo vo);

    /**
     * 根据ids获取任务策略列表
     * @param ids
     * @return
     */
    List<CollectionPolicy> getPolicyByIds(List<String> ids);

    /**
     * 手动执行策略
     * @param policyId 策略编号
     */
    void executePolicyByHand(String policyId);

    /**
     * 更新异常信息
     * @param policyId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String policyId,String exMsg);
}

