package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 信息发送队列表 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
public interface IInfoSendQueueService extends IMppService<InfoSendQueue> {

    /**
     * 插入
     * @param vo 数据
     * @return
     */
    String insert(InfoSendQueue vo);
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<InfoSendQueue> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<InfoSendQueue> getPageResult(BasePage vo);

    /**
     *  获取需要处理的消息队列
     * @param limit
     * @return
     */
    List<InfoSendQueue>  getPreSendList(int limit);

    /**
     * 更新异常处理信息
     * @param infoId
     * @param exMsg
     * @param sendCount
     * @return
     */
    int updateExMsgAndRunCount(String infoId,String exMsg,int sendCount);

    /**
     * 更新异常处理信息
     * @param infoId
     * @param exMsg
     * @return
     */
    int updateExMsgAndRunCount(String infoId,String exMsg);

}
