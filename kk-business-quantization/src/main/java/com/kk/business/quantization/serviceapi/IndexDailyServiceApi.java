package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.business.quantization.service.IIndexDailyService;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 指数日线行情 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexDailyServiceApi   {

    @Resource
    public IIndexDailyService indexDailyService;

    /**
    * 分批批量插入指数日线行情
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertIndexDailyBatchSomeColumn(List<IndexDaily> list)
    {
        indexDailyService.insertIndexDailyBatchSomeColumn(list);
    }
    /**
    * 单条插入指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertIndexDaily(IndexDailyAddReqVo vo)
    {
        indexDailyService.insertIndexDaily(vo);
    }
    /**
    * 更新指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateIndexDaily(IndexDailyEditReqVo vo)
    {
        return indexDailyService.updateIndexDaily(vo);
    }
    /**
    * 单条查询指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public IndexDailyResVo selectIndexDailyById(IndexDailyDetailsReqVo vo)
    {
        return indexDailyService.selectIndexDailyById(vo);
    }
    /**
    * 删除指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteIndexDailyById(IndexDailyDeleteReqVo vo)
    {
        return indexDailyService.deleteIndexDailyById(vo);
    }
    /**
    * 分页获取指数日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<IndexDailyListResVo>  selectIndexDailyPageList(IndexDailyListReqVo vo){
        return indexDailyService.selectIndexDailyPageList(vo);
    }



}
