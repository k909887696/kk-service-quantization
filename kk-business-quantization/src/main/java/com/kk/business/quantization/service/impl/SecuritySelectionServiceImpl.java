package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.dao.mapper.SecuritySelectionMapper;
import com.kk.business.quantization.service.ISecuritySelectionService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.SecuritySelectionListVo;
import com.kk.business.quantization.model.dto.SecuritySelectionListDto;
import com.kk.business.quantization.model.vo.SecuritySelectionAddVo;
import com.kk.business.quantization.model.vo.SecuritySelectionEditVo;
import com.kk.business.quantization.model.dto.SecuritySelectionDto;
import com.kk.business.quantization.model.vo.SecuritySelectionDetailsVo;
import com.kk.business.quantization.model.vo.SecuritySelectionDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个人自选股 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class SecuritySelectionServiceImpl extends MppServiceImpl<SecuritySelectionMapper, SecuritySelection> implements ISecuritySelectionService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<SecuritySelection> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SecuritySelection> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(SecuritySelectionAddVo vo)
    {
        SecuritySelection model = mapperUtils.map(vo,SecuritySelection.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(SecuritySelectionEditVo vo)
    {
        SecuritySelection model = mapperUtils.map(vo,SecuritySelection.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个人自选股更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public SecuritySelectionDto selectById(SecuritySelectionDetailsVo vo)
    {
        SecuritySelection model = mapperUtils.map(vo,SecuritySelection.class);
        SecuritySelection res = this.baseMapper.selectByMultiId(model);
        SecuritySelectionDto dto = mapperUtils.map(res,SecuritySelectionDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(SecuritySelectionDeleteVo vo)
    {
        SecuritySelection model = mapperUtils.map(vo,SecuritySelection.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个人自选股删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SecuritySelectionListDto>  selectPageList(SecuritySelectionListVo vo){

        IPage<SecuritySelectionListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<SecuritySelectionListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
