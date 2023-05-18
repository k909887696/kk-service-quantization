package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.kk.business.quantization.dao.mapper.StockFluctuationMapper;
import com.kk.business.quantization.service.IStockFluctuationService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.StockFluctuationListVo;
import com.kk.business.quantization.model.dto.StockFluctuationListDto;
import com.kk.business.quantization.model.vo.StockFluctuationAddVo;
import com.kk.business.quantization.model.vo.StockFluctuationEditVo;
import com.kk.business.quantization.model.dto.StockFluctuationDto;
import com.kk.business.quantization.model.vo.StockFluctuationDetailsVo;
import com.kk.business.quantization.model.vo.StockFluctuationDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股异常波动信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class StockFluctuationServiceImpl extends MppServiceImpl<StockFluctuationMapper, StockFluctuation> implements IStockFluctuationService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<StockFluctuation> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<StockFluctuation> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(StockFluctuationAddVo vo)
    {
        StockFluctuation model = mapperUtils.map(vo,StockFluctuation.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(StockFluctuationEditVo vo)
    {
        StockFluctuation model = mapperUtils.map(vo,StockFluctuation.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股异常波动信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public StockFluctuationDto selectById(StockFluctuationDetailsVo vo)
    {
        StockFluctuation model = mapperUtils.map(vo,StockFluctuation.class);
        StockFluctuation res = this.baseMapper.selectByMultiId(model);
        StockFluctuationDto dto = mapperUtils.map(res,StockFluctuationDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(StockFluctuationDeleteVo vo)
    {
        StockFluctuation model = mapperUtils.map(vo,StockFluctuation.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股异常波动信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<StockFluctuationListDto>  selectPageList(StockFluctuationListVo vo){

        IPage<StockFluctuationListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<StockFluctuationListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
