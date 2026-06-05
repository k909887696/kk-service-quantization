package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.business.quantization.dao.mapper.IndexMemberMapper;
import com.kk.business.quantization.service.IIndexMemberService;
import com.kk.business.quantization.model.vobase.req.IndexMemberListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberListResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexMemberResVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexMemberDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 申万行业明细 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexMemberServiceImpl extends ServiceImpl<IndexMemberMapper, IndexMember> implements IIndexMemberService {


    /**
    * 分批批量插入申万行业明细
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertIndexMemberBatchSomeColumn(List<IndexMember> list)
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
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertIndexMember(IndexMemberAddReqVo vo)
    {
        IndexMember model = new IndexMember();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateIndexMember(IndexMemberEditReqVo vo)
    {
        IndexMember model = new IndexMember();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业明细更新失败!");
        }
        return r;
    }
    /**
    * 单条查询申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public IndexMemberResVo selectIndexMemberById(IndexMemberDetailsReqVo vo)
    {
        IndexMember model = new IndexMember();
        BeanUtil.copyProperties(vo,model);
        IndexMember res = this.baseMapper.selectByMultiId(model);
        IndexMemberResVo resVo = new IndexMemberResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除申万行业明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteIndexMemberById(IndexMemberDeleteReqVo vo)
    {
        IndexMember model = new IndexMember();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业明细删除失败!");
        }
        return r;
    }
    /**
    * 分页获取申万行业明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<IndexMemberListResVo>  selectIndexMemberPageList(IndexMemberListReqVo vo){

        Page<IndexMemberListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<IndexMemberListResVo> results = this.baseMapper.selectIndexMemberPageList(page,vo);
        PageResult<IndexMemberListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
