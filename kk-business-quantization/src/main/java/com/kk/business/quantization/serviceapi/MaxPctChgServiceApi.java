package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.business.quantization.service.IMaxPctChgService;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgAddReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgEditReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 各个市场涨跌幅限制 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MaxPctChgServiceApi   {

    @Resource
    public IMaxPctChgService maxPctChgService;

    /**
    * 分批批量插入各个市场涨跌幅限制
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertMaxPctChgBatchSomeColumn(List<MaxPctChg> list)
    {
        maxPctChgService.insertMaxPctChgBatchSomeColumn(list);
    }
    /**
    * 单条插入各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertMaxPctChg(MaxPctChgAddReqVo vo)
    {
        maxPctChgService.insertMaxPctChg(vo);
    }
    /**
    * 更新各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateMaxPctChg(MaxPctChgEditReqVo vo)
    {
        return maxPctChgService.updateMaxPctChg(vo);
    }
    /**
    * 单条查询各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public MaxPctChgResVo selectMaxPctChgById(MaxPctChgDetailsReqVo vo)
    {
        return maxPctChgService.selectMaxPctChgById(vo);
    }
    /**
    * 删除各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteMaxPctChgById(MaxPctChgDeleteReqVo vo)
    {
        return maxPctChgService.deleteMaxPctChgById(vo);
    }
    /**
    * 分页获取各个市场涨跌幅限制结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<MaxPctChgListResVo>  selectMaxPctChgPageList(MaxPctChgListReqVo vo){
        return maxPctChgService.selectMaxPctChgPageList(vo);
    }



}
