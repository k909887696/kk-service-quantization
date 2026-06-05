package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexClassify;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业分类 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IIndexClassifyService  {

    /**
    * 分批批量插入申万行业分类
    * @param list 数据列表
    * @return
    */
    void insertIndexClassifyBatchSomeColumn(List<IndexClassify> list);
    /**
    * 单条插入申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    void insertIndexClassify(IndexClassifyAddReqVo vo);
    /**
    * 更新申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    int updateIndexClassify(IndexClassifyEditReqVo vo);
    /**
    * 单条查询申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    IndexClassifyResVo selectIndexClassifyById(IndexClassifyDetailsReqVo vo);
    /**
    * 删除申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteIndexClassifyById(IndexClassifyDeleteReqVo vo);
    /**
    * 分页获取申万行业分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexClassifyListResVo>  selectIndexClassifyPageList(IndexClassifyListReqVo vo);
}

