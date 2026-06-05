package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.Monthly;
import com.kk.business.quantization.dao.mapper.MonthlyMapper;
import com.kk.business.quantization.service.IMonthlyService;
import com.kk.business.quantization.model.vobase.req.MonthlyListReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyListResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyAddReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyEditReqVo;
import com.kk.business.quantization.model.vobase.res.MonthlyResVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MonthlyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股月线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MonthlyServiceImpl extends ServiceImpl<MonthlyMapper, Monthly> implements IMonthlyService {


    /**
    * 分批批量插入个股月线行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertMonthlyBatchSomeColumn(List<Monthly> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Monthly> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertMonthly(MonthlyAddReqVo vo)
    {
        Monthly model = new Monthly();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateMonthly(MonthlyEditReqVo vo)
    {
        Monthly model = new Monthly();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股月线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public MonthlyResVo selectMonthlyById(MonthlyDetailsReqVo vo)
    {
        Monthly model = new Monthly();
        BeanUtil.copyProperties(vo,model);
        Monthly res = this.baseMapper.selectByMultiId(model);
        MonthlyResVo resVo = new MonthlyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股月线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteMonthlyById(MonthlyDeleteReqVo vo)
    {
        Monthly model = new Monthly();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股月线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股月线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<MonthlyListResVo>  selectMonthlyPageList(MonthlyListReqVo vo){

        Page<MonthlyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<MonthlyListResVo> results = this.baseMapper.selectMonthlyPageList(page,vo);
        PageResult<MonthlyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
