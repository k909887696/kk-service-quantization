package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.KdjCross;
import com.kk.business.quantization.dao.mapper.KdjCrossMapper;
import com.kk.business.quantization.service.IKdjCrossService;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossAddReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossEditReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * kdj交叉点 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class KdjCrossServiceImpl extends ServiceImpl<KdjCrossMapper, KdjCross> implements IKdjCrossService {


    /**
    * 分批批量插入kdj交叉点
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertKdjCrossBatchSomeColumn(List<KdjCross> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<KdjCross> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertKdjCross(KdjCrossAddReqVo vo)
    {
        KdjCross model = new KdjCross();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateKdjCross(KdjCrossEditReqVo vo)
    {
        KdjCross model = new KdjCross();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("kdj交叉点更新失败!");
        }
        return r;
    }
    /**
    * 单条查询kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public KdjCrossResVo selectKdjCrossById(KdjCrossDetailsReqVo vo)
    {
        KdjCross model = new KdjCross();
        BeanUtil.copyProperties(vo,model);
        KdjCross res = this.baseMapper.selectByMultiId(model);
        KdjCrossResVo resVo = new KdjCrossResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteKdjCrossById(KdjCrossDeleteReqVo vo)
    {
        KdjCross model = new KdjCross();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("kdj交叉点删除失败!");
        }
        return r;
    }
    /**
    * 分页获取kdj交叉点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<KdjCrossListResVo>  selectKdjCrossPageList(KdjCrossListReqVo vo){

        Page<KdjCrossListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<KdjCrossListResVo> results = this.baseMapper.selectKdjCrossPageList(page,vo);
        PageResult<KdjCrossListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
