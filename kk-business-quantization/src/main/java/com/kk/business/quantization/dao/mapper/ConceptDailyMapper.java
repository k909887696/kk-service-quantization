package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.common.dao.mapper.RootMapper;

import java.util.List;

/**
 * <p>
 * 概念日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-17
 */

public interface ConceptDailyMapper extends RootMapper<ConceptDaily> {


    /**
     * 获取区间涨幅最好的概念
     * @param vo
     * @return
     */
    public List<DailyLeaderDto> selectLeaderListByRange(SearchDailyLeaderVo vo);

}
