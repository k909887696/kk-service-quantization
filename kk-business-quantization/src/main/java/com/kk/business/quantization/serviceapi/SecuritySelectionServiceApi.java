package com.kk.business.quantization.serviceapi;


import com.kk.common.utils.BeanUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.service.ISecuritySelectionService;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionAddReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionEditReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.annotation.BaseTransactional;
/**
 * <p>
 * 个人自选股 Api服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Service
public class SecuritySelectionServiceApi   {

    @Resource
    public ISecuritySelectionService securitySelectionService;

    /**
    * 分批批量插入个人自选股
    * @param list 数据列表
    * @return
    */
    @BaseTransactional
    public void insertSecuritySelectionBatchSomeColumn(List<SecuritySelection> list)
    {
        securitySelectionService.insertSecuritySelectionBatchSomeColumn(list);
    }
    /**
    * 单条插入个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public void insertSecuritySelection(SecuritySelectionAddReqVo vo)
    {
        securitySelectionService.insertSecuritySelection(vo);
    }
    /**
    * 更新个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int updateSecuritySelection(SecuritySelectionEditReqVo vo)
    {
        int r =securitySelectionService.updateSecuritySelection(vo);
        SecuritySelectionAddReqVo model = new SecuritySelectionAddReqVo();
        BeanUtil.copyProperties(vo, model);
        //securitySelectionService.insertSecuritySelection(model);
        return r;
    }
    /**
    * 单条查询个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public SecuritySelectionResVo selectSecuritySelectionById(SecuritySelectionDetailsReqVo vo)
    {
        SecuritySelectionResVo res =securitySelectionService.selectSecuritySelectionById(vo);

        return res ;
    }
    /**
    * 删除个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional
    public int deleteSecuritySelectionById(SecuritySelectionDeleteReqVo vo)
    {
        return securitySelectionService.deleteSecuritySelectionById(vo);
    }
    /**
    * 分页获取个人自选股结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @BaseTransactional(readOnly = true)
    public PageResult<SecuritySelectionListResVo>  selectSecuritySelectionPageList(SecuritySelectionListReqVo vo){
        return securitySelectionService.selectSecuritySelectionPageList(vo);
    }



}
