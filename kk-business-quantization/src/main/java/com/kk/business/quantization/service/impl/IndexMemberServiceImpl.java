package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.business.quantization.dao.mapper.IndexMemberMapper;
import com.kk.business.quantization.service.IIndexMemberService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.IndexMemberListVo;
import com.kk.business.quantization.model.dto.IndexMemberListDto;
import com.kk.business.quantization.model.vo.IndexMemberAddVo;
import com.kk.business.quantization.model.vo.IndexMemberEditVo;
import com.kk.business.quantization.model.dto.IndexMemberDto;
import com.kk.business.quantization.model.vo.IndexMemberDetailsVo;
import com.kk.business.quantization.model.vo.IndexMemberDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 申万行业明细 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class IndexMemberServiceImpl extends MppServiceImpl<IndexMemberMapper, IndexMember> implements IIndexMemberService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexMember> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexMember> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(IndexMemberAddVo vo)
    {
        IndexMember model = mapperUtils.map(vo,IndexMember.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(IndexMemberEditVo vo)
    {
        IndexMember model = mapperUtils.map(vo,IndexMember.class);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业明细更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public IndexMemberDto selectById(IndexMemberDetailsVo vo)
    {
        IndexMember model = mapperUtils.map(vo,IndexMember.class);
        IndexMember res = this.baseMapper.selectByMultiId(model);
        IndexMemberDto dto = mapperUtils.map(res,IndexMemberDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(IndexMemberDeleteVo vo)
    {
        IndexMember model = mapperUtils.map(vo,IndexMember.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业明细删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexMemberListDto>  selectPageList(IndexMemberListVo vo){

        IPage<IndexMemberListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<IndexMemberListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
