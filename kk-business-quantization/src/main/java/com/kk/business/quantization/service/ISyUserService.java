package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SyUser;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.SyUserListVo;
import com.kk.business.quantization.model.dto.SyUserListDto;
import com.kk.business.quantization.model.vo.SyUserAddVo;
import com.kk.business.quantization.model.vo.SyUserEditVo;
import com.kk.business.quantization.model.dto.SyUserDto;
import com.kk.business.quantization.model.vo.SyUserDetailsVo;
import com.kk.business.quantization.model.vo.SyUserDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface ISyUserService extends IMppService<SyUser> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<SyUser> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SyUserListDto>  selectPageList(SyUserListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(SyUserAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(SyUserEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    SyUserDto selectById(SyUserDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(SyUserDeleteVo vo);

}
