package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.model.vo.CollectionPolicyListVo;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * <p>
 * 数据策略 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-16
 */

public interface CollectionPolicyMapper extends RootMapper<CollectionPolicy> {
    /**
     * 查询列表
     */
    Page selectPageList(IPage page, CollectionPolicyListVo collectionPolicyListVo);

    /**
     * 查询需要执行策略
     * @param page
     * @param vo
     * @return
     */
    Page selectPreExecutePolicy(IPage page, SelectPreExecutePolicyVo vo);
    /**
     * 更新策略异常信息
     * @param policyId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String policyId,String exMsg);

    /**
     * 更新策略下次执行时间
     * @param policyId
     * @param preRunTime
     * @return
     */
    int updatePreRunTime(String policyId, Date preRunTime);


}
