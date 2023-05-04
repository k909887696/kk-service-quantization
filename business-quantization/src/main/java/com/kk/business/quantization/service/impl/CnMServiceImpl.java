package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.dao.mapper.CnMMapper;
import com.kk.business.quantization.model.vo.SearchCnMVo;
import com.kk.business.quantization.service.ICnMService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 人民币货币总量 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class CnMServiceImpl extends MppServiceImpl<CnMMapper, CnM> implements ICnMService {

    @Resource
    public CnMMapper mapper;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<CnM> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CnM> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<CnM>  getCnMPageResult(SearchCnMVo vo){

        LambdaQueryWrapper<CnM> query = new LambdaQueryWrapper<>();
        IPage<CnM> page = new Page<>(vo.getPageIndex(),vo.getPageSize());



        //这里开始编写查询条件
        if(StringUtils.isNotBlank(vo.getMonthStart()))
        {
            query.ge(CnM::getMonth,vo.getMonthStart());
        }
        if(StringUtils.isNotBlank(vo.getMonthEnd()))
        {
            query.le(CnM::getMonth,vo.getMonthEnd());
        }

        page = mapper.selectPage(page,query);
        PageResult<CnM>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
