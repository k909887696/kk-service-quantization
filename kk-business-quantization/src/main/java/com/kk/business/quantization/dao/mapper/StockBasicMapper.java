package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.StockBasic;
import com.kk.business.quantization.model.vo.StockBasicListVo;
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

    /**
     * 清空数据
     */
    void truncateTable();

    /**
     * 查询列表
     */
    Page selectPageList(IPage page, StockBasicListVo stockBasicListVo);

}
