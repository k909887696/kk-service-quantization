package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.model.po.tushare.ConceptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.Concept;
import com.kk.business.quantization.dao.mapper.ConceptMapper;
import com.kk.business.quantization.service.IConceptService;
import com.kk.business.quantization.model.vobase.req.ConceptListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念分类 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptServiceImpl extends ServiceImpl<ConceptMapper, Concept> implements IConceptService {


    /**
    * 分批批量插入概念分类
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertConceptBatchSomeColumn(List<Concept> list)
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
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertConcept(ConceptAddReqVo vo)
    {
        Concept model = new Concept();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateConcept(ConceptEditReqVo vo)
    {
        Concept model = new Concept();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("概念分类更新失败!");
        }
        return r;
    }
    /**
    * 单条查询概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ConceptResVo selectConceptById(ConceptDetailsReqVo vo)
    {
        Concept model = new Concept();
        BeanUtil.copyProperties(vo,model);
        Concept res = this.baseMapper.selectById(model);
        ConceptResVo resVo = new ConceptResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除概念分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteConceptById(ConceptDeleteReqVo vo)
    {
        Concept model = new Concept();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("概念分类删除失败!");
        }
        return r;
    }
    /**
    * 分页获取概念分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<ConceptListResVo>  selectConceptPageList(ConceptListReqVo vo){

        Page<ConceptListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<ConceptListResVo> results = this.baseMapper.selectConceptPageList(page,vo);
        PageResult<ConceptListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    @Override
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
