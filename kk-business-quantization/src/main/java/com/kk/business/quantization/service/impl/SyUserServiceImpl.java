package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.SyUser;
import com.kk.business.quantization.dao.mapper.SyUserMapper;
import com.kk.business.quantization.service.ISyUserService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.model.vo.SyUserListVo;
import com.kk.business.quantization.model.dto.SyUserListDto;
import com.kk.business.quantization.model.vo.SyUserAddVo;
import com.kk.business.quantization.model.vo.SyUserEditVo;
import com.kk.business.quantization.model.dto.SyUserDto;
import com.kk.business.quantization.model.vo.SyUserDetailsVo;
import com.kk.business.quantization.model.vo.SyUserDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
@Service
public class SyUserServiceImpl extends MppServiceImpl<SyUserMapper, SyUser> implements ISyUserService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<SyUser> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyUser> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(SyUserAddVo vo)
    {
        SyUser model = mapperUtils.map(vo,SyUser.class);
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(SyUserEditVo vo)
    {
        SyUser model = mapperUtils.map(vo,SyUser.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("用户信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public SyUserDto selectById(SyUserDetailsVo vo)
    {
        SyUser model = mapperUtils.map(vo,SyUser.class);
        SyUser res = this.baseMapper.selectByMultiId(model);
        SyUserDto dto = mapperUtils.map(res,SyUserDto.class);
        return dto;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(SyUserDeleteVo vo)
    {
        SyUser model = mapperUtils.map(vo,SyUser.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("用户信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyUserListDto>  selectPageList(SyUserListVo vo){

        IPage<SyUserListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<SyUserListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
