package com.kk.business.quantization.service.bll;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessMain;
import com.kk.business.quantization.service.ISyDagProcessMainService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例主表 Bll业务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessMainBll   {

    @Resource
    public ISyDagProcessMainService syDagProcessMainService;

    /**
    * 分批批量插入DAG执行实例主表
    * @param list 数据列表
    * @return
    */
    public void insertSyDagProcessMainBatchSomeColumn(List<SyDagProcessMain> list)
    {
        syDagProcessMainService.insertSyDagProcessMainBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    public void insertSyDagProcessMain(SyDagProcessMainAddReqVo vo)
    {
        syDagProcessMainService.insertSyDagProcessMain(vo);
    }
    /**
    * 更新DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    public int updateSyDagProcessMain(SyDagProcessMainEditReqVo vo)
    {
        return syDagProcessMainService.updateSyDagProcessMain(vo);
    }
    /**
    * 单条查询DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    public SyDagProcessMainResVo selectSyDagProcessMainById(SyDagProcessMainDetailsReqVo vo)
    {
        return syDagProcessMainService.selectSyDagProcessMainById(vo);
    }
    /**
    * 删除DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteSyDagProcessMainById(SyDagProcessMainDeleteReqVo vo)
    {
        return syDagProcessMainService.deleteSyDagProcessMainById(vo);
    }
    /**
    * 分页获取DAG执行实例主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyDagProcessMainListResVo>  selectSyDagProcessMainPageList(SyDagProcessMainListReqVo vo){
        return syDagProcessMainService.selectSyDagProcessMainPageList(vo);
    }



}

