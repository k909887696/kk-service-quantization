package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexMember;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业明细 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IIndexMemberService  {

    /**
    * 分批批量插入申万行业明细
    * @param list 数据列表
    * @return
    */
    void insertIndexMemberBatchSomeColumn(List<IndexMember> list);
    /**
    * 单条插入申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    void insertIndexMember(IndexMemberAddReqVo vo);
    /**
    * 更新申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    int updateIndexMember(IndexMemberEditReqVo vo);
    /**
    * 单条查询申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    IndexMemberResVo selectIndexMemberById(IndexMemberDetailsReqVo vo);
    /**
    * 删除申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteIndexMemberById(IndexMemberDeleteReqVo vo);
    /**
    * 分页获取申万行业明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexMemberListResVo>  selectIndexMemberPageList(IndexMemberListReqVo vo);
}

