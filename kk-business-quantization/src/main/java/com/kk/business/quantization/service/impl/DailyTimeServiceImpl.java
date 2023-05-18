package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.business.quantization.dao.mapper.DailyTimeMapper;
import com.kk.business.quantization.service.IDailyTimeService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.DailyTimeListVo;
import com.kk.business.quantization.model.dto.DailyTimeListDto;
import com.kk.business.quantization.model.vo.DailyTimeAddVo;
import com.kk.business.quantization.model.vo.DailyTimeEditVo;
import com.kk.business.quantization.model.dto.DailyTimeDto;
import com.kk.business.quantization.model.vo.DailyTimeDetailsVo;
import com.kk.business.quantization.model.vo.DailyTimeDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股分钟行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class DailyTimeServiceImpl extends MppServiceImpl<DailyTimeMapper, DailyTime> implements IDailyTimeService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<DailyTime> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<DailyTime> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(DailyTimeAddVo vo)
    {
        DailyTime model = mapperUtils.map(vo,DailyTime.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(DailyTimeEditVo vo)
    {
        DailyTime model = mapperUtils.map(vo,DailyTime.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股分钟行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public DailyTimeDto selectById(DailyTimeDetailsVo vo)
    {
        DailyTime model = mapperUtils.map(vo,DailyTime.class);
        DailyTime res = this.baseMapper.selectByMultiId(model);
        DailyTimeDto dto = mapperUtils.map(res,DailyTimeDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(DailyTimeDeleteVo vo)
    {
        DailyTime model = mapperUtils.map(vo,DailyTime.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股分钟行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<DailyTimeListDto>  selectPageList(DailyTimeListVo vo){

        IPage<DailyTimeListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<DailyTimeListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
