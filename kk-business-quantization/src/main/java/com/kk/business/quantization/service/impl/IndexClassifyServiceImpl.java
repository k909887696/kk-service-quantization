package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.dao.mapper.IndexClassifyMapper;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.IndexClassifyListVo;
import com.kk.business.quantization.model.dto.IndexClassifyListDto;
import com.kk.business.quantization.model.vo.IndexClassifyAddVo;
import com.kk.business.quantization.model.vo.IndexClassifyEditVo;
import com.kk.business.quantization.model.dto.IndexClassifyDto;
import com.kk.business.quantization.model.vo.IndexClassifyDetailsVo;
import com.kk.business.quantization.model.vo.IndexClassifyDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 申万行业分类 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class IndexClassifyServiceImpl extends MppServiceImpl<IndexClassifyMapper, IndexClassify> implements IIndexClassifyService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexClassify> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexClassify> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(IndexClassifyAddVo vo)
    {
        IndexClassify model = mapperUtils.map(vo,IndexClassify.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(IndexClassifyEditVo vo)
    {
        IndexClassify model = mapperUtils.map(vo,IndexClassify.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业分类更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public IndexClassifyDto selectById(IndexClassifyDetailsVo vo)
    {
        IndexClassify model = mapperUtils.map(vo,IndexClassify.class);
        IndexClassify res = this.baseMapper.selectByMultiId(model);
        IndexClassifyDto dto = mapperUtils.map(res,IndexClassifyDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(IndexClassifyDeleteVo vo)
    {
        IndexClassify model = mapperUtils.map(vo,IndexClassify.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业分类删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexClassifyListDto>  selectPageList(IndexClassifyListVo vo){

        IPage<IndexClassifyListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<IndexClassifyListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
