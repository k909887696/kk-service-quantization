package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.ConceptDaily;
import com.kk.business.quantization.dao.mapper.ConceptDailyMapper;
import com.kk.business.quantization.service.IConceptDailyService;
import com.kk.business.quantization.model.vobase.req.ConceptDailyListReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyListResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.ConceptDailyResVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.ConceptDailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 概念日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class ConceptDailyServiceImpl extends ServiceImpl<ConceptDailyMapper, ConceptDaily> implements IConceptDailyService {


    /**
    * 分批批量插入概念日线行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertConceptDailyBatchSomeColumn(List<ConceptDaily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<ConceptDaily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertConceptDaily(ConceptDailyAddReqVo vo)
    {
        ConceptDaily model = new ConceptDaily();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateConceptDaily(ConceptDailyEditReqVo vo)
    {
        ConceptDaily model = new ConceptDaily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念日线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public ConceptDailyResVo selectConceptDailyById(ConceptDailyDetailsReqVo vo)
    {
        ConceptDaily model = new ConceptDaily();
        BeanUtil.copyProperties(vo,model);
        ConceptDaily res = this.baseMapper.selectByMultiId(model);
        ConceptDailyResVo resVo = new ConceptDailyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除概念日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteConceptDailyById(ConceptDailyDeleteReqVo vo)
    {
        ConceptDaily model = new ConceptDaily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("概念日线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取概念日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<ConceptDailyListResVo>  selectConceptDailyPageList(ConceptDailyListReqVo vo){

        Page<ConceptDailyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<ConceptDailyListResVo> results = this.baseMapper.selectConceptDailyPageList(page,vo);
        PageResult<ConceptDailyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }
    /**
     * 查询区间涨幅最大概念
     * @param vo
     * @return
     */
    @Override
    public PageResult<DailyLeaderDto> selectConceptLeaderListByRange(SearchDailyLeaderVo vo)
    {
        IPage<DailyLeaderDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectConceptLeaderListByRange(page,vo);
        PageResult<DailyLeaderDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
