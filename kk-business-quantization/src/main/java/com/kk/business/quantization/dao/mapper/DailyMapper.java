package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.vo.DailyListVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.model.vo.SelectMaxMinByDateRangeVo;
import com.kk.common.dao.mapper.RootMapper;


/**
 * <p>
 *  个股日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-08
 */

public interface DailyMapper extends RootMapper<Daily> {

    /**
     * 查询日线行情拓展信息（分页）
     * @param vo
     * @param page
     * @return
     */
    Page selectDailyExList(IPage page, SearchDailyVo vo);

    /**
     * 查询列表
     */
    Page selectPageList(IPage page, DailyListVo dailyListVo);

    /**
     * 查询区间内股票最大最小收盘价
     * @param page
     * @param vo
     * @return
     */
    Page selectMaxMinByDateRange(IPage page, SelectMaxMinByDateRangeVo vo);

}
