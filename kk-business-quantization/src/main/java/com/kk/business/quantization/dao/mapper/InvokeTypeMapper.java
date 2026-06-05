package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.InvokeTypeListResVo;
import com.kk.business.quantization.model.vobase.req.InvokeTypeListReqVo;
/**
 * <p>
 * 系统设置-调度类型 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface InvokeTypeMapper extends RootMapper<InvokeType> {
     /**
     * 查询系统设置-调度类型列表
     */
     Page<InvokeTypeListResVo> selectInvokeTypePageList(Page page, InvokeTypeListReqVo invokeTypeListReqVo);
}
