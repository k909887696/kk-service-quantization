package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagMain;
import com.kk.business.quantization.service.ISyDagMainService;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * DAG主表 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagMainServiceApi   {

    @Resource
    public ISyDagMainService syDagMainService;

    /**
    * 分批批量插入DAG主表
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertSyDagMainBatchSomeColumn(List<SyDagMain> list)
    {
        syDagMainService.insertSyDagMainBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertSyDagMain(SyDagMainAddReqVo vo)
    {
        syDagMainService.insertSyDagMain(vo);
    }
    /**
    * 更新DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateSyDagMain(SyDagMainEditReqVo vo)
    {
        return syDagMainService.updateSyDagMain(vo);
    }
    /**
    * 单条查询DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public SyDagMainResVo selectSyDagMainById(SyDagMainDetailsReqVo vo)
    {
        return syDagMainService.selectSyDagMainById(vo);
    }
    /**
    * 删除DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteSyDagMainById(SyDagMainDeleteReqVo vo)
    {
        return syDagMainService.deleteSyDagMainById(vo);
    }
    /**
    * 分页获取DAG主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<SyDagMainListResVo>  selectSyDagMainPageList(SyDagMainListReqVo vo){
        return syDagMainService.selectSyDagMainPageList(vo);
    }



}
