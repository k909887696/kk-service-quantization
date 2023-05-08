package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.InfoSendQueue;
import com.kk.common.dao.mapper.RootMapper;

/**
 * <p>
 * 信息发送队列表 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
public interface InfoSendQueueMapper extends RootMapper<InfoSendQueue> {

    /**
     * 发送消息调度出错记录
     * @param infoId
     * @param exMsg
     * @return
     */
    public int updateExMsgAndRunCount(String infoId,String exMsg,Integer sendCount);

}
