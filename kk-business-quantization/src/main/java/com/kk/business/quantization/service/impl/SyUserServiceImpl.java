package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyUser;
import com.kk.business.quantization.dao.mapper.SyUserMapper;
import com.kk.business.quantization.service.ISyUserService;
import com.kk.business.quantization.model.vobase.req.SyUserListReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserListResVo;
import com.kk.business.quantization.model.vobase.req.SyUserAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserResVo;
import com.kk.business.quantization.model.vobase.req.SyUserDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class SyUserServiceImpl extends ServiceImpl<SyUserMapper, SyUser> implements ISyUserService {


    /**
    * 分批批量插入用户信息
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyUserBatchSomeColumn(List<SyUser> list)
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
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyUser(SyUserAddReqVo vo)
    {
        SyUser model = new SyUser();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyUser(SyUserEditReqVo vo)
    {
        SyUser model = new SyUser();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("用户信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyUserResVo selectSyUserById(SyUserDetailsReqVo vo)
    {
        SyUser model = new SyUser();
        BeanUtil.copyProperties(vo,model);
        SyUser res = this.baseMapper.selectById(model);
        SyUserResVo resVo = new SyUserResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyUserById(SyUserDeleteReqVo vo)
    {
        SyUser model = new SyUser();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("用户信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取用户信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyUserListResVo>  selectSyUserPageList(SyUserListReqVo vo){

        Page<SyUserListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyUserListResVo> results = this.baseMapper.selectSyUserPageList(page,vo);
        PageResult<SyUserListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
