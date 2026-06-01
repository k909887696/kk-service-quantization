package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.model.vo.InvokeTypeListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 系统设置-调度类型	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface InvokeTypeMapper extends RootMapper<InvokeType> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, InvokeTypeListVo invokeTypeListVo);
}
