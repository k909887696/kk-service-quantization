package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.business.quantization.dao.mapper.KdjCrossMapper;
import com.kk.business.quantization.service.IKdjCrossService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.KdjCrossListVo;
import com.kk.business.quantization.model.dto.KdjCrossListDto;
import com.kk.business.quantization.model.vo.KdjCrossAddVo;
import com.kk.business.quantization.model.vo.KdjCrossEditVo;
import com.kk.business.quantization.model.dto.KdjCrossDto;
import com.kk.business.quantization.model.vo.KdjCrossDetailsVo;
import com.kk.business.quantization.model.vo.KdjCrossDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * kdj交叉点 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class KdjCrossServiceImpl extends MppServiceImpl<KdjCrossMapper, KdjCross> implements IKdjCrossService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<KdjCross> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<KdjCross> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(KdjCrossAddVo vo)
    {
        KdjCross model = mapperUtils.map(vo,KdjCross.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(KdjCrossEditVo vo)
    {
        KdjCross model = mapperUtils.map(vo,KdjCross.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("kdj交叉点更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public KdjCrossDto selectById(KdjCrossDetailsVo vo)
    {
        KdjCross model = mapperUtils.map(vo,KdjCross.class);
        KdjCross res = this.baseMapper.selectByMultiId(model);
        KdjCrossDto dto = mapperUtils.map(res,KdjCrossDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(KdjCrossDeleteVo vo)
    {
        KdjCross model = mapperUtils.map(vo,KdjCross.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("kdj交叉点删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<KdjCrossListDto>  selectPageList(KdjCrossListVo vo){

        IPage<KdjCrossListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<KdjCrossListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
