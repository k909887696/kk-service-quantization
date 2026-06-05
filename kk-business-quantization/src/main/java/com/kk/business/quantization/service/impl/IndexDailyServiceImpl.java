package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.IndexDaily;
import com.kk.business.quantization.dao.mapper.IndexDailyMapper;
import com.kk.business.quantization.service.IIndexDailyService;
import com.kk.business.quantization.model.vobase.req.IndexDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyListResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.IndexDailyResVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.IndexDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 指数日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class IndexDailyServiceImpl extends ServiceImpl<IndexDailyMapper, IndexDaily> implements IIndexDailyService {


    /**
    * 分批批量插入指数日线行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertIndexDailyBatchSomeColumn(List<IndexDaily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<IndexDaily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertIndexDaily(IndexDailyAddReqVo vo)
    {
        IndexDaily model = new IndexDaily();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateIndexDaily(IndexDailyEditReqVo vo)
    {
        IndexDaily model = new IndexDaily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数日线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public IndexDailyResVo selectIndexDailyById(IndexDailyDetailsReqVo vo)
    {
        IndexDaily model = new IndexDaily();
        BeanUtil.copyProperties(vo,model);
        IndexDaily res = this.baseMapper.selectByMultiId(model);
        IndexDailyResVo resVo = new IndexDailyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除指数日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteIndexDailyById(IndexDailyDeleteReqVo vo)
    {
        IndexDaily model = new IndexDaily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("指数日线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取指数日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<IndexDailyListResVo>  selectIndexDailyPageList(IndexDailyListReqVo vo){

        Page<IndexDailyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<IndexDailyListResVo> results = this.baseMapper.selectIndexDailyPageList(page,vo);
        PageResult<IndexDailyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
