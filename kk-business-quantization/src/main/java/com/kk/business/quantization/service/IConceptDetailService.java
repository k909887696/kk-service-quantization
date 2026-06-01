package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.ConceptDetailListVo;
import com.kk.business.quantization.model.dto.ConceptDetailListDto;
import com.kk.business.quantization.model.vo.ConceptDetailAddVo;
import com.kk.business.quantization.model.vo.ConceptDetailEditVo;
import com.kk.business.quantization.model.dto.ConceptDetailDto;
import com.kk.business.quantization.model.vo.ConceptDetailDetailsVo;
import com.kk.business.quantization.model.vo.ConceptDetailDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念明细 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IConceptDetailService extends IMppService<ConceptDetail> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<ConceptDetail> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptDetailListDto>  selectPageList(ConceptDetailListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(ConceptDetailAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(ConceptDetailEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptDetailDto selectById(ConceptDetailDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(ConceptDetailDeleteVo vo);

}
