package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.SyDagProcessMain;
import com.kk.business.quantization.dao.mapper.SyDagProcessMainMapper;
import com.kk.business.quantization.service.ISyDagProcessMainService;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainListReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainListResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainAddReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainEditReqVo;
import com.kk.business.quantization.model.vobase.res.SyDagProcessMainResVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.SyDagProcessMainDeleteReqVo;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * DAG执行实例主表 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Service
public class SyDagProcessMainServiceImpl extends ServiceImpl<SyDagProcessMainMapper, SyDagProcessMain> implements ISyDagProcessMainService {


    /**
    * 分批批量插入DAG执行实例主表
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertSyDagProcessMainBatchSomeColumn(List<SyDagProcessMain> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SyDagProcessMain> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            for(SyDagProcessMain model : tempList){
                model.setProcessId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_PROCESS_MAIN, DateUtil.PATTERN_STANDARD02W));
            }
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertSyDagProcessMain(SyDagProcessMainAddReqVo vo)
    {
        SyDagProcessMain model = new SyDagProcessMain();
        BeanUtil.copyProperties(vo,model);
        model.setProcessId(SerialNoUtil.getSingleNextId(SerialNoType.DAG_PROCESS_MAIN, DateUtil.PATTERN_STANDARD02W));
        this.baseMapper.insert(model);
    }
    /**
    * 更新DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateSyDagProcessMain(SyDagProcessMainEditReqVo vo)
    {
        SyDagProcessMain model = new SyDagProcessMain();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例主表更新失败!");
        }
        return r;
    }
    /**
    * 单条查询DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public SyDagProcessMainResVo selectSyDagProcessMainById(SyDagProcessMainDetailsReqVo vo)
    {
        SyDagProcessMain model = new SyDagProcessMain();
        BeanUtil.copyProperties(vo,model);
        SyDagProcessMain res = this.baseMapper.selectById(model);
        SyDagProcessMainResVo resVo = new SyDagProcessMainResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除DAG执行实例主表
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteSyDagProcessMainById(SyDagProcessMainDeleteReqVo vo)
    {
        SyDagProcessMain model = new SyDagProcessMain();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("DAG执行实例主表删除失败!");
        }
        return r;
    }
    /**
    * 分页获取DAG执行实例主表结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<SyDagProcessMainListResVo>  selectSyDagProcessMainPageList(SyDagProcessMainListReqVo vo){

        Page<SyDagProcessMainListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<SyDagProcessMainListResVo> results = this.baseMapper.selectSyDagProcessMainPageList(page,vo);
        PageResult<SyDagProcessMainListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
