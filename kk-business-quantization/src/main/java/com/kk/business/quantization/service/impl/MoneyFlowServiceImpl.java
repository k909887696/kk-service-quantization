package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.business.quantization.dao.mapper.MoneyFlowMapper;
import com.kk.business.quantization.service.IMoneyFlowService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.MoneyFlowListVo;
import com.kk.business.quantization.model.dto.MoneyFlowListDto;
import com.kk.business.quantization.model.vo.MoneyFlowAddVo;
import com.kk.business.quantization.model.vo.MoneyFlowEditVo;
import com.kk.business.quantization.model.dto.MoneyFlowDto;
import com.kk.business.quantization.model.vo.MoneyFlowDetailsVo;
import com.kk.business.quantization.model.vo.MoneyFlowDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股资金流向 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class MoneyFlowServiceImpl extends MppServiceImpl<MoneyFlowMapper, MoneyFlow> implements IMoneyFlowService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<MoneyFlow> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<MoneyFlow> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(MoneyFlowAddVo vo)
    {
        MoneyFlow model = mapperUtils.map(vo,MoneyFlow.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(MoneyFlowEditVo vo)
    {
        MoneyFlow model = mapperUtils.map(vo,MoneyFlow.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股资金流向更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public MoneyFlowDto selectById(MoneyFlowDetailsVo vo)
    {
        MoneyFlow model = mapperUtils.map(vo,MoneyFlow.class);
        MoneyFlow res = this.baseMapper.selectByMultiId(model);
        MoneyFlowDto dto = mapperUtils.map(res,MoneyFlowDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(MoneyFlowDeleteVo vo)
    {
        MoneyFlow model = mapperUtils.map(vo,MoneyFlow.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股资金流向删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<MoneyFlowListDto>  selectPageList(MoneyFlowListVo vo){

        IPage<MoneyFlowListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<MoneyFlowListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
