package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.dto.DailyDto;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.dto.DailyListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 个股日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IDailyService extends IMppService<Daily> {


    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<Daily> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyListDto>  selectPageList(DailyListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(DailyAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(DailyEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    DailyDto selectById(DailyDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(DailyDeleteVo vo);

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyKdjDto>  getPageResultEx(SearchDailyVo vo);
    /**
     * 获取区间涨幅最好的概念
     * @param vo
     * @return
     */
    PageResult<DailyLeaderDto> selectStockLeader(SearchDailyLeaderVo vo);

}
