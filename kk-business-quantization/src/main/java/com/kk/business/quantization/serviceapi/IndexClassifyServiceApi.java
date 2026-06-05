package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 申万行业分类 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexClassifyServiceApi   {

    @Resource
    public IIndexClassifyService indexClassifyService;

    /**
    * 分批批量插入申万行业分类
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertIndexClassifyBatchSomeColumn(List<IndexClassify> list)
    {
        indexClassifyService.insertIndexClassifyBatchSomeColumn(list);
    }
    /**
    * 单条插入申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertIndexClassify(IndexClassifyAddReqVo vo)
    {
        indexClassifyService.insertIndexClassify(vo);
    }
    /**
    * 更新申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateIndexClassify(IndexClassifyEditReqVo vo)
    {
        return indexClassifyService.updateIndexClassify(vo);
    }
    /**
    * 单条查询申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public IndexClassifyResVo selectIndexClassifyById(IndexClassifyDetailsReqVo vo)
    {
        return indexClassifyService.selectIndexClassifyById(vo);
    }
    /**
    * 删除申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteIndexClassifyById(IndexClassifyDeleteReqVo vo)
    {
        return indexClassifyService.deleteIndexClassifyById(vo);
    }
    /**
    * 分页获取申万行业分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<IndexClassifyListResVo>  selectIndexClassifyPageList(IndexClassifyListReqVo vo){
        return indexClassifyService.selectIndexClassifyPageList(vo);
    }



}
