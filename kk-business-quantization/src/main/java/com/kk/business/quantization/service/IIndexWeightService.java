package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexWeight;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.IndexWeightListVo;
import com.kk.business.quantization.model.dto.IndexWeightListDto;
import com.kk.business.quantization.model.vo.IndexWeightAddVo;
import com.kk.business.quantization.model.vo.IndexWeightEditVo;
import com.kk.business.quantization.model.dto.IndexWeightDto;
import com.kk.business.quantization.model.vo.IndexWeightDetailsVo;
import com.kk.business.quantization.model.vo.IndexWeightDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数成分权重 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
public interface IIndexWeightService extends IMppService<IndexWeight> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<IndexWeight> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexWeightListDto>  selectPageList(IndexWeightListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(IndexWeightAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(IndexWeightEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    IndexWeightDto selectById(IndexWeightDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(IndexWeightDeleteVo vo);

}
