package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.dao.mapper.ConceptMapper;
import com.kk.business.quantization.model.dto.ConceptDto;
import com.kk.business.quantization.model.dto.ConceptListDto;
import com.kk.business.quantization.model.po.tushare.ConceptVo;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.IConceptService;
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
 * 股票概念 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class ConceptServiceImpl extends MppServiceImpl<ConceptMapper, Concept> implements IConceptService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<Concept> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Concept> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(ConceptAddVo vo)
    {
        Concept model = mapperUtils.map(vo,Concept.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(ConceptEditVo vo)
    {
        Concept model = mapperUtils.map(vo,Concept.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("概念分类更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public ConceptDto selectById(ConceptDetailsVo vo)
    {
        Concept model = mapperUtils.map(vo,Concept.class);
        Concept res = this.baseMapper.selectById(model);
        ConceptDto dto = mapperUtils.map(res,ConceptDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(ConceptDeleteVo vo)
    {
        Concept model = mapperUtils.map(vo,Concept.class);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("概念分类删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<ConceptListDto>  selectPageList(ConceptListVo vo){

        IPage<ConceptListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<ConceptListDto>  pageResult = new PageResult<>();

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
    public PageResult<Concept>  getConceptPageResult(ConceptVo vo){

        QueryWrapper<Concept> query = new QueryWrapper<>();
        IPage<Concept> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件
        if(StringUtils.isNotBlank(vo.getSrc()))
        {
            query.eq("src",vo.getSrc());
        }
        page = this.baseMapper.selectPage(page,query);
        PageResult<Concept>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
