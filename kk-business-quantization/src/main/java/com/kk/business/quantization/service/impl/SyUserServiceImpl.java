package com.kk.business.quantization.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.kk.common.auth.LoginInfo;
import com.kk.common.auth.LoginUserInfo;
import com.kk.common.auth.LoginUserJurInfo;
import com.kk.common.auth.LoginUtil;
import com.kk.common.base.model.LoginRes;
import com.kk.common.base.model.LoginVo;
import com.kk.common.web.model.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.Date;
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
 * @since 2026-06-08
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
        if(StringUtils.isBlank(vo.getUserId()))
        {
            throw new BusinessException("账号不能为空！");
        }
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

    /**
     * 用户登录
     * @param vo
     * @return
     */
    @Override
    public LoginUserInfo loginUser(LoginVo vo)
    {
        if(StringUtils.isBlank(vo.getUserId()))
        {
            throw new BusinessException("账号不能为空！");
        }
        if(StringUtils.isBlank(vo.getPassword()))
        {
            throw new BusinessException("密码不能为空！");
        }
        SyUser user = this.baseMapper.selectById(vo.getUserId());
        if(user==null || !user.getPassword().equals(vo.getPassword()) || !user.getStatus().equals("1"))
        {
            throw new BusinessException("账号或密码错误！");
        }
        StpUtil.login(vo.getUserId());
        LoginInfo loginInfo = new LoginInfo();
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setUserId(user.getUserId()); // Set user ID
        userInfo.setUserName(user.getUserName());
        userInfo.setUserType(user.getUserType());
        userInfo.setLoginTime(new Date());
        userInfo.setLoginChannel("web");
        loginInfo.setLoginUserInfo(userInfo);
        loginInfo.setLoginUserJurInfo(new LoginUserJurInfo());
        //List<String> permissionList = List.of("add","update","delete");
        //loginInfo.getLoginUserJurInfo().setPermissionList(permissionList);
        loginInfo = LoginUtil.login(loginInfo);


        return loginInfo.getLoginUserInfo();
    }

}
