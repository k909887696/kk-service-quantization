package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.kk.business.quantization.dao.mapper.MoneyFlowMapper;
import com.kk.business.quantization.service.IMoneyFlowService;
import com.kk.business.quantization.model.vobase.req.MoneyFlowListReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowListResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowEditReqVo;
import com.kk.business.quantization.model.vobase.res.MoneyFlowResVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MoneyFlowDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股资金流向 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MoneyFlowServiceImpl extends ServiceImpl<MoneyFlowMapper, MoneyFlow> implements IMoneyFlowService {


    /**
    * 分批批量插入个股资金流向
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertMoneyFlowBatchSomeColumn(List<MoneyFlow> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<MoneyFlow> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertMoneyFlow(MoneyFlowAddReqVo vo)
    {
        MoneyFlow model = new MoneyFlow();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateMoneyFlow(MoneyFlowEditReqVo vo)
    {
        MoneyFlow model = new MoneyFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股资金流向更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public MoneyFlowResVo selectMoneyFlowById(MoneyFlowDetailsReqVo vo)
    {
        MoneyFlow model = new MoneyFlow();
        BeanUtil.copyProperties(vo,model);
        MoneyFlow res = this.baseMapper.selectByMultiId(model);
        MoneyFlowResVo resVo = new MoneyFlowResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股资金流向
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteMoneyFlowById(MoneyFlowDeleteReqVo vo)
    {
        MoneyFlow model = new MoneyFlow();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股资金流向删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股资金流向结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<MoneyFlowListResVo>  selectMoneyFlowPageList(MoneyFlowListReqVo vo){

        Page<MoneyFlowListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<MoneyFlowListResVo> results = this.baseMapper.selectMoneyFlowPageList(page,vo);
        PageResult<MoneyFlowListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
