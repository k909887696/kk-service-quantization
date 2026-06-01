package com.kk.business.quantization.third;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.po.dfcf.DfcfConceptDailyVo;
import com.kk.business.quantization.model.po.dfcf.DfcfConceptMoneyFlowVo;
import com.kk.business.quantization.model.po.dfcf.DfcfData;
import com.kk.business.quantization.model.po.tushare.ConceptDetailVo;
import com.kk.business.quantization.model.po.tushare.ConceptVo;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:22
 *  对接 东方财富 数据接口
 */
public interface IDfcfDataApi {


    /**
     * 获取概念信息
     * @param vo
     * @return
     */
    DfcfData<Concept> concept(ConceptVo vo);

    /**
     * 获取概念明细信息
     * @param vo
     * @return
     */
    DfcfData<ConceptDetail> conceptDetail(ConceptDetailVo vo);

    /**
     * 获取东方财富概念日线行情
     * @param vo
     * @return
     */
    DfcfData<ConceptDaily> conceptDaily(DfcfConceptDailyVo vo);

    /**
     * 获取东方财富概念资金流向
     * @param vo
     * @return
     */
    DfcfData<ConceptMoneyFlow> conceptMoneyFlow(DfcfConceptMoneyFlowVo vo);

}
