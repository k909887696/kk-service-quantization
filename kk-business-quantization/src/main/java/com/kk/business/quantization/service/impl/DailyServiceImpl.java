package com.kk.business.quantization.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kk.business.quantization.model.dto.DailyKdjDto;
import com.kk.business.quantization.model.dto.DailyLeaderDto;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.dao.mapper.DailyMapper;
import com.kk.business.quantization.service.IDailyService;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyAddReqVo;
import com.kk.business.quantization.model.vobase.req.DailyEditReqVo;
import com.kk.business.quantization.model.vobase.res.DailyResVo;
import com.kk.business.quantization.model.vobase.req.DailyDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.DailyDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 个股日线行情 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements IDailyService {


    /**
    * 分批批量插入个股日线行情
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertDailyBatchSomeColumn(List<Daily> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<Daily> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertDuplicateKeyUpdate(tempList);
        }
    }
    /**
    * 单条插入个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertDaily(DailyAddReqVo vo)
    {
        Daily model = new Daily();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateDaily(DailyEditReqVo vo)
    {
        Daily model = new Daily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股日线行情更新失败!");
        }
        return r;
    }
    /**
    * 单条查询个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public DailyResVo selectDailyById(DailyDetailsReqVo vo)
    {
        Daily model = new Daily();
        BeanUtil.copyProperties(vo,model);
        Daily res = this.baseMapper.selectByMultiId(model);
        DailyResVo resVo = new DailyResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除个股日线行情
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteDailyById(DailyDeleteReqVo vo)
    {
        Daily model = new Daily();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("个股日线行情删除失败!");
        }
        return r;
    }
    /**
    * 分页获取个股日线行情结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<DailyListResVo>  selectDailyPageList(DailyListReqVo vo){

        Page<DailyListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<DailyListResVo> results = this.baseMapper.selectDailyPageList(page,vo);
        PageResult<DailyListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    @Override
    public PageResult<DailyKdjDto>  getPageResultEx(SearchDailyVo vo) {


        IPage<DailyKdjDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        page = this.baseMapper.selectDailyExList(page,vo);
        PageResult<DailyKdjDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }

    /**
     * 获取区间涨幅最好的概念
     * @param vo
     * @return
     */
    @Override
    public PageResult<DailyLeaderDto> selectStockLeader(SearchDailyLeaderVo vo)
    {
        IPage<DailyLeaderDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        page = this.baseMapper.selectStockLeader(page,vo);
        PageResult<DailyLeaderDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());
        return pageResult;
    }


}
