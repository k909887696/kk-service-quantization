package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.dao.mapper.DailyMapper;
import com.kk.business.quantization.model.dto.DailyDto;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.dto.DailyListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.IDailyService;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.MapperUtils;
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
    public MapperUtils mapperUtils;
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
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(DailyAddVo vo)
    {
        Daily model = mapperUtils.map(vo,Daily.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(DailyEditVo vo)
    {
        Daily model = mapperUtils.map(vo,Daily.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股日线行情更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public DailyDto selectById(DailyDetailsVo vo)
    {
        Daily model = mapperUtils.map(vo,Daily.class);
        Daily res = this.baseMapper.selectByMultiId(model);
        DailyDto dto = mapperUtils.map(res,DailyDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(DailyDeleteVo vo)
    {
        Daily model = mapperUtils.map(vo,Daily.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股日线行情删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<DailyListDto>  selectPageList(DailyListVo vo){

        IPage<DailyListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<DailyListDto>  pageResult = new PageResult<>();

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
    public PageResult<DailyKdjDto>  getPageResultEx(SearchDailyVo vo) {


        IPage<DailyKdjDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        page = this.baseMapper.selectDailyExList(page,vo);
        PageResult<DailyKdjDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }

    /**
     * 获取区间涨幅最好的概念
     * @param vo
     * @return
     */
    public PageResult<DailyLeaderDto> selectStockLeader(SearchDailyLeaderVo vo)
    {
        IPage<DailyLeaderDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        page = this.baseMapper.selectStockLeader(page,vo);
        PageResult<DailyLeaderDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }

}
