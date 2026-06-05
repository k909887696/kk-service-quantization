package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyUser;
import java.util.List;
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
 * 用户信息 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface ISyUserService  {

    /**
    * 分批批量插入用户信息
    * @param list 数据列表
    * @return
    */
    void insertSyUserBatchSomeColumn(List<SyUser> list);
    /**
    * 单条插入用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSyUser(SyUserAddReqVo vo);
    /**
    * 更新用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSyUser(SyUserEditReqVo vo);
    /**
    * 单条查询用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    SyUserResVo selectSyUserById(SyUserDetailsReqVo vo);
    /**
    * 删除用户信息
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSyUserById(SyUserDeleteReqVo vo);
    /**
    * 分页获取用户信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyUserListResVo>  selectSyUserPageList(SyUserListReqVo vo);
}

