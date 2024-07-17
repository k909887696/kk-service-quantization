package com.kk.business.quantization.service.impl;


import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.dao.mapper.ConceptDailyMapper;
import com.kk.business.quantization.service.IConceptDailyService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.dto.ConceptDailyListDto;
import com.kk.business.quantization.model.dto.ConceptDailyDto;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
@Service
public class ConceptDailyServiceImpl extends MppServiceImpl<ConceptDailyMapper, ConceptDaily> implements IConceptDailyService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<ConceptDaily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptDaily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(ConceptDailyAddVo vo)
    {
        ConceptDaily model = mapperUtils.map(vo,ConceptDaily.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(ConceptDailyEditVo vo)
    {
        ConceptDaily model = mapperUtils.map(vo,ConceptDaily.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念日线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public ConceptDailyDto selectById(ConceptDailyDetailsVo vo)
    {
        ConceptDaily model = mapperUtils.map(vo,ConceptDaily.class);
        ConceptDaily res = this.baseMapper.selectByMultiId(model);
        ConceptDailyDto dto = mapperUtils.map(res,ConceptDailyDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(ConceptDailyDeleteVo vo)
    {
        ConceptDaily model = mapperUtils.map(vo,ConceptDaily.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念日线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<ConceptDailyListDto>  selectPageList(ConceptDailyListVo vo){

        IPage<ConceptDailyListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<ConceptDailyListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 查询区间涨幅最大概念
     * @param vo
     * @return
     */
    public PageResult<DailyLeaderDto> selectConceptLeaderListByRange(SearchDailyLeaderVo vo)
    {
        IPage<DailyLeaderDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectConceptLeaderListByRange(page,vo);
        PageResult<DailyLeaderDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

}
