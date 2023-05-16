package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.model.dto.StockBasicDto;
import com.kk.business.quantization.model.dto.StockBasicListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 个股基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IStockBasicService extends IMppService<StockBasic> {


    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<StockBasic> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<StockBasicListDto>  selectPageList(StockBasicListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(StockBasicAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(StockBasicEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    StockBasicDto selectById(StockBasicDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(StockBasicDeleteVo vo);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<StockBasic> getStockBasicPageResult(BasePage vo);

}
