package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.service.IInvokeTypeService;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeAddReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeEditReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 系统设置-调度类型 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class InvokeTypeServiceApi   {

    @Resource
    public IInvokeTypeService invokeTypeService;

    /**
    * 分批批量插入系统设置-调度类型
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertInvokeTypeBatchSomeColumn(List<InvokeType> list)
    {
        invokeTypeService.insertInvokeTypeBatchSomeColumn(list);
    }
    /**
    * 单条插入系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertInvokeType(InvokeTypeAddReqVo vo)
    {
        invokeTypeService.insertInvokeType(vo);
    }
    /**
    * 更新系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateInvokeType(InvokeTypeEditReqVo vo)
    {
        return invokeTypeService.updateInvokeType(vo);
    }
    /**
    * 单条查询系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public InvokeTypeResVo selectInvokeTypeById(InvokeTypeDetailsReqVo vo)
    {
        return invokeTypeService.selectInvokeTypeById(vo);
    }
    /**
    * 删除系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteInvokeTypeById(InvokeTypeDeleteReqVo vo)
    {
        return invokeTypeService.deleteInvokeTypeById(vo);
    }
    /**
    * 分页获取系统设置-调度类型结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<InvokeTypeListResVo>  selectInvokeTypePageList(InvokeTypeListReqVo vo){
        return invokeTypeService.selectInvokeTypePageList(vo);
    }



}
