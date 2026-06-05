package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.service.ICnMService;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMAddReqVo;
import com.kk.business.quantization.model.vobase.req.CnMEditReqVo;
import com.kk.business.quantization.model.vobase.res.CnMResVo;
import com.kk.business.quantization.model.vobase.req.CnMDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CnMDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 人民币货币总量对象 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CnMServiceApi   {

    @Resource
    public ICnMService cnMService;

    /**
    * 分批批量插入人民币货币总量对象
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertCnMBatchSomeColumn(List<CnM> list)
    {
        cnMService.insertCnMBatchSomeColumn(list);
    }
    /**
    * 单条插入人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertCnM(CnMAddReqVo vo)
    {
        cnMService.insertCnM(vo);
    }
    /**
    * 更新人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateCnM(CnMEditReqVo vo)
    {
        return cnMService.updateCnM(vo);
    }
    /**
    * 单条查询人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public CnMResVo selectCnMById(CnMDetailsReqVo vo)
    {
        return cnMService.selectCnMById(vo);
    }
    /**
    * 删除人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteCnMById(CnMDeleteReqVo vo)
    {
        return cnMService.deleteCnMById(vo);
    }
    /**
    * 分页获取人民币货币总量对象结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<CnMListResVo>  selectCnMPageList(CnMListReqVo vo){
        return cnMService.selectCnMPageList(vo);
    }



}
