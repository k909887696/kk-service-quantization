package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowListVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowListDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowAddVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowEditVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDetailsVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念资金流向 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IConceptMoneyFlowService extends IMppService<ConceptMoneyFlow> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<ConceptMoneyFlow> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<ConceptMoneyFlowListDto>  selectPageList(ConceptMoneyFlowListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(ConceptMoneyFlowAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(ConceptMoneyFlowEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    ConceptMoneyFlowDto selectById(ConceptMoneyFlowDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(ConceptMoneyFlowDeleteVo vo);

}
