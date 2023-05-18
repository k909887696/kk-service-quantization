package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.business.quantization.dao.mapper.MaxPctChgMapper;
import com.kk.business.quantization.service.IMaxPctChgService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.MaxPctChgListVo;
import com.kk.business.quantization.model.dto.MaxPctChgListDto;
import com.kk.business.quantization.model.vo.MaxPctChgAddVo;
import com.kk.business.quantization.model.vo.MaxPctChgEditVo;
import com.kk.business.quantization.model.dto.MaxPctChgDto;
import com.kk.business.quantization.model.vo.MaxPctChgDetailsVo;
import com.kk.business.quantization.model.vo.MaxPctChgDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 各个市场涨跌幅限制 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class MaxPctChgServiceImpl extends MppServiceImpl<MaxPctChgMapper, MaxPctChg> implements IMaxPctChgService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<MaxPctChg> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<MaxPctChg> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(MaxPctChgAddVo vo)
    {
        MaxPctChg model = mapperUtils.map(vo,MaxPctChg.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(MaxPctChgEditVo vo)
    {
        MaxPctChg model = mapperUtils.map(vo,MaxPctChg.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("各个市场涨跌幅限制更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public MaxPctChgDto selectById(MaxPctChgDetailsVo vo)
    {
        MaxPctChg model = mapperUtils.map(vo,MaxPctChg.class);
        MaxPctChg res = this.baseMapper.selectByMultiId(model);
        MaxPctChgDto dto = mapperUtils.map(res,MaxPctChgDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(MaxPctChgDeleteVo vo)
    {
        MaxPctChg model = mapperUtils.map(vo,MaxPctChg.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("各个市场涨跌幅限制删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<MaxPctChgListDto>  selectPageList(MaxPctChgListVo vo){

        IPage<MaxPctChgListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<MaxPctChgListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
