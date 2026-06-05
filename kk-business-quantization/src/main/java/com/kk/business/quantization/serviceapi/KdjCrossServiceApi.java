package com.kk.business.quantization.serviceapi;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.business.quantization.service.IKdjCrossService;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossAddReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossEditReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * kdj交叉点 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class KdjCrossServiceApi   {

    @Resource
    public IKdjCrossService kdjCrossService;

    /**
    * 分批批量插入kdj交叉点
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertKdjCrossBatchSomeColumn(List<KdjCross> list)
    {
        kdjCrossService.insertKdjCrossBatchSomeColumn(list);
    }
    /**
    * 单条插入kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertKdjCross(KdjCrossAddReqVo vo)
    {
        kdjCrossService.insertKdjCross(vo);
    }
    /**
    * 更新kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateKdjCross(KdjCrossEditReqVo vo)
    {
        return kdjCrossService.updateKdjCross(vo);
    }
    /**
    * 单条查询kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public KdjCrossResVo selectKdjCrossById(KdjCrossDetailsReqVo vo)
    {
        return kdjCrossService.selectKdjCrossById(vo);
    }
    /**
    * 删除kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteKdjCrossById(KdjCrossDeleteReqVo vo)
    {
        return kdjCrossService.deleteKdjCrossById(vo);
    }
    /**
    * 分页获取kdj交叉点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<KdjCrossListResVo>  selectKdjCrossPageList(KdjCrossListReqVo vo){
        return kdjCrossService.selectKdjCrossPageList(vo);
    }



}
