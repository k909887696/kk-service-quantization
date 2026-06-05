package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.model.vo.StockBasicList4InnVo;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.dao.mapper.StockBasicMapper;
import com.kk.business.quantization.service.IStockBasicService;
import com.kk.business.quantization.model.vobase.req.StockBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicListResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.StockBasicResVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.StockBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股基本信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class StockBasicServiceImpl extends ServiceImpl<StockBasicMapper, StockBasic> implements IStockBasicService {


    /**
    * 分批批量插入个股基本信息
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertStockBasicBatchSomeColumn(List<StockBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<StockBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertStockBasic(StockBasicAddReqVo vo)
    {
        StockBasic model = new StockBasic();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateStockBasic(StockBasicEditReqVo vo)
    {
        StockBasic model = new StockBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("个股基本信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public StockBasicResVo selectStockBasicById(StockBasicDetailsReqVo vo)
    {
        StockBasic model = new StockBasic();
        BeanUtil.copyProperties(vo,model);
        StockBasic res = this.baseMapper.selectById(model);
        StockBasicResVo resVo = new StockBasicResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteStockBasicById(StockBasicDeleteReqVo vo)
    {
        StockBasic model = new StockBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("个股基本信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<StockBasicListResVo>  selectStockBasicPageList(StockBasicListReqVo vo){

        Page<StockBasicListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<StockBasicListResVo> results = this.baseMapper.selectStockBasicPageList(page,vo);
        PageResult<StockBasicListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }

    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    @Override
    public PageResult<StockBasic>  getStockBasicPageResult(StockBasicList4InnVo vo){

        LambdaQueryWrapper<StockBasic> query = new LambdaQueryWrapper<>();
        IPage<StockBasic> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件
        if(vo.getSymbolList() != null && vo.getSymbolList().size() > 0)
        {
            query.in(StockBasic::getSymbol,vo.getSymbolList());
        }
        page = this.baseMapper.selectPage(page,query);
        PageResult<StockBasic>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

}
