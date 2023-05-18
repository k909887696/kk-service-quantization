package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.SecuritySelectionListVo;
import com.kk.business.quantization.model.dto.SecuritySelectionListDto;
import com.kk.business.quantization.model.vo.SecuritySelectionAddVo;
import com.kk.business.quantization.model.vo.SecuritySelectionEditVo;
import com.kk.business.quantization.model.dto.SecuritySelectionDto;
import com.kk.business.quantization.model.vo.SecuritySelectionDetailsVo;
import com.kk.business.quantization.model.vo.SecuritySelectionDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个人自选股 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface ISecuritySelectionService extends IMppService<SecuritySelection> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<SecuritySelection> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SecuritySelectionListDto>  selectPageList(SecuritySelectionListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(SecuritySelectionAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(SecuritySelectionEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    SecuritySelectionDto selectById(SecuritySelectionDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(SecuritySelectionDeleteVo vo);

}
