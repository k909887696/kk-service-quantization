package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexDaily;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IIndexDailyService  {

    /**
    * 分批批量插入指数日线行情
    * @param list 数据列表
    * @return
    */
    void insertIndexDailyBatchSomeColumn(List<IndexDaily> list);
    /**
    * 单条插入指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    void insertIndexDaily(IndexDailyAddReqVo vo);
    /**
    * 更新指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int updateIndexDaily(IndexDailyEditReqVo vo);
    /**
    * 单条查询指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    IndexDailyResVo selectIndexDailyById(IndexDailyDetailsReqVo vo);
    /**
    * 删除指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteIndexDailyById(IndexDailyDeleteReqVo vo);
    /**
    * 分页获取指数日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexDailyListResVo>  selectIndexDailyPageList(IndexDailyListReqVo vo);
}

