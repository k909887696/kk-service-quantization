package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.dao.mapper.DailyBasicMapper;
import com.kk.business.quantization.service.IDailyBasicService;
import com.kk.business.quantization.model.vobase.req.DailyBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicListResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyBasicResVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股每日指标 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyBasicServiceImpl extends ServiceImpl<DailyBasicMapper, DailyBasic> implements IDailyBasicService {


    /**
    * 分批批量插入个股每日指标
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertDailyBasicBatchSomeColumn(List<DailyBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<DailyBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertDailyBasic(DailyBasicAddReqVo vo)
    {
        DailyBasic model = new DailyBasic();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateDailyBasic(DailyBasicEditReqVo vo)
    {
        DailyBasic model = new DailyBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股每日指标更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public DailyBasicResVo selectDailyBasicById(DailyBasicDetailsReqVo vo)
    {
        DailyBasic model = new DailyBasic();
        BeanUtil.copyProperties(vo,model);
        DailyBasic res = this.baseMapper.selectByMultiId(model);
        DailyBasicResVo resVo = new DailyBasicResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股每日指标
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteDailyBasicById(DailyBasicDeleteReqVo vo)
    {
        DailyBasic model = new DailyBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股每日指标删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股每日指标结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<DailyBasicListResVo>  selectDailyBasicPageList(DailyBasicListReqVo vo){

        Page<DailyBasicListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<DailyBasicListResVo> results = this.baseMapper.selectDailyBasicPageList(page,vo);
        PageResult<DailyBasicListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
