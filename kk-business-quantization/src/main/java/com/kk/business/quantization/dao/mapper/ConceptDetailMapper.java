package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.model.vo.ConceptDetailListVo;
import com.kk.common.dao.mapper.RootMapper;

import java.util.List;

/**
 * <p>
 * 概念明细 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-17
 */

public interface ConceptDetailMapper extends RootMapper<ConceptDetail> {
    /**
     * 查询列表
     */
    Page selectPageList(IPage page, ConceptDetailListVo conceptDetailListVo);
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
