package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.business.quantization.service.IIndexMemberService;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 申万行业明细 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexMemberServiceApi   {

    @Resource
    public IIndexMemberService indexMemberService;

    /**
    * 分批批量插入申万行业明细
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertIndexMemberBatchSomeColumn(List<IndexMember> list)
    {
        indexMemberService.insertIndexMemberBatchSomeColumn(list);
    }
    /**
    * 单条插入申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertIndexMember(IndexMemberAddReqVo vo)
    {
        indexMemberService.insertIndexMember(vo);
    }
    /**
    * 更新申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateIndexMember(IndexMemberEditReqVo vo)
    {
        return indexMemberService.updateIndexMember(vo);
    }
    /**
    * 单条查询申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public IndexMemberResVo selectIndexMemberById(IndexMemberDetailsReqVo vo)
    {
        return indexMemberService.selectIndexMemberById(vo);
    }
    /**
    * 删除申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteIndexMemberById(IndexMemberDeleteReqVo vo)
    {
        return indexMemberService.deleteIndexMemberById(vo);
    }
    /**
    * 分页获取申万行业明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<IndexMemberListResVo>  selectIndexMemberPageList(IndexMemberListReqVo vo){
        return indexMemberService.selectIndexMemberPageList(vo);
    }



}
