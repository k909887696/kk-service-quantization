package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.dao.mapper.CnMMapper;
import com.kk.business.quantization.service.ICnMService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.CnMListVo;
import com.kk.business.quantization.model.dto.CnMListDto;
import com.kk.business.quantization.model.vo.CnMAddVo;
import com.kk.business.quantization.model.vo.CnMEditVo;
import com.kk.business.quantization.model.dto.CnMDto;
import com.kk.business.quantization.model.vo.CnMDetailsVo;
import com.kk.business.quantization.model.vo.CnMDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 人民币货币总量对象 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class CnMServiceImpl extends MppServiceImpl<CnMMapper, CnM> implements ICnMService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<CnM> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CnM> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(CnMAddVo vo)
    {
        CnM model = mapperUtils.map(vo,CnM.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(CnMEditVo vo)
    {
        CnM model = mapperUtils.map(vo,CnM.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("人民币货币总量对象更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public CnMDto selectById(CnMDetailsVo vo)
    {
        CnM model = mapperUtils.map(vo,CnM.class);
        CnM res = this.baseMapper.selectById(model);
        CnMDto dto = mapperUtils.map(res,CnMDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(CnMDeleteVo vo)
    {
        CnM model = mapperUtils.map(vo,CnM.class);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("人民币货币总量对象删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<CnMListDto>  selectPageList(CnMListVo vo){

        IPage<CnMListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<CnMListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
