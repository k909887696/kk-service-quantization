package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CollectionPolicy;
import com.kk.business.quantization.model.vo.SelectPreExecutePolicyVo;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.CollectionPolicyListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionPolicyListReqVo;

import java.util.Date;

/**
 * <p>
 * 系统设置-数据策略 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface CollectionPolicyMapper extends RootMapper<CollectionPolicy> {
     /**
     * 查询系统设置-数据策略列表
     */
     Page<CollectionPolicyListResVo> selectCollectionPolicyPageList(Page page, CollectionPolicyListReqVo collectionPolicyListReqVo);

     /**
      * 查询需要执行策略
      * @param page
      * @param vo
      * @return
      */
     IPage<CollectionPolicy> selectPreExecutePolicy(IPage page, SelectPreExecutePolicyVo vo);
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
