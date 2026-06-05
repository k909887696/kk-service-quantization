package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.DailyTime;
import com.kk.business.quantization.dao.mapper.DailyTimeMapper;
import com.kk.business.quantization.service.IDailyTimeService;
import com.kk.business.quantization.model.vobase.req.DailyTimeListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeListResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyTimeResVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyTimeDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股分钟行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyTimeServiceImpl extends ServiceImpl<DailyTimeMapper, DailyTime> implements IDailyTimeService {


    /**
    * 分批批量插入个股分钟行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertDailyTimeBatchSomeColumn(List<DailyTime> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<DailyTime> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertDailyTime(DailyTimeAddReqVo vo)
    {
        DailyTime model = new DailyTime();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateDailyTime(DailyTimeEditReqVo vo)
    {
        DailyTime model = new DailyTime();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股分钟行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public DailyTimeResVo selectDailyTimeById(DailyTimeDetailsReqVo vo)
    {
        DailyTime model = new DailyTime();
        BeanUtil.copyProperties(vo,model);
        DailyTime res = this.baseMapper.selectByMultiId(model);
        DailyTimeResVo resVo = new DailyTimeResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股分钟行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteDailyTimeById(DailyTimeDeleteReqVo vo)
    {
        DailyTime model = new DailyTime();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股分钟行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股分钟行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<DailyTimeListResVo>  selectDailyTimePageList(DailyTimeListReqVo vo){

        Page<DailyTimeListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<DailyTimeListResVo> results = this.baseMapper.selectDailyTimePageList(page,vo);
        PageResult<DailyTimeListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
