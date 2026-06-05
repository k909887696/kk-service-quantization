package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.kk.business.quantization.dao.mapper.MaxPctChgMapper;
import com.kk.business.quantization.service.IMaxPctChgService;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgAddReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgEditReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 各个市场涨跌幅限制 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class MaxPctChgServiceImpl extends ServiceImpl<MaxPctChgMapper, MaxPctChg> implements IMaxPctChgService {


    /**
    * 分批批量插入各个市场涨跌幅限制
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertMaxPctChgBatchSomeColumn(List<MaxPctChg> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<MaxPctChg> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertMaxPctChg(MaxPctChgAddReqVo vo)
    {
        MaxPctChg model = new MaxPctChg();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateMaxPctChg(MaxPctChgEditReqVo vo)
    {
        MaxPctChg model = new MaxPctChg();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("各个市场涨跌幅限制更新失败!");
        }
        return r;
    }
    /**
    * 单条查询各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public MaxPctChgResVo selectMaxPctChgById(MaxPctChgDetailsReqVo vo)
    {
        MaxPctChg model = new MaxPctChg();
        BeanUtil.copyProperties(vo,model);
        MaxPctChg res = this.baseMapper.selectById(model);
        MaxPctChgResVo resVo = new MaxPctChgResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteMaxPctChgById(MaxPctChgDeleteReqVo vo)
    {
        MaxPctChg model = new MaxPctChg();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("各个市场涨跌幅限制删除失败!");
        }
        return r;
    }
    /**
    * 分页获取各个市场涨跌幅限制结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<MaxPctChgListResVo>  selectMaxPctChgPageList(MaxPctChgListReqVo vo){

        Page<MaxPctChgListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<MaxPctChgListResVo> results = this.baseMapper.selectMaxPctChgPageList(page,vo);
        PageResult<MaxPctChgListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
