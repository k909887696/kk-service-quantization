package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.model.dto.StockDayKdjDto;
import com.kk.business.quantization.model.dto.StockDayKdjListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * kdj数据 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IStockDayKdjService extends IMppService<StockDayKdj> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<StockDayKdj> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<StockDayKdjListDto>  selectPageList(StockDayKdjListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(StockDayKdjAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(StockDayKdjEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    StockDayKdjDto selectById(StockDayKdjDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(StockDayKdjDeleteVo vo);

    /**
     * 根据交易日期删除kdj数据
     * @param tradeDate
     */
    void deleteByTradeDate(String tradeDate);

}
