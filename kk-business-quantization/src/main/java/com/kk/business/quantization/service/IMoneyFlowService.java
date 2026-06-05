package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.MoneyFlow;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股资金流向 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IMoneyFlowService  {

    /**
    * 分批批量插入个股资金流向
    * @param list 数据列表
    * @return
    */
    void insertMoneyFlowBatchSomeColumn(List<MoneyFlow> list);
    /**
    * 单条插入个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    void insertMoneyFlow(MoneyFlowAddReqVo vo);
    /**
    * 更新个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    int updateMoneyFlow(MoneyFlowEditReqVo vo);
    /**
    * 单条查询个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    MoneyFlowResVo selectMoneyFlowById(MoneyFlowDetailsReqVo vo);
    /**
    * 删除个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteMoneyFlowById(MoneyFlowDeleteReqVo vo);
    /**
    * 分页获取个股资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MoneyFlowListResVo>  selectMoneyFlowPageList(MoneyFlowListReqVo vo);
}

