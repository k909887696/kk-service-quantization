package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.business.quantization.dao.mapper.MonthlyMapper;
import com.kk.business.quantization.service.IMonthlyService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.MonthlyListVo;
import com.kk.business.quantization.model.dto.MonthlyListDto;
import com.kk.business.quantization.model.vo.MonthlyAddVo;
import com.kk.business.quantization.model.vo.MonthlyEditVo;
import com.kk.business.quantization.model.dto.MonthlyDto;
import com.kk.business.quantization.model.vo.MonthlyDetailsVo;
import com.kk.business.quantization.model.vo.MonthlyDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股月线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class MonthlyServiceImpl extends MppServiceImpl<MonthlyMapper, Monthly> implements IMonthlyService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<Monthly> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Monthly> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(MonthlyAddVo vo)
    {
        Monthly model = mapperUtils.map(vo,Monthly.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(MonthlyEditVo vo)
    {
        Monthly model = mapperUtils.map(vo,Monthly.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股月线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public MonthlyDto selectById(MonthlyDetailsVo vo)
    {
        Monthly model = mapperUtils.map(vo,Monthly.class);
        Monthly res = this.baseMapper.selectByMultiId(model);
        MonthlyDto dto = mapperUtils.map(res,MonthlyDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(MonthlyDeleteVo vo)
    {
        Monthly model = mapperUtils.map(vo,Monthly.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股月线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<MonthlyListDto>  selectPageList(MonthlyListVo vo){

        IPage<MonthlyListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<MonthlyListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
