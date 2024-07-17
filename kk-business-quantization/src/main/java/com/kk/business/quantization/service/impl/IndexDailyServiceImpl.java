package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.business.quantization.dao.mapper.IndexDailyMapper;
import com.kk.business.quantization.service.IIndexDailyService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.IndexDailyListVo;
import com.kk.business.quantization.model.dto.IndexDailyListDto;
import com.kk.business.quantization.model.vo.IndexDailyAddVo;
import com.kk.business.quantization.model.vo.IndexDailyEditVo;
import com.kk.business.quantization.model.dto.IndexDailyDto;
import com.kk.business.quantization.model.vo.IndexDailyDetailsVo;
import com.kk.business.quantization.model.vo.IndexDailyDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Service
public class IndexDailyServiceImpl extends MppServiceImpl<IndexDailyMapper, IndexDaily> implements IIndexDailyService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexDaily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexDaily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(IndexDailyAddVo vo)
    {
        IndexDaily model = mapperUtils.map(vo,IndexDaily.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(IndexDailyEditVo vo)
    {
        IndexDaily model = mapperUtils.map(vo,IndexDaily.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数日线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public IndexDailyDto selectById(IndexDailyDetailsVo vo)
    {
        IndexDaily model = mapperUtils.map(vo,IndexDaily.class);
        IndexDaily res = this.baseMapper.selectByMultiId(model);
        IndexDailyDto dto = mapperUtils.map(res,IndexDailyDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(IndexDailyDeleteVo vo)
    {
        IndexDaily model = mapperUtils.map(vo,IndexDaily.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数日线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexDailyListDto>  selectPageList(IndexDailyListVo vo){

        IPage<IndexDailyListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<IndexDailyListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
