package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.dao.mapper.DailyMapper;
import com.kk.business.quantization.model.DailyKdj;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.service.IDailyService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 个股日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class DailyServiceImpl extends MppServiceImpl<DailyMapper, Daily> implements IDailyService {

    @Resource
    public DailyMapper mapper;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<Daily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Daily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<Daily>  getDailyPageResult(SearchDailyVo vo){

        LambdaQueryWrapper<Daily> query = new LambdaQueryWrapper<>();
        IPage<Daily> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件
        if(StringUtils.isNotBlank(vo.getTsCode()))
        {
            query.eq(Daily::getTsCode,vo.getTsCode());
        }

        if(StringUtils.isNotBlank(vo.getTradeDate()))
        {
            query.eq(Daily::getTradeDate,vo.getTradeDate());
        }

        if(StringUtils.isNotBlank(vo.getStartDate()))
        {
            query.ge(Daily::getTradeDate,vo.getStartDate());
        }

        if(StringUtils.isNotBlank(vo.getTsCode()))
        {
            query.le(Daily::getTradeDate,vo.getEndDate());
        }

        if(vo.getIds()!=null && vo.getIds().size()>0)
        {
            query.in(Daily::getTsCode,vo.getIds());
        }


        page = mapper.selectPage(page,query);
        PageResult<Daily>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<DailyKdj>  getPageResultEx(SearchDailyVo vo) {


        IPage<DailyKdj> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        page = mapper.selectDailyExList(page,vo);
        PageResult<DailyKdj>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }


}