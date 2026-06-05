package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalAddReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalEditReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 交易日历 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class TradeCalServiceApi   {

    @Resource
    public ITradeCalService tradeCalService;

    /**
    * 分批批量插入交易日历
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertTradeCalBatchSomeColumn(List<TradeCal> list)
    {
        tradeCalService.insertTradeCalBatchSomeColumn(list);
    }
    /**
    * 单条插入交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertTradeCal(TradeCalAddReqVo vo)
    {
        tradeCalService.insertTradeCal(vo);
    }
    /**
    * 更新交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateTradeCal(TradeCalEditReqVo vo)
    {
        return tradeCalService.updateTradeCal(vo);
    }
    /**
    * 单条查询交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public TradeCalResVo selectTradeCalById(TradeCalDetailsReqVo vo)
    {
        return tradeCalService.selectTradeCalById(vo);
    }
    /**
    * 删除交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteTradeCalById(TradeCalDeleteReqVo vo)
    {
        return tradeCalService.deleteTradeCalById(vo);
    }
    /**
    * 分页获取交易日历结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<TradeCalListResVo>  selectTradeCalPageList(TradeCalListReqVo vo){
        return tradeCalService.selectTradeCalPageList(vo);
    }



}
