package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.tushare.DailyVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.common.dao.mapper.RootMapper;


/**
 * <p>
 *  Mapper 接口
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

}
