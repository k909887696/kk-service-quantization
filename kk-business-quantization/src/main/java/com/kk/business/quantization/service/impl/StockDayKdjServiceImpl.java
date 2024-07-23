package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.dao.mapper.StockDayKdjMapper;
import com.kk.business.quantization.model.dto.StockDayKdjDto;
import com.kk.business.quantization.model.dto.StockDayKdjListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.IStockDayKdjService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.MapperUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * kdj数据 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class StockDayKdjServiceImpl extends MppServiceImpl<StockDayKdjMapper, StockDayKdj> implements IStockDayKdjService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<StockDayKdj> list)
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
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(StockDayKdjAddVo vo)
    {
        StockDayKdj model = mapperUtils.map(vo,StockDayKdj.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(StockDayKdjEditVo vo)
    {
        StockDayKdj model = mapperUtils.map(vo,StockDayKdj.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股kdj数据更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public StockDayKdjDto selectById(StockDayKdjDetailsVo vo)
    {
        StockDayKdj model = mapperUtils.map(vo,StockDayKdj.class);
        StockDayKdj res = this.baseMapper.selectByMultiId(model);
        StockDayKdjDto dto = mapperUtils.map(res,StockDayKdjDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(StockDayKdjDeleteVo vo)
    {
        StockDayKdj model = mapperUtils.map(vo,StockDayKdj.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股kdj数据删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<StockDayKdjListDto>  selectPageList(StockDayKdjListVo vo){

        IPage<StockDayKdjListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<StockDayKdjListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 根据交易日期删除kdj数据
     * @param tradeDate
     */
    public void deleteByTradeDate(String tradeDate)
    {
        LambdaQueryWrapper<StockDayKdj> query = new LambdaQueryWrapper<>();
        query.eq(StockDayKdj::getTradeDate,tradeDate);
        baseMapper.delete(query);
    }



}
