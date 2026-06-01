package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.model.dto.TradeCalDto;
import com.kk.business.quantization.model.dto.TradeCalListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 交易日历 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface ITradeCalService extends IMppService<TradeCal> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<TradeCal> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<TradeCalListDto>  selectPageList(TradeCalListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(TradeCalAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(TradeCalEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    TradeCalDto selectById(TradeCalDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(TradeCalDeleteVo vo);
    /**
     * 根据日期获取日期最近以往开市日期
     * @param date 日期
     * @param limit 天数
     * @param order 默认asc 升序，desc 降序
     * @return
     */
    TradeCal getRecentlyOpenByDay(String date,int limit,String order);
}
