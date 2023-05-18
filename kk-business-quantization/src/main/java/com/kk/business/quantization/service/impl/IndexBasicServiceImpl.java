package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.dao.mapper.IndexBasicMapper;
import com.kk.business.quantization.service.IIndexBasicService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.IndexBasicListVo;
import com.kk.business.quantization.model.dto.IndexBasicListDto;
import com.kk.business.quantization.model.vo.IndexBasicAddVo;
import com.kk.business.quantization.model.vo.IndexBasicEditVo;
import com.kk.business.quantization.model.dto.IndexBasicDto;
import com.kk.business.quantization.model.vo.IndexBasicDetailsVo;
import com.kk.business.quantization.model.vo.IndexBasicDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数基本信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class IndexBasicServiceImpl extends MppServiceImpl<IndexBasicMapper, IndexBasic> implements IIndexBasicService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(IndexBasicAddVo vo)
    {
        IndexBasic model = mapperUtils.map(vo,IndexBasic.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(IndexBasicEditVo vo)
    {
        IndexBasic model = mapperUtils.map(vo,IndexBasic.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("指数基本信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public IndexBasicDto selectById(IndexBasicDetailsVo vo)
    {
        IndexBasic model = mapperUtils.map(vo,IndexBasic.class);
        IndexBasic res = this.baseMapper.selectByMultiId(model);
        IndexBasicDto dto = mapperUtils.map(res,IndexBasicDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(IndexBasicDeleteVo vo)
    {
        IndexBasic model = mapperUtils.map(vo,IndexBasic.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数基本信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexBasicListDto>  selectPageList(IndexBasicListVo vo){

        IPage<IndexBasicListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<IndexBasicListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
