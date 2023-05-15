package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.vo.SearchDailyVo;
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
    PageResult<Daily> getDailyPageResult(SearchDailyVo vo);

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyKdjDto>  getPageResultEx(SearchDailyVo vo);

}
