package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyUser;
import com.kk.business.quantization.service.ISyUserService;
import com.kk.business.quantization.model.vobase.req.SyUserListReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserListResVo;
import com.kk.business.quantization.model.vobase.req.SyUserAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyUserResVo;
import com.kk.business.quantization.model.vobase.req.SyUserDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyUserDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 用户信息 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class SyUserServiceApi   {

    @Resource
    public ISyUserService syUserService;

    /**
    * 分批批量插入用户信息
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertSyUserBatchSomeColumn(List<SyUser> list)
    {
        syUserService.insertSyUserBatchSomeColumn(list);
    }
    /**
    * 单条插入用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertSyUser(SyUserAddReqVo vo)
    {
        syUserService.insertSyUser(vo);
    }
    /**
    * 更新用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateSyUser(SyUserEditReqVo vo)
    {
        return syUserService.updateSyUser(vo);
    }
    /**
    * 单条查询用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public SyUserResVo selectSyUserById(SyUserDetailsReqVo vo)
    {
        return syUserService.selectSyUserById(vo);
    }
    /**
    * 删除用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteSyUserById(SyUserDeleteReqVo vo)
    {
        return syUserService.deleteSyUserById(vo);
    }
    /**
    * 分页获取用户信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<SyUserListResVo>  selectSyUserPageList(SyUserListReqVo vo){
        return syUserService.selectSyUserPageList(vo);
    }



}
