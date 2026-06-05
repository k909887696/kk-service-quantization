package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexBasic;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IIndexBasicService  {

    /**
    * 分批批量插入指数基本信息
    * @param list 数据列表
    * @return
    */
    void insertIndexBasicBatchSomeColumn(List<IndexBasic> list);
    /**
    * 单条插入指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    void insertIndexBasic(IndexBasicAddReqVo vo);
    /**
    * 更新指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int updateIndexBasic(IndexBasicEditReqVo vo);
    /**
    * 单条查询指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    IndexBasicResVo selectIndexBasicById(IndexBasicDetailsReqVo vo);
    /**
    * 删除指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteIndexBasicById(IndexBasicDeleteReqVo vo);
    /**
    * 分页获取指数基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexBasicListResVo>  selectIndexBasicPageList(IndexBasicListReqVo vo);
}

