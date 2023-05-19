package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.dto.IndexDailyListDto;
import com.kk.business.quantization.model.vo.IndexDailyListVo;
/**
 * <p>
 * 指数日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
public interface IndexDailyMapper extends RootMapper<IndexDaily> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, IndexDailyListVo indexDailyListVo);
}
