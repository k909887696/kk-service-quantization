package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.dao.mapper.InvokeTypeMapper;
import com.kk.business.quantization.service.IInvokeTypeService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.InvokeTypeListVo;
import com.kk.business.quantization.model.dto.InvokeTypeListDto;
import com.kk.business.quantization.model.vo.InvokeTypeAddVo;
import com.kk.business.quantization.model.vo.InvokeTypeEditVo;
import com.kk.business.quantization.model.dto.InvokeTypeDto;
import com.kk.business.quantization.model.vo.InvokeTypeDetailsVo;
import com.kk.business.quantization.model.vo.InvokeTypeDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 系统设置-调度类型 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class InvokeTypeServiceImpl extends MppServiceImpl<InvokeTypeMapper, InvokeType> implements IInvokeTypeService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<InvokeType> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<InvokeType> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(InvokeTypeAddVo vo)
    {
        InvokeType model = mapperUtils.map(vo,InvokeType.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(InvokeTypeEditVo vo)
    {
        InvokeType model = mapperUtils.map(vo,InvokeType.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-调度类型更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public InvokeTypeDto selectById(InvokeTypeDetailsVo vo)
    {
        InvokeType model = mapperUtils.map(vo,InvokeType.class);
        InvokeType res = this.baseMapper.selectById(model);
        InvokeTypeDto dto = mapperUtils.map(res,InvokeTypeDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(InvokeTypeDeleteVo vo)
    {
        InvokeType model = mapperUtils.map(vo,InvokeType.class);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-调度类型删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<InvokeTypeListDto>  selectPageList(InvokeTypeListVo vo){

        IPage<InvokeTypeListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<InvokeTypeListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
