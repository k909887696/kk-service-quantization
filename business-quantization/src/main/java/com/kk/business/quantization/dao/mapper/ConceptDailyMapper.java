package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.model.DailyLeader;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

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
    public List<DailyLeader> selectLeaderListByRange(SearchDailyLeaderVo vo);

}
