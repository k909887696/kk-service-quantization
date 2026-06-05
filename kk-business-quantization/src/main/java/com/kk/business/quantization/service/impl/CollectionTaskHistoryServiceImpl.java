package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.dao.mapper.CollectionTaskHistoryMapper;
import com.kk.business.quantization.service.ICollectionTaskHistoryService;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 系统设置-数据任务-历史 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Service
public class CollectionTaskHistoryServiceImpl extends ServiceImpl<CollectionTaskHistoryMapper, CollectionTaskHistory> implements ICollectionTaskHistoryService {


    /**
    * 分批批量插入系统设置-数据任务-历史
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertCollectionTaskHistoryBatchSomeColumn(List<CollectionTaskHistory> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CollectionTaskHistory> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertCollectionTaskHistory(CollectionTaskHistoryAddReqVo vo)
    {
        CollectionTaskHistory model = new CollectionTaskHistory();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateCollectionTaskHistory(CollectionTaskHistoryEditReqVo vo)
    {
        CollectionTaskHistory model = new CollectionTaskHistory();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据任务-历史更新失败!");
        }
        return r;
    }
    /**
    * 单条查询系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public CollectionTaskHistoryResVo selectCollectionTaskHistoryById(CollectionTaskHistoryDetailsReqVo vo)
    {
        CollectionTaskHistory model = new CollectionTaskHistory();
        BeanUtil.copyProperties(vo,model);
        CollectionTaskHistory res = this.baseMapper.selectById(model);
        CollectionTaskHistoryResVo resVo = new CollectionTaskHistoryResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除系统设置-数据任务-历史
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteCollectionTaskHistoryById(CollectionTaskHistoryDeleteReqVo vo)
    {
        CollectionTaskHistory model = new CollectionTaskHistory();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("系统设置-数据任务-历史删除失败!");
        }
        return r;
    }
    /**
    * 分页获取系统设置-数据任务-历史结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<CollectionTaskHistoryListResVo>  selectCollectionTaskHistoryPageList(CollectionTaskHistoryListReqVo vo){

        Page<CollectionTaskHistoryListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<CollectionTaskHistoryListResVo> results = this.baseMapper.selectCollectionTaskHistoryPageList(page,vo);
        PageResult<CollectionTaskHistoryListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
