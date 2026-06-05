package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.IndexWeight;
import com.kk.business.quantization.dao.mapper.IndexWeightMapper;
import com.kk.business.quantization.service.IIndexWeightService;
import com.kk.business.quantization.model.vobase.req.IndexWeightListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightListResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexWeightResVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexWeightDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数成分权重 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexWeightServiceImpl extends ServiceImpl<IndexWeightMapper, IndexWeight> implements IIndexWeightService {


    /**
    * 分批批量插入指数成分权重
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertIndexWeightBatchSomeColumn(List<IndexWeight> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexWeight> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertIndexWeight(IndexWeightAddReqVo vo)
    {
        IndexWeight model = new IndexWeight();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateIndexWeight(IndexWeightEditReqVo vo)
    {
        IndexWeight model = new IndexWeight();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数成分权重更新失败!");
        }
        return r;
    }
    /**
    * 单条查询指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public IndexWeightResVo selectIndexWeightById(IndexWeightDetailsReqVo vo)
    {
        IndexWeight model = new IndexWeight();
        BeanUtil.copyProperties(vo,model);
        IndexWeight res = this.baseMapper.selectByMultiId(model);
        IndexWeightResVo resVo = new IndexWeightResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除指数成分权重
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteIndexWeightById(IndexWeightDeleteReqVo vo)
    {
        IndexWeight model = new IndexWeight();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数成分权重删除失败!");
        }
        return r;
    }
    /**
    * 分页获取指数成分权重结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<IndexWeightListResVo>  selectIndexWeightPageList(IndexWeightListReqVo vo){

        Page<IndexWeightListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<IndexWeightListResVo> results = this.baseMapper.selectIndexWeightPageList(page,vo);
        PageResult<IndexWeightListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
