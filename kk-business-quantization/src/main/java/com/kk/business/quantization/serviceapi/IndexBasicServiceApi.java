package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.service.IIndexBasicService;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 指数基本信息 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexBasicServiceApi   {

    @Resource
    public IIndexBasicService indexBasicService;

    /**
    * 分批批量插入指数基本信息
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertIndexBasicBatchSomeColumn(List<IndexBasic> list)
    {
        indexBasicService.insertIndexBasicBatchSomeColumn(list);
    }
    /**
    * 单条插入指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertIndexBasic(IndexBasicAddReqVo vo)
    {
        indexBasicService.insertIndexBasic(vo);
    }
    /**
    * 更新指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateIndexBasic(IndexBasicEditReqVo vo)
    {
        return indexBasicService.updateIndexBasic(vo);
    }
    /**
    * 单条查询指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public IndexBasicResVo selectIndexBasicById(IndexBasicDetailsReqVo vo)
    {
        return indexBasicService.selectIndexBasicById(vo);
    }
    /**
    * 删除指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteIndexBasicById(IndexBasicDeleteReqVo vo)
    {
        return indexBasicService.deleteIndexBasicById(vo);
    }
    /**
    * 分页获取指数基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<IndexBasicListResVo>  selectIndexBasicPageList(IndexBasicListReqVo vo){
        return indexBasicService.selectIndexBasicPageList(vo);
    }



}
