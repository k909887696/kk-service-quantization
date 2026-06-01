package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexMember;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.IndexMemberListVo;
import com.kk.business.quantization.model.dto.IndexMemberListDto;
import com.kk.business.quantization.model.vo.IndexMemberAddVo;
import com.kk.business.quantization.model.vo.IndexMemberEditVo;
import com.kk.business.quantization.model.dto.IndexMemberDto;
import com.kk.business.quantization.model.vo.IndexMemberDetailsVo;
import com.kk.business.quantization.model.vo.IndexMemberDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业明细 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IIndexMemberService extends IMppService<IndexMember> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<IndexMember> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexMemberListDto>  selectPageList(IndexMemberListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(IndexMemberAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(IndexMemberEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    IndexMemberDto selectById(IndexMemberDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(IndexMemberDeleteVo vo);

}
