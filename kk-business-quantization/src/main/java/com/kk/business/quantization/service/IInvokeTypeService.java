package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.InvokeType;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeAddReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeEditReqVo;
import com.kk.business.quantization.model.vobase.res.InvokeTypeResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-调度类型 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IInvokeTypeService  {

    /**
    * 分批批量插入系统设置-调度类型
    * @param list 数据列表
    * @return
    */
    void insertInvokeTypeBatchSomeColumn(List<InvokeType> list);
    /**
    * 单条插入系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    void insertInvokeType(InvokeTypeAddReqVo vo);
    /**
    * 更新系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    int updateInvokeType(InvokeTypeEditReqVo vo);
    /**
    * 单条查询系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    InvokeTypeResVo selectInvokeTypeById(InvokeTypeDetailsReqVo vo);
    /**
    * 删除系统设置-调度类型
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteInvokeTypeById(InvokeTypeDeleteReqVo vo);
    /**
    * 分页获取系统设置-调度类型结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<InvokeTypeListResVo>  selectInvokeTypePageList(InvokeTypeListReqVo vo);
}

