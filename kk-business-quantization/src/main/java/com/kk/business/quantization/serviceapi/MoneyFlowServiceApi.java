package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.business.quantization.service.IMoneyFlowService;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个股资金流向 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MoneyFlowServiceApi   {

    @Resource
    public IMoneyFlowService moneyFlowService;

    /**
    * 分批批量插入个股资金流向
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertMoneyFlowBatchSomeColumn(List<MoneyFlow> list)
    {
        moneyFlowService.insertMoneyFlowBatchSomeColumn(list);
    }
    /**
    * 单条插入个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertMoneyFlow(MoneyFlowAddReqVo vo)
    {
        moneyFlowService.insertMoneyFlow(vo);
    }
    /**
    * 更新个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateMoneyFlow(MoneyFlowEditReqVo vo)
    {
        return moneyFlowService.updateMoneyFlow(vo);
    }
    /**
    * 单条查询个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public MoneyFlowResVo selectMoneyFlowById(MoneyFlowDetailsReqVo vo)
    {
        return moneyFlowService.selectMoneyFlowById(vo);
    }
    /**
    * 删除个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteMoneyFlowById(MoneyFlowDeleteReqVo vo)
    {
        return moneyFlowService.deleteMoneyFlowById(vo);
    }
    /**
    * 分页获取个股资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<MoneyFlowListResVo>  selectMoneyFlowPageList(MoneyFlowListReqVo vo){
        return moneyFlowService.selectMoneyFlowPageList(vo);
    }



}
