package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.dto.CnMListDto;
import com.kk.business.quantization.model.vo.CnMListVo;
/**
 * <p>
 * 人民币货币总量对象 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface CnMMapper extends RootMapper<CnM> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, CnMListVo cnMListVo);
}
