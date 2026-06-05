package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.StockFluctuation;
import com.kk.business.quantization.dao.mapper.StockFluctuationMapper;
import com.kk.business.quantization.service.IStockFluctuationService;
import com.kk.business.quantization.model.vobase.req.StockFluctuationListReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationListResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockFluctuationResVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockFluctuationDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股异常波动信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockFluctuationServiceImpl extends ServiceImpl<StockFluctuationMapper, StockFluctuation> implements IStockFluctuationService {


    /**
    * 分批批量插入个股异常波动信息
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertStockFluctuationBatchSomeColumn(List<StockFluctuation> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<StockFluctuation> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertStockFluctuation(StockFluctuationAddReqVo vo)
    {
        StockFluctuation model = new StockFluctuation();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateStockFluctuation(StockFluctuationEditReqVo vo)
    {
        StockFluctuation model = new StockFluctuation();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("个股异常波动信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public StockFluctuationResVo selectStockFluctuationById(StockFluctuationDetailsReqVo vo)
    {
        StockFluctuation model = new StockFluctuation();
        BeanUtil.copyProperties(vo,model);
        StockFluctuation res = this.baseMapper.selectById(model);
        StockFluctuationResVo resVo = new StockFluctuationResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股异常波动信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteStockFluctuationById(StockFluctuationDeleteReqVo vo)
    {
        StockFluctuation model = new StockFluctuation();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("个股异常波动信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股异常波动信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<StockFluctuationListResVo>  selectStockFluctuationPageList(StockFluctuationListReqVo vo){

        Page<StockFluctuationListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<StockFluctuationListResVo> results = this.baseMapper.selectStockFluctuationPageList(page,vo);
        PageResult<StockFluctuationListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
