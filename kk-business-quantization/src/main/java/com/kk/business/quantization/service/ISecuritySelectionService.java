package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.SecuritySelection;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionAddReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionEditReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个人自选股 服务类
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
public interface ISecuritySelectionService  {

    /**
    * 分批批量插入个人自选股
    * @param list 数据列表
    * @return
    */
    void insertSecuritySelectionBatchSomeColumn(List<SecuritySelection> list);
    /**
    * 单条插入个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    void insertSecuritySelection(SecuritySelectionAddReqVo vo);
    /**
    * 更新个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    int updateSecuritySelection(SecuritySelectionEditReqVo vo);
    /**
    * 单条查询个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    SecuritySelectionResVo selectSecuritySelectionById(SecuritySelectionDetailsReqVo vo);
    /**
    * 删除个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteSecuritySelectionById(SecuritySelectionDeleteReqVo vo);
    /**
    * 分页获取个人自选股结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SecuritySelectionListResVo>  selectSecuritySelectionPageList(SecuritySelectionListReqVo vo);
}

