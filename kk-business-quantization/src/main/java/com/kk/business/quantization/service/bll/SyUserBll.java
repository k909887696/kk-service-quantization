package com.kk.business.quantization.service.bll;


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
/**
 * <p>
 * 用户信息 Bll业务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Service
public class SyUserBll   {

    @Resource
    public ISyUserService syUserService;

    /**
    * 分批批量插入用户信息
    * @param list 数据列表
    * @return
    */
    public void insertSyUserBatchSomeColumn(List<SyUser> list)
    {
        syUserService.insertSyUserBatchSomeColumn(list);
    }
    /**
    * 单条插入用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    public void insertSyUser(SyUserAddReqVo vo)
    {
        syUserService.insertSyUser(vo);
    }
    /**
    * 更新用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    public int updateSyUser(SyUserEditReqVo vo)
    {
        return syUserService.updateSyUser(vo);
    }
    /**
    * 单条查询用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    public SyUserResVo selectSyUserById(SyUserDetailsReqVo vo)
    {
        return syUserService.selectSyUserById(vo);
    }
    /**
    * 删除用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteSyUserById(SyUserDeleteReqVo vo)
    {
        return syUserService.deleteSyUserById(vo);
    }
    /**
    * 分页获取用户信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyUserListResVo>  selectSyUserPageList(SyUserListReqVo vo){
        return syUserService.selectSyUserPageList(vo);
    }



}

