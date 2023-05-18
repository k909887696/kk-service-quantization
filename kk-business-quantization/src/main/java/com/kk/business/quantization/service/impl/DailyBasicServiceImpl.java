package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.dao.mapper.DailyBasicMapper;
import com.kk.business.quantization.service.IDailyBasicService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.DailyBasicListVo;
import com.kk.business.quantization.model.dto.DailyBasicListDto;
import com.kk.business.quantization.model.vo.DailyBasicAddVo;
import com.kk.business.quantization.model.vo.DailyBasicEditVo;
import com.kk.business.quantization.model.dto.DailyBasicDto;
import com.kk.business.quantization.model.vo.DailyBasicDetailsVo;
import com.kk.business.quantization.model.vo.DailyBasicDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股每日指标 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class DailyBasicServiceImpl extends MppServiceImpl<DailyBasicMapper, DailyBasic> implements IDailyBasicService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<DailyBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<DailyBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(DailyBasicAddVo vo)
    {
        DailyBasic model = mapperUtils.map(vo,DailyBasic.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(DailyBasicEditVo vo)
    {
        DailyBasic model = mapperUtils.map(vo,DailyBasic.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股每日指标更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public DailyBasicDto selectById(DailyBasicDetailsVo vo)
    {
        DailyBasic model = mapperUtils.map(vo,DailyBasic.class);
        DailyBasic res = this.baseMapper.selectByMultiId(model);
        DailyBasicDto dto = mapperUtils.map(res,DailyBasicDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(DailyBasicDeleteVo vo)
    {
        DailyBasic model = mapperUtils.map(vo,DailyBasic.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股每日指标删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<DailyBasicListDto>  selectPageList(DailyBasicListVo vo){

        IPage<DailyBasicListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<DailyBasicListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
