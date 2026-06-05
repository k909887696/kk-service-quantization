package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;

import java.util.List;

/**
 * <p>
 * 概念明细 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ConceptDetailMapper extends RootMapper<ConceptDetail> {
     /**
     * 查询概念明细列表
     */
     Page<ConceptDetailListResVo> selectConceptDetailPageList(Page page, ConceptDetailListReqVo conceptDetailListReqVo);
     /**
      * 根据概念id更新股票代码
      * @param conceptIds
      * @return
      *
      * */
     int updateSymbolByConceptIds(List<String> conceptIds);

     /**
      * 根据概念id更新股票代码symbol
      * @param conceptIds
      * @return
      */
     int updateTsCodeByConceptIds(List<String> conceptIds);

     /**
      * 根据概念id更新概念名称
      * @param conceptIds
      * @return
      */
     int updateConceptNameByConceptIds(List<String> conceptIds);
}
