package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.ConceptMoneyFlow;
import com.kk.business.quantization.dao.mapper.ConceptMoneyFlowMapper;
import com.kk.business.quantization.service.IConceptMoneyFlowService;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptMoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptMoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念资金流向 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptMoneyFlowServiceImpl extends ServiceImpl<ConceptMoneyFlowMapper, ConceptMoneyFlow> implements IConceptMoneyFlowService {


    /**
    * 分批批量插入概念资金流向
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertConceptMoneyFlowBatchSomeColumn(List<ConceptMoneyFlow> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptMoneyFlow> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertConceptMoneyFlow(ConceptMoneyFlowAddReqVo vo)
    {
        ConceptMoneyFlow model = new ConceptMoneyFlow();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateConceptMoneyFlow(ConceptMoneyFlowEditReqVo vo)
    {
        ConceptMoneyFlow model = new ConceptMoneyFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念资金流向更新失败!");
        }
        return r;
    }
    /**
    * 单条查询概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ConceptMoneyFlowResVo selectConceptMoneyFlowById(ConceptMoneyFlowDetailsReqVo vo)
    {
        ConceptMoneyFlow model = new ConceptMoneyFlow();
        BeanUtil.copyProperties(vo,model);
        ConceptMoneyFlow res = this.baseMapper.selectByMultiId(model);
        ConceptMoneyFlowResVo resVo = new ConceptMoneyFlowResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除概念资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteConceptMoneyFlowById(ConceptMoneyFlowDeleteReqVo vo)
    {
        ConceptMoneyFlow model = new ConceptMoneyFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念资金流向删除失败!");
        }
        return r;
    }
    /**
    * 分页获取概念资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<ConceptMoneyFlowListResVo>  selectConceptMoneyFlowPageList(ConceptMoneyFlowListReqVo vo){

        Page<ConceptMoneyFlowListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<ConceptMoneyFlowListResVo> results = this.baseMapper.selectConceptMoneyFlowPageList(page,vo);
        PageResult<ConceptMoneyFlowListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
