package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.dao.mapper.TradeCalMapper;
import com.kk.business.quantization.model.dto.TradeCalDto;
import com.kk.business.quantization.model.dto.TradeCalListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.ITradeCalService;
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
 * 交易日历 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class TradeCalServiceImpl extends MppServiceImpl<TradeCalMapper, TradeCal> implements ITradeCalService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<TradeCal> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<TradeCal> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(TradeCalAddVo vo)
    {
        TradeCal model = mapperUtils.map(vo,TradeCal.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(TradeCalEditVo vo)
    {
        TradeCal model = mapperUtils.map(vo,TradeCal.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("交易日历更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public TradeCalDto selectById(TradeCalDetailsVo vo)
    {
        TradeCal model = mapperUtils.map(vo,TradeCal.class);
        TradeCal res = this.baseMapper.selectByMultiId(model);
        TradeCalDto dto = mapperUtils.map(res,TradeCalDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(TradeCalDeleteVo vo)
    {
        TradeCal model = mapperUtils.map(vo,TradeCal.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("交易日历删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<TradeCalListDto>  selectPageList(TradeCalListVo vo){

        IPage<TradeCalListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<TradeCalListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 根据日期获取日期最近以往开市日期
     * @param date 日期
     * @param limit 天数
     * @param order 默认asc 升序，desc 降序
     * @return
     */
    public TradeCal getRecentlyOpenByDay(String date,int limit,String order)
    {
        LambdaQueryWrapper<TradeCal> query = new LambdaQueryWrapper<>();
        IPage<TradeCal> page = new Page<>(1,limit<=0?1:limit);

        //这里开始编写查询条件
        query.le(TradeCal::getCalDate,date);
        query.eq(TradeCal::getIsOpen,1);
        query.orderByDesc(TradeCal::getCalDate);

        page = baseMapper.selectPage(page,query);


        if(page.getRecords()!=null && page.getRecords().size() > 0) {
            if("desc".equals(order)) {
                return page.getRecords().get(0);
            }else{
                return page.getRecords().get(page.getRecords().size()-1);
            }
        }else {
            return null;
        }
    }


}
