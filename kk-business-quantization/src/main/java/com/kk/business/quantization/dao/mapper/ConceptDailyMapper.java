package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
/**
 * <p>
 * 概念日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ConceptDailyMapper extends RootMapper<ConceptDaily> {
     /**
     * 查询概念日线行情列表
     */
     Page<ConceptDailyListResVo> selectConceptDailyPageList(Page page, ConceptDailyListReqVo conceptDailyListReqVo);

     /**
      * 获取区间涨幅最好的概念
      * @param vo
      * @return
      */
     Page<DailyLeaderDto> selectConceptLeaderListByRange(IPage page, SearchDailyLeaderVo vo);
}
