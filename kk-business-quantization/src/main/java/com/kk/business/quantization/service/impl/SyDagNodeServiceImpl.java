package com.kk.business.quantization.service.impl;


import com.kk.common.auth.LoginUserInfo;
import com.kk.common.auth.LoginUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyDagNode;
import com.kk.business.quantization.dao.mapper.SyDagNodeMapper;
import com.kk.business.quantization.service.ISyDagNodeService;
import com.kk.business.quantization.model.vobase.req.SyDagNodeListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagNodeResVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagNodeDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG执行节点 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagNodeServiceImpl extends ServiceImpl<SyDagNodeMapper, SyDagNode> implements ISyDagNodeService {


    /**
    * 分批批量插入DAG执行节点
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagNodeBatchSomeColumn(List<SyDagNode> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagNode> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagNode(SyDagNodeAddReqVo vo)
    {
        LoginUserInfo loginUserInfo = LoginUtil.getLoginUserInfo();
        vo.setCreateTime(new java.util.Date()); // Set the create time to the current date and time
        vo.setCreateId(loginUserInfo.getUserId());
        vo.setCreateName(loginUserInfo.getUserName());
        SyDagNode model = new SyDagNode();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagNode(SyDagNodeEditReqVo vo)
    {
        SyDagNode model = new SyDagNode();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行节点更新失败!");
        }
        return r;
    }
    /**
    * 单条查询DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagNodeResVo selectSyDagNodeById(SyDagNodeDetailsReqVo vo)
    {
        SyDagNode model = new SyDagNode();
        BeanUtil.copyProperties(vo,model);
        SyDagNode res = this.baseMapper.selectById(model);
        SyDagNodeResVo resVo = new SyDagNodeResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除DAG执行节点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagNodeById(SyDagNodeDeleteReqVo vo)
    {
        SyDagNode model = new SyDagNode();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行节点删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG执行节点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagNodeListResVo>  selectSyDagNodePageList(SyDagNodeListReqVo vo){

        Page<SyDagNodeListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagNodeListResVo> results = this.baseMapper.selectSyDagNodePageList(page,vo);
        PageResult<SyDagNodeListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
