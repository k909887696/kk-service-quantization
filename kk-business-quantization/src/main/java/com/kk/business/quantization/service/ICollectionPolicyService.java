package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.model.vo.SearchPolicyListVo;
import com.kk.business.quantization.model.vo.SearchPolicyVo;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 数据策略 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface ICollectionPolicyService extends IMppService<CollectionPolicy> {

    /**
     * 查询单个实体
     * @param vo
     * @return
     */
    CollectionPolicy  getPolicy(SearchPolicyVo vo);
    /**
     * 新增数据
     * @param vo 数据
     * @return
     */
    String insert(CollectionPolicy vo);

    /**
     * 更新数据
     * @param vo
     * @return
     */
    String update(CollectionPolicy vo);
    /**
     * 更新异常信息
     * @param policyId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String policyId,String exMsg);
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<CollectionPolicy> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CollectionPolicy> getCollectionPolicyPageResult(SearchPolicyListVo vo);

    /**
     * 获取需要处理的任务策略
     * @param limit 前 limit 条
     * @return 任务策略集合
     */
    List<CollectionPolicy> getPreExecutePolicy(int limit);

    /**
     * 根据ids获取任务策略列表
     * @param ids
     * @return
     */
    List<CollectionPolicy> getPolicyByIds(List<String> ids);

}
