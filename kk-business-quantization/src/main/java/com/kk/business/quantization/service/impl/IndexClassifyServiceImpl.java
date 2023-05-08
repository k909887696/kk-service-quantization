package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.IndexClassify;
import com.kk.business.quantization.dao.mapper.IndexClassifyMapper;
import com.kk.business.quantization.model.tushare.IndexClassifyVo;
import com.kk.business.quantization.model.tushare.IndexMemberVo;
import com.kk.business.quantization.service.IIndexClassifyService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 申万行业分类 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class IndexClassifyServiceImpl extends MppServiceImpl<IndexClassifyMapper, IndexClassify> implements IIndexClassifyService {

    @Resource
    public IndexClassifyMapper mapper;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<IndexClassify> list)
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
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<IndexClassify>  getPageResult(IndexClassifyVo vo){

        QueryWrapper<IndexClassify> query = new QueryWrapper<>();
        IPage<IndexClassify> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件


        if(StringUtils.isNotBlank(vo.getSrc()))
        {
            query.eq("src",vo.getSrc());
        }

        page = mapper.selectPage(page,query);
        PageResult<IndexClassify>  pageResult = new PageResult<>();


        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
