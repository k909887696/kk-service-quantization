package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.TradeCal;
import com.kk.business.quantization.dao.mapper.TradeCalMapper;
import com.kk.business.quantization.service.ITradeCalService;
import com.kk.business.quantization.model.vobase.req.TradeCalListReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalListResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalAddReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalEditReqVo;
import com.kk.business.quantization.model.vobase.res.TradeCalResVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.TradeCalDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 交易日历 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class TradeCalServiceImpl extends ServiceImpl<TradeCalMapper, TradeCal> implements ITradeCalService {


    /**
    * 分批批量插入交易日历
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertTradeCalBatchSomeColumn(List<TradeCal> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<TradeCal> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertTradeCal(TradeCalAddReqVo vo)
    {
        TradeCal model = new TradeCal();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateTradeCal(TradeCalEditReqVo vo)
    {
        TradeCal model = new TradeCal();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("交易日历更新失败!");
        }
        return r;
    }
    /**
    * 单条查询交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public TradeCalResVo selectTradeCalById(TradeCalDetailsReqVo vo)
    {
        TradeCal model = new TradeCal();
        BeanUtil.copyProperties(vo,model);
        TradeCal res = this.baseMapper.selectByMultiId(model);
        TradeCalResVo resVo = new TradeCalResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除交易日历
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteTradeCalById(TradeCalDeleteReqVo vo)
    {
        TradeCal model = new TradeCal();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("交易日历删除失败!");
        }
        return r;
    }
    /**
    * 分页获取交易日历结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<TradeCalListResVo>  selectTradeCalPageList(TradeCalListReqVo vo){

        Page<TradeCalListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<TradeCalListResVo> results = this.baseMapper.selectTradeCalPageList(page,vo);
        PageResult<TradeCalListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }
    /**
     * 根据日期获取日期最近以往开市日期
     * @param date 日期
     * @param limit 天数
     * @param order 默认asc 升序，desc 降序
     * @return
     */
    @Override
    public TradeCal getRecentlyOpenByDay(String date,int limit,String order)
    {
        LambdaQueryWrapper<TradeCal> query = new LambdaQueryWrapper<>();
        IPage<TradeCal> page = new Page<>(1,limit<=0?1:limit);

        //这里开始编写查询条件
        query.le(TradeCal::getCalDate,date);
        query.eq(TradeCal::getIsOpen,1);
        query.orderByDesc(TradeCal::getCalDate);

        page = baseMapper.selectPage(page,query);


        if(page.getRecords()!=null && page.getRecords().size() > 0) {
            if("desc".equals(order)) {
                return page.getRecords().get(0);
            }else{
                return page.getRecords().get(page.getRecords().size()-1);
            }
        }else {
            return null;
        }
    }


}
