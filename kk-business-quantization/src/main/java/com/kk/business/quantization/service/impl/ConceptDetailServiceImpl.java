package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.ConceptDetail;
import com.kk.business.quantization.dao.mapper.ConceptDetailMapper;
import com.kk.business.quantization.service.IConceptDetailService;
import com.kk.business.quantization.model.vobase.req.ConceptDetailListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDetailResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDetailDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念明细 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptDetailServiceImpl extends ServiceImpl<ConceptDetailMapper, ConceptDetail> implements IConceptDetailService {


    /**
    * 分批批量插入概念明细
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertConceptDetailBatchSomeColumn(List<ConceptDetail> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptDetail> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertConceptDetail(ConceptDetailAddReqVo vo)
    {
        ConceptDetail model = new ConceptDetail();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateConceptDetail(ConceptDetailEditReqVo vo)
    {
        ConceptDetail model = new ConceptDetail();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念明细更新失败!");
        }
        return r;
    }
    /**
    * 单条查询概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ConceptDetailResVo selectConceptDetailById(ConceptDetailDetailsReqVo vo)
    {
        ConceptDetail model = new ConceptDetail();
        BeanUtil.copyProperties(vo,model);
        ConceptDetail res = this.baseMapper.selectByMultiId(model);
        ConceptDetailResVo resVo = new ConceptDetailResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除概念明细
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteConceptDetailById(ConceptDetailDeleteReqVo vo)
    {
        ConceptDetail model = new ConceptDetail();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念明细删除失败!");
        }
        return r;
    }
    /**
    * 分页获取概念明细结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<ConceptDetailListResVo>  selectConceptDetailPageList(ConceptDetailListReqVo vo){

        Page<ConceptDetailListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<ConceptDetailListResVo> results = this.baseMapper.selectConceptDetailPageList(page,vo);
        PageResult<ConceptDetailListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
