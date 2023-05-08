package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个股基本信息 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-17
 */

public interface StockBasicMapper extends RootMapper<StockBasic> {

    public void truncateTable();

}
