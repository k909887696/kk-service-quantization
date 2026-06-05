package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.business.quantization.service.IIndexWeightService;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 指数成分权重 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexWeightServiceApi   {

    @Resource
    public IIndexWeightService indexWeightService;

    /**
    * 分批批量插入指数成分权重
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertIndexWeightBatchSomeColumn(List<IndexWeight> list)
    {
        indexWeightService.insertIndexWeightBatchSomeColumn(list);
    }
    /**
    * 单条插入指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertIndexWeight(IndexWeightAddReqVo vo)
    {
        indexWeightService.insertIndexWeight(vo);
    }
    /**
    * 更新指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateIndexWeight(IndexWeightEditReqVo vo)
    {
        return indexWeightService.updateIndexWeight(vo);
    }
    /**
    * 单条查询指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public IndexWeightResVo selectIndexWeightById(IndexWeightDetailsReqVo vo)
    {
        return indexWeightService.selectIndexWeightById(vo);
    }
    /**
    * 删除指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteIndexWeightById(IndexWeightDeleteReqVo vo)
    {
        return indexWeightService.deleteIndexWeightById(vo);
    }
    /**
    * 分页获取指数成分权重结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<IndexWeightListResVo>  selectIndexWeightPageList(IndexWeightListReqVo vo){
        return indexWeightService.selectIndexWeightPageList(vo);
    }



}
