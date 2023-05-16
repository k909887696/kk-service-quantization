package com.kk.business.quantization.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.IndexMember;
import com.kk.business.quantization.model.vo.IndexMemberListVo;
import com.kk.common.dao.mapper.RootMapper;
/**
 * <p>
 * 申万行业明细	 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2023-05-16
 */
public interface IndexMemberMapper extends RootMapper<IndexMember> {
     /**
     * 查询列表
     */
     Page selectPageList(IPage page, IndexMemberListVo indexMemberListVo);
}
