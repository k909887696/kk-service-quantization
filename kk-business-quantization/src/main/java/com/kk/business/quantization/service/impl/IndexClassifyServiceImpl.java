package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.dao.mapper.IndexClassifyMapper;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.kk.business.quantization.model.vobase.req.IndexClassifyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexClassifyResVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexClassifyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 申万行业分类 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexClassifyServiceImpl extends ServiceImpl<IndexClassifyMapper, IndexClassify> implements IIndexClassifyService {


    /**
    * 分批批量插入申万行业分类
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertIndexClassifyBatchSomeColumn(List<IndexClassify> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexClassify> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertIndexClassify(IndexClassifyAddReqVo vo)
    {
        IndexClassify model = new IndexClassify();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateIndexClassify(IndexClassifyEditReqVo vo)
    {
        IndexClassify model = new IndexClassify();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业分类更新失败!");
        }
        return r;
    }
    /**
    * 单条查询申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public IndexClassifyResVo selectIndexClassifyById(IndexClassifyDetailsReqVo vo)
    {
        IndexClassify model = new IndexClassify();
        BeanUtil.copyProperties(vo,model);
        IndexClassify res = this.baseMapper.selectById(model);
        IndexClassifyResVo resVo = new IndexClassifyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除申万行业分类
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteIndexClassifyById(IndexClassifyDeleteReqVo vo)
    {
        IndexClassify model = new IndexClassify();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("申万行业分类删除失败!");
        }
        return r;
    }
    /**
    * 分页获取申万行业分类结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<IndexClassifyListResVo>  selectIndexClassifyPageList(IndexClassifyListReqVo vo){

        Page<IndexClassifyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<IndexClassifyListResVo> results = this.baseMapper.selectIndexClassifyPageList(page,vo);
        PageResult<IndexClassifyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
