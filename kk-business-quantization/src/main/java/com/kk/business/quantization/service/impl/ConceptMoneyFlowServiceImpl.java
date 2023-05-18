package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.business.quantization.dao.mapper.ConceptMoneyFlowMapper;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowListVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowListDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowAddVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowEditVo;
import com.kk.business.quantization.model.dto.ConceptMoneyFlowDto;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDetailsVo;
import com.kk.business.quantization.model.vo.ConceptMoneyFlowDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念资金流向 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Service
public class ConceptMoneyFlowServiceImpl extends MppServiceImpl<ConceptMoneyFlowMapper, ConceptMoneyFlow> implements IConceptMoneyFlowService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<ConceptMoneyFlow> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptMoneyFlow> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(ConceptMoneyFlowAddVo vo)
    {
        ConceptMoneyFlow model = mapperUtils.map(vo,ConceptMoneyFlow.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(ConceptMoneyFlowEditVo vo)
    {
        ConceptMoneyFlow model = mapperUtils.map(vo,ConceptMoneyFlow.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念资金流向更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public ConceptMoneyFlowDto selectById(ConceptMoneyFlowDetailsVo vo)
    {
        ConceptMoneyFlow model = mapperUtils.map(vo,ConceptMoneyFlow.class);
        ConceptMoneyFlow res = this.baseMapper.selectByMultiId(model);
        ConceptMoneyFlowDto dto = mapperUtils.map(res,ConceptMoneyFlowDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(ConceptMoneyFlowDeleteVo vo)
    {
        ConceptMoneyFlow model = mapperUtils.map(vo,ConceptMoneyFlow.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念资金流向删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<ConceptMoneyFlowListDto>  selectPageList(ConceptMoneyFlowListVo vo){

        IPage<ConceptMoneyFlowListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<ConceptMoneyFlowListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
