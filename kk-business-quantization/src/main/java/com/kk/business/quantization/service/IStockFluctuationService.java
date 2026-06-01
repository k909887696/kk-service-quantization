package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.StockFluctuationListVo;
import com.kk.business.quantization.model.dto.StockFluctuationListDto;
import com.kk.business.quantization.model.vo.StockFluctuationAddVo;
import com.kk.business.quantization.model.vo.StockFluctuationEditVo;
import com.kk.business.quantization.model.dto.StockFluctuationDto;
import com.kk.business.quantization.model.vo.StockFluctuationDetailsVo;
import com.kk.business.quantization.model.vo.StockFluctuationDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股异常波动信息 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IStockFluctuationService extends IMppService<StockFluctuation> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<StockFluctuation> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<StockFluctuationListDto>  selectPageList(StockFluctuationListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(StockFluctuationAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(StockFluctuationEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    StockFluctuationDto selectById(StockFluctuationDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(StockFluctuationDeleteVo vo);

}
