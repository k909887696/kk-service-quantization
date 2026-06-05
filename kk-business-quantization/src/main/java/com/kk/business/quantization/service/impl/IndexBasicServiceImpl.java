package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.business.quantization.dao.mapper.IndexBasicMapper;
import com.kk.business.quantization.service.IIndexBasicService;
import com.kk.business.quantization.model.vobase.req.IndexBasicListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicListResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexBasicResVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexBasicDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数基本信息 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexBasicServiceImpl extends ServiceImpl<IndexBasicMapper, IndexBasic> implements IIndexBasicService {


    /**
    * 分批批量插入指数基本信息
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertIndexBasicBatchSomeColumn(List<IndexBasic> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexBasic> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertIndexBasic(IndexBasicAddReqVo vo)
    {
        IndexBasic model = new IndexBasic();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateIndexBasic(IndexBasicEditReqVo vo)
    {
        IndexBasic model = new IndexBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("指数基本信息更新失败!");
        }
        return r;
    }
    /**
    * 单条查询指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public IndexBasicResVo selectIndexBasicById(IndexBasicDetailsReqVo vo)
    {
        IndexBasic model = new IndexBasic();
        BeanUtil.copyProperties(vo,model);
        IndexBasic res = this.baseMapper.selectById(model);
        IndexBasicResVo resVo = new IndexBasicResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除指数基本信息
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteIndexBasicById(IndexBasicDeleteReqVo vo)
    {
        IndexBasic model = new IndexBasic();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("指数基本信息删除失败!");
        }
        return r;
    }
    /**
    * 分页获取指数基本信息结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<IndexBasicListResVo>  selectIndexBasicPageList(IndexBasicListReqVo vo){

        Page<IndexBasicListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<IndexBasicListResVo> results = this.baseMapper.selectIndexBasicPageList(page,vo);
        PageResult<IndexBasicListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
