package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.dao.mapper.StockBasicMapper;
import com.kk.business.quantization.model.dto.StockBasicDto;
import com.kk.business.quantization.model.dto.StockBasicListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.IStockBasicService;
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
 * 个股基本信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class StockBasicServiceImpl extends MppServiceImpl<StockBasicMapper, StockBasic> implements IStockBasicService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<StockBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<StockBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(StockBasicAddVo vo)
    {
        StockBasic model = mapperUtils.map(vo,StockBasic.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(StockBasicEditVo vo)
    {
        StockBasic model = mapperUtils.map(vo,StockBasic.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("个股基本信息更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public StockBasicDto selectById(StockBasicDetailsVo vo)
    {
        StockBasic model = mapperUtils.map(vo,StockBasic.class);
        StockBasic res = this.baseMapper.selectByMultiId(model);
        StockBasicDto dto = mapperUtils.map(res,StockBasicDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(StockBasicDeleteVo vo)
    {
        StockBasic model = mapperUtils.map(vo,StockBasic.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股基本信息删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<StockBasicListDto>  selectPageList(StockBasicListVo vo){

        IPage<StockBasicListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<StockBasicListDto>  pageResult = new PageResult<>();

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
    public PageResult<StockBasic>  getStockBasicPageResult(BasePage vo){

        QueryWrapper<StockBasic> query = new QueryWrapper<>();
        IPage<StockBasic> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件

        page = this.baseMapper.selectPage(page,query);
        PageResult<StockBasic>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
