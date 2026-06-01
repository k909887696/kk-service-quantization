package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.CnM;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMAddReqVo;
import com.kk.business.quantization.model.vobase.req.CnMEditReqVo;
import com.kk.business.quantization.model.vobase.res.CnMResVo;
import com.kk.business.quantization.model.vobase.req.CnMDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CnMDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 人民币货币总量对象 服务类
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
public interface ICnMService  {

    /**
    * 分批批量插入人民币货币总量对象
    * @param list 数据列表
    * @return
    */
    void insertCnMBatchSomeColumn(List<CnM> list);
    /**
    * 单条插入人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    void insertCnM(CnMAddReqVo vo);
    /**
    * 更新人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    int updateCnM(CnMEditReqVo vo);
    /**
    * 单条查询人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    CnMResVo selectCnMById(CnMDetailsReqVo vo);
    /**
    * 删除人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteCnMById(CnMDeleteReqVo vo);
    /**
    * 分页获取人民币货币总量对象结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<CnMListResVo>  selectCnMPageList(CnMListReqVo vo);
}

