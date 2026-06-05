package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MarketBasic;
import com.kk.business.quantization.service.IMarketBasicService;
import com.kk.business.quantization.model.vobase.req.MarketBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicListResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.MarketBasicResVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MarketBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 市场基本信息 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MarketBasicServiceApi   {

    @Resource
    public IMarketBasicService marketBasicService;

    /**
    * 分批批量插入市场基本信息
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertMarketBasicBatchSomeColumn(List<MarketBasic> list)
    {
        marketBasicService.insertMarketBasicBatchSomeColumn(list);
    }
    /**
    * 单条插入市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertMarketBasic(MarketBasicAddReqVo vo)
    {
        marketBasicService.insertMarketBasic(vo);
    }
    /**
    * 更新市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateMarketBasic(MarketBasicEditReqVo vo)
    {
        return marketBasicService.updateMarketBasic(vo);
    }
    /**
    * 单条查询市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public MarketBasicResVo selectMarketBasicById(MarketBasicDetailsReqVo vo)
    {
        return marketBasicService.selectMarketBasicById(vo);
    }
    /**
    * 删除市场基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteMarketBasicById(MarketBasicDeleteReqVo vo)
    {
        return marketBasicService.deleteMarketBasicById(vo);
    }
    /**
    * 分页获取市场基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<MarketBasicListResVo>  selectMarketBasicPageList(MarketBasicListReqVo vo){
        return marketBasicService.selectMarketBasicPageList(vo);
    }



}
