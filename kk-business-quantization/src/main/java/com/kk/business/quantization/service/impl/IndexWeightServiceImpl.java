package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.business.quantization.dao.mapper.IndexWeightMapper;
import com.kk.business.quantization.service.IIndexWeightService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.IndexWeightListVo;
import com.kk.business.quantization.model.dto.IndexWeightListDto;
import com.kk.business.quantization.model.vo.IndexWeightAddVo;
import com.kk.business.quantization.model.vo.IndexWeightEditVo;
import com.kk.business.quantization.model.dto.IndexWeightDto;
import com.kk.business.quantization.model.vo.IndexWeightDetailsVo;
import com.kk.business.quantization.model.vo.IndexWeightDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数成分权重 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Service
public class IndexWeightServiceImpl extends MppServiceImpl<IndexWeightMapper, IndexWeight> implements IIndexWeightService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexWeight> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexWeight> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(IndexWeightAddVo vo)
    {
        IndexWeight model = mapperUtils.map(vo,IndexWeight.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(IndexWeightEditVo vo)
    {
        IndexWeight model = mapperUtils.map(vo,IndexWeight.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数成分权重更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public IndexWeightDto selectById(IndexWeightDetailsVo vo)
    {
        IndexWeight model = mapperUtils.map(vo,IndexWeight.class);
        IndexWeight res = this.baseMapper.selectByMultiId(model);
        IndexWeightDto dto = mapperUtils.map(res,IndexWeightDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(IndexWeightDeleteVo vo)
    {
        IndexWeight model = mapperUtils.map(vo,IndexWeight.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数成分权重删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexWeightListDto>  selectPageList(IndexWeightListVo vo){

        IPage<IndexWeightListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<IndexWeightListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
