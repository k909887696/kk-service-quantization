package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexWeight;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数成分权重 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IIndexWeightService  {

    /**
    * 分批批量插入指数成分权重
    * @param list 数据列表
    * @return
    */
    void insertIndexWeightBatchSomeColumn(List<IndexWeight> list);
    /**
    * 单条插入指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    void insertIndexWeight(IndexWeightAddReqVo vo);
    /**
    * 更新指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    int updateIndexWeight(IndexWeightEditReqVo vo);
    /**
    * 单条查询指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    IndexWeightResVo selectIndexWeightById(IndexWeightDetailsReqVo vo);
    /**
    * 删除指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteIndexWeightById(IndexWeightDeleteReqVo vo);
    /**
    * 分页获取指数成分权重结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexWeightListResVo>  selectIndexWeightPageList(IndexWeightListReqVo vo);
}

