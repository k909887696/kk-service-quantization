package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.SecuritySelection;
import com.kk.business.quantization.dao.mapper.SecuritySelectionMapper;
import com.kk.business.quantization.service.ISecuritySelectionService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
public class SecuritySelectionServiceImpl extends MppServiceImpl<SecuritySelectionMapper, SecuritySelection> implements ISecuritySelectionService {

    @Resource
    public SecuritySelectionMapper mapper;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<SecuritySelection> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SecuritySelection> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<SecuritySelection>  getPageResult(BasePage vo){

        QueryWrapper<SecuritySelection> query = new QueryWrapper<>();
        IPage<SecuritySelection> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件

        page = mapper.selectPage(page,query);
        PageResult<SecuritySelection>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }


}
