package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.dao.mapper.InvokeTypeMapper;
import com.kk.business.quantization.service.IInvokeTypeService;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeAddReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeEditReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 系统设置-调度类型 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class InvokeTypeServiceImpl extends ServiceImpl<InvokeTypeMapper, InvokeType> implements IInvokeTypeService {


    /**
    * 分批批量插入系统设置-调度类型
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertInvokeTypeBatchSomeColumn(List<InvokeType> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<InvokeType> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertInvokeType(InvokeTypeAddReqVo vo)
    {
        InvokeType model = new InvokeType();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateInvokeType(InvokeTypeEditReqVo vo)
    {
        InvokeType model = new InvokeType();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-调度类型更新失败!");
        }
        return r;
    }
    /**
    * 单条查询系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public InvokeTypeResVo selectInvokeTypeById(InvokeTypeDetailsReqVo vo)
    {
        InvokeType model = new InvokeType();
        BeanUtil.copyProperties(vo,model);
        InvokeType res = this.baseMapper.selectById(model);
        InvokeTypeResVo resVo = new InvokeTypeResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteInvokeTypeById(InvokeTypeDeleteReqVo vo)
    {
        InvokeType model = new InvokeType();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-调度类型删除失败!");
        }
        return r;
    }
    /**
    * 分页获取系统设置-调度类型结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<InvokeTypeListResVo>  selectInvokeTypePageList(InvokeTypeListReqVo vo){

        Page<InvokeTypeListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<InvokeTypeListResVo> results = this.baseMapper.selectInvokeTypePageList(page,vo);
        PageResult<InvokeTypeListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
