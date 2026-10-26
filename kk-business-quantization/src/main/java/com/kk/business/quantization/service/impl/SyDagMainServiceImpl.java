package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyDagFlow;
import com.kk.business.quantization.dao.entity.SyDagMain;
import com.kk.business.quantization.dao.mapper.SyDagMainMapper;
import com.kk.business.quantization.service.ISyDagFlowService;
import com.kk.business.quantization.service.ISyDagMainService;
import com.kk.business.quantization.model.vobase.req.SyDagFlowAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainListResVo;
import com.kk.business.quantization.model.vobase.res.SyDagFlowListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagMainDeleteReqVo;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG主表 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagMainServiceImpl extends ServiceImpl<SyDagMainMapper, SyDagMain> implements ISyDagMainService {


    @Resource
    private ISyDagFlowService syDagFlowService;

    /**
    * 分批批量插入DAG主表
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagMainBatchSomeColumn(List<SyDagMain> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagMain> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            for(SyDagMain model : tempList){
                model.setDagId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_MAIN, DateUtil.PATTERN_STANDARD02W));
            }
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagMain(SyDagMainAddReqVo vo)
    {
        SyDagMain model = new SyDagMain();
        BeanUtil.copyProperties(vo,model);
        model.setDagId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_MAIN, DateUtil.PATTERN_STANDARD02W));
        this.baseMapper.insert(model);
        // 保存DAG流程数据
        if(vo.getDagFlowList() != null && !vo.getDagFlowList().isEmpty())
        {
            String dagId = model.getDagId();
            for(SyDagFlowAddReqVo flowVo : vo.getDagFlowList())
            {
                flowVo.setDagId(dagId);
                syDagFlowService.insertSyDagFlow(flowVo);
            }
        }
    }
    /**
    * 更新DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagMain(SyDagMainEditReqVo vo)
    {
        SyDagMain model = new SyDagMain();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG主表更新失败!");
        }
        // 更新DAG流程数据：先删除旧的，再插入新的
        if(vo.getDagFlowList() != null)
        {
            String dagId = vo.getDagId();
            // 删除旧的流程数据
            syDagFlowService.deleteSyDagFlowByDagId(dagId);
            // 插入新的流程数据
            if(!vo.getDagFlowList().isEmpty())
            {
                for(SyDagFlowAddReqVo flowVo : vo.getDagFlowList())
                {
                    flowVo.setDagId(dagId);
                    syDagFlowService.insertSyDagFlow(flowVo);
                }
            }
        }
        return r;
    }
    /**
    * 单条查询DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagMainResVo selectSyDagMainById(SyDagMainDetailsReqVo vo)
    {
        SyDagMain model = new SyDagMain();
        BeanUtil.copyProperties(vo,model);
        SyDagMain res = this.baseMapper.selectById(model);
        SyDagMainResVo resVo = new SyDagMainResVo();
        BeanUtil.copyProperties(res,resVo);
        // 查询关联的DAG流程数据
        if(resVo.getDagId() != null)
        {
            List<SyDagFlowListResVo> flowResList = syDagFlowService.selectSyDagFlowListByDagId(resVo.getDagId());
            if(flowResList != null && !flowResList.isEmpty())
            {
                resVo.setDagFlowList(flowResList);
            }
        }
        return resVo;
    }
    /**
    * 删除DAG主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagMainById(SyDagMainDeleteReqVo vo)
    {
        SyDagMain model = new SyDagMain();
        BeanUtil.copyProperties(vo,model);
        // 先删除关联的DAG流程数据
        if(model.getDagId() != null)
        {
            syDagFlowService.deleteSyDagFlowByDagId(model.getDagId());
        }
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG主表删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagMainListResVo>  selectSyDagMainPageList(SyDagMainListReqVo vo){

        Page<SyDagMainListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagMainListResVo> results = this.baseMapper.selectSyDagMainPageList(page,vo);
        PageResult<SyDagMainListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
