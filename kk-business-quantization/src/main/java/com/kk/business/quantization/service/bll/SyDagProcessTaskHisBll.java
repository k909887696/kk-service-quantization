package com.kk.business.quantization.service.bll;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SyDagProcessTaskHis;
import com.kk.business.quantization.service.ISyDagProcessTaskHisService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessTaskHisResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessTaskHisDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * DAG执行实例历史任务 Bll业务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessTaskHisBll   {

    @Resource
    public ISyDagProcessTaskHisService syDagProcessTaskHisService;

    /**
    * 分批批量插入DAG执行实例历史任务
    * @param list 数据列表
    * @return
    */
    public void insertSyDagProcessTaskHisBatchSomeColumn(List<SyDagProcessTaskHis> list)
    {
        syDagProcessTaskHisService.insertSyDagProcessTaskHisBatchSomeColumn(list);
    }
    /**
    * 单条插入DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    public void insertSyDagProcessTaskHis(SyDagProcessTaskHisAddReqVo vo)
    {
        syDagProcessTaskHisService.insertSyDagProcessTaskHis(vo);
    }
    /**
    * 更新DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    public int updateSyDagProcessTaskHis(SyDagProcessTaskHisEditReqVo vo)
    {
        return syDagProcessTaskHisService.updateSyDagProcessTaskHis(vo);
    }
    /**
    * 单条查询DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    public SyDagProcessTaskHisResVo selectSyDagProcessTaskHisById(SyDagProcessTaskHisDetailsReqVo vo)
    {
        return syDagProcessTaskHisService.selectSyDagProcessTaskHisById(vo);
    }
    /**
    * 删除DAG执行实例历史任务
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteSyDagProcessTaskHisById(SyDagProcessTaskHisDeleteReqVo vo)
    {
        return syDagProcessTaskHisService.deleteSyDagProcessTaskHisById(vo);
    }
    /**
    * 分页获取DAG执行实例历史任务结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SyDagProcessTaskHisListResVo>  selectSyDagProcessTaskHisPageList(SyDagProcessTaskHisListReqVo vo){
        return syDagProcessTaskHisService.selectSyDagProcessTaskHisPageList(vo);
    }



}

