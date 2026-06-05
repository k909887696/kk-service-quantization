package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.Weekly;
import com.kk.business.quantization.dao.mapper.WeeklyMapper;
import com.kk.business.quantization.service.IWeeklyService;
import com.kk.business.quantization.model.vobase.req.WeeklyListReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyListResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyAddReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyEditReqVo;
import com.kk.business.quantization.model.vobase.res.WeeklyResVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.WeeklyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股周线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class WeeklyServiceImpl extends ServiceImpl<WeeklyMapper, Weekly> implements IWeeklyService {


    /**
    * 分批批量插入个股周线行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertWeeklyBatchSomeColumn(List<Weekly> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Weekly> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertWeekly(WeeklyAddReqVo vo)
    {
        Weekly model = new Weekly();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateWeekly(WeeklyEditReqVo vo)
    {
        Weekly model = new Weekly();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股周线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public WeeklyResVo selectWeeklyById(WeeklyDetailsReqVo vo)
    {
        Weekly model = new Weekly();
        BeanUtil.copyProperties(vo,model);
        Weekly res = this.baseMapper.selectByMultiId(model);
        WeeklyResVo resVo = new WeeklyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股周线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteWeeklyById(WeeklyDeleteReqVo vo)
    {
        Weekly model = new Weekly();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股周线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股周线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<WeeklyListResVo>  selectWeeklyPageList(WeeklyListReqVo vo){

        Page<WeeklyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<WeeklyListResVo> results = this.baseMapper.selectWeeklyPageList(page,vo);
        PageResult<WeeklyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
