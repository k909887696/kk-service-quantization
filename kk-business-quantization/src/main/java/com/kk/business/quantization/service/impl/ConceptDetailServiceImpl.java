package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.dao.mapper.ConceptDetailMapper;
import com.kk.business.quantization.service.IConceptDetailService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.ConceptDetailListVo;
import com.kk.business.quantization.model.dto.ConceptDetailListDto;
import com.kk.business.quantization.model.vo.ConceptDetailAddVo;
import com.kk.business.quantization.model.vo.ConceptDetailEditVo;
import com.kk.business.quantization.model.dto.ConceptDetailDto;
import com.kk.business.quantization.model.vo.ConceptDetailDetailsVo;
import com.kk.business.quantization.model.vo.ConceptDetailDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念明细 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Service
public class ConceptDetailServiceImpl extends MppServiceImpl<ConceptDetailMapper, ConceptDetail> implements IConceptDetailService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<ConceptDetail> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptDetail> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(ConceptDetailAddVo vo)
    {
        ConceptDetail model = mapperUtils.map(vo,ConceptDetail.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(ConceptDetailEditVo vo)
    {
        ConceptDetail model = mapperUtils.map(vo,ConceptDetail.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念明细更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public ConceptDetailDto selectById(ConceptDetailDetailsVo vo)
    {
        ConceptDetail model = mapperUtils.map(vo,ConceptDetail.class);
        ConceptDetail res = this.baseMapper.selectByMultiId(model);
        ConceptDetailDto dto = mapperUtils.map(res,ConceptDetailDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(ConceptDetailDeleteVo vo)
    {
        ConceptDetail model = mapperUtils.map(vo,ConceptDetail.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念明细删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<ConceptDetailListDto>  selectPageList(ConceptDetailListVo vo){

        IPage<ConceptDetailListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<ConceptDetailListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
