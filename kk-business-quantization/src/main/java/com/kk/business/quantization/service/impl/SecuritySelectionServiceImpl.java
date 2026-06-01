package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.dao.mapper.SecuritySelectionMapper;
import com.kk.business.quantization.service.ISecuritySelectionService;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionListReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionListResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionAddReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionEditReqVo;
import com.kk.business.quantization.model.vobase.res.SecuritySelectionResVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SecuritySelectionDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个人自选股 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Service
public class SecuritySelectionServiceImpl extends ServiceImpl<SecuritySelectionMapper, SecuritySelection> implements ISecuritySelectionService {


    /**
    * 分批批量插入个人自选股
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSecuritySelectionBatchSomeColumn(List<SecuritySelection> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SecuritySelection> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSecuritySelection(SecuritySelectionAddReqVo vo)
    {
        SecuritySelection model = new SecuritySelection();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSecuritySelection(SecuritySelectionEditReqVo vo)
    {
        SecuritySelection model = new SecuritySelection();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个人自选股更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SecuritySelectionResVo selectSecuritySelectionById(SecuritySelectionDetailsReqVo vo)
    {
        SecuritySelection model = new SecuritySelection();
        BeanUtil.copyProperties(vo,model);
        SecuritySelection res = this.baseMapper.selectByMultiId(model);
        SecuritySelectionResVo resVo = new SecuritySelectionResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个人自选股
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSecuritySelectionById(SecuritySelectionDeleteReqVo vo)
    {
        SecuritySelection model = new SecuritySelection();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个人自选股删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个人自选股结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SecuritySelectionListResVo>  selectSecuritySelectionPageList(SecuritySelectionListReqVo vo){

        Page<SecuritySelectionListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SecuritySelectionListResVo> results = this.baseMapper.selectSecuritySelectionPageList(page,vo);
        PageResult<SecuritySelectionListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
