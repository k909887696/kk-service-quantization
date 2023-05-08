package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.constant.SerialNoType;
import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.business.quantization.dao.mapper.InfoSendQueueMapper;
import com.kk.business.quantization.service.IInfoSendQueueService;
import com.kk.business.quantization.utils.SerialNoUtil;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 信息发送队列表 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
@Service
public class InfoSendQueueServiceImpl extends MppServiceImpl<InfoSendQueueMapper, InfoSendQueue> implements IInfoSendQueueService {

    @Resource
    public InfoSendQueueMapper mapper;

    /**
     * 插入
     * @param vo 数据
     * @return
     */
    public String insert(InfoSendQueue vo)
    {
       String selialno = SerialNoUtil.getSingleNextId(SerialNoType.INFO_SEND_QUEUE, DateUtil.PATTERN_STANDARD06W,10);
       vo.setInfoId(selialno);
       mapper.insert(vo);
       return selialno;
    }
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<InfoSendQueue> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        List<String> selialNoList = SerialNoUtil.getMultiNextId(SerialNoType.INFO_SEND_QUEUE, DateUtil.PATTERN_STANDARD06W,10,totalCount);

        for (int i=0;i<totalCount;i++
        ) {
            list.get(i).setInfoId(selialNoList.get(i));
        }

        for(;index<=totalPage;index++)
        {
            List<InfoSendQueue> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            mapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<InfoSendQueue>  getPageResult(BasePage vo){

        QueryWrapper<InfoSendQueue> query = new QueryWrapper<>();
        IPage<InfoSendQueue> page = new Page<>(vo.getPageIndex(),vo.getPageSize());

        //这里开始编写查询条件

        page = mapper.selectPage(page,query);
        PageResult<InfoSendQueue>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     *  获取需要处理的消息队列
     * @param limit
     * @return
     */
    public List<InfoSendQueue>  getPreSendList(int limit){

        LambdaQueryWrapper<InfoSendQueue> query = new LambdaQueryWrapper<>();
        IPage<InfoSendQueue> page = new Page<>(1,limit <=0 ? 10:limit);

        //这里开始编写查询条件
        query.le(InfoSendQueue::getPreSendTime,new Date());
        query.lt(InfoSendQueue::getSendCount,10);

        query.orderByAsc(InfoSendQueue::getPreSendTime,InfoSendQueue::getSendCount);
        page = mapper.selectPage(page,query);

        return page.getRecords();
    }

    /**
     * 更新异常处理信息
     * @param infoId
     * @param exMsg
     * @param sendCount
     * @return
     */
    public int updateExMsgAndRunCount(String infoId,String exMsg,int sendCount)
    {
        return mapper.updateExMsgAndRunCount(infoId,exMsg,sendCount);
    }

    /**
     * 更新异常处理信息
     * @param infoId
     * @param exMsg
     * @return
     */
    public int updateExMsgAndRunCount(String infoId,String exMsg)
    {
        return mapper.updateExMsgAndRunCount(infoId,exMsg,1);
    }
}
