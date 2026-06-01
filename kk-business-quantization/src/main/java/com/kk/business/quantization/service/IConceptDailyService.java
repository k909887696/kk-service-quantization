package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.model.dto.ConceptDailyListDto;
import com.kk.business.quantization.model.dto.ConceptDailyDto;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 概念日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IConceptDailyService extends IMppService<ConceptDaily> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<ConceptDaily> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<ConceptDailyListDto>  selectPageList(ConceptDailyListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(ConceptDailyAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(ConceptDailyEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    ConceptDailyDto selectById(ConceptDailyDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(ConceptDailyDeleteVo vo);

    /**
     * 查询区间涨幅最大概念
     * @param vo
     * @return
     */
    PageResult<DailyLeaderDto> selectConceptLeaderListByRange(SearchDailyLeaderVo vo);

}
