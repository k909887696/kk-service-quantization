package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.dao.mapper.StockDayKdjMapper;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.business.quantization.model.vobase.req.StockDayKdjListReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjListResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockDayKdjResVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockDayKdjDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股kdj数据 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockDayKdjServiceImpl extends ServiceImpl<StockDayKdjMapper, StockDayKdj> implements IStockDayKdjService {


    /**
    * 分批批量插入个股kdj数据
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertStockDayKdjBatchSomeColumn(List<StockDayKdj> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<StockDayKdj> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertStockDayKdj(StockDayKdjAddReqVo vo)
    {
        StockDayKdj model = new StockDayKdj();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateStockDayKdj(StockDayKdjEditReqVo vo)
    {
        StockDayKdj model = new StockDayKdj();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股kdj数据更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public StockDayKdjResVo selectStockDayKdjById(StockDayKdjDetailsReqVo vo)
    {
        StockDayKdj model = new StockDayKdj();
        BeanUtil.copyProperties(vo,model);
        StockDayKdj res = this.baseMapper.selectByMultiId(model);
        StockDayKdjResVo resVo = new StockDayKdjResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股kdj数据
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteStockDayKdjById(StockDayKdjDeleteReqVo vo)
    {
        StockDayKdj model = new StockDayKdj();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股kdj数据删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股kdj数据结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<StockDayKdjListResVo>  selectStockDayKdjPageList(StockDayKdjListReqVo vo){

        Page<StockDayKdjListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<StockDayKdjListResVo> results = this.baseMapper.selectStockDayKdjPageList(page,vo);
        PageResult<StockDayKdjListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
