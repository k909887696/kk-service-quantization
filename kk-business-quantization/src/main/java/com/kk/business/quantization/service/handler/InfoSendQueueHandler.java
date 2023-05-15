package com.kk.business.quantization.service.handler;

import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.dao.mapper.InfoSendQueueHistoryMapper;
import com.kk.business.quantization.dao.mapper.InfoSendQueueMapper;
import com.kk.common.base.email.EmailSendMsg;
import com.kk.common.base.email.EmailUtil;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: kk
 * @Date: 2021/12/29 16:08
 * 消息队列发送处理器
 */
@Service
public class InfoSendQueueHandler {

    @Value("${spring.mail.from}")
    public String defaultSender;
    @Resource
    public InfoSendQueueMapper infoSendQueueMapper;
    @Resource
    public InfoSendQueueHistoryMapper infoSendQueueHistoryMapper;
    @Resource
    public EmailUtil emailUtil;
    @Resource
    public MapperUtils mapperUtils;

    /**
     * 消息队列发送处理
     * @param info
     */
    public void handlerInfoSendQueue(InfoSendQueue info)
    {

        infoSendQueueMapper.deleteById(info.getInfoId());//删除任务

        info.setSendTime(new Date());//记录执行时间
        if(StringUtils.isBlank(info.getSender()))
            info.setSender(defaultSender);



        if(StringUtils.isBlank(info.getReciver())) {
            infoSendQueueMapper.updateExMsgAndRunCount(info.getInfoId(),"消息接收人为空！",10);
            return ;
        }
        if(StringUtils.isBlank(info.getInfoMsg())) {
            infoSendQueueMapper.updateExMsgAndRunCount(info.getInfoId(),"消息为空！",10);
            return ;
        }
        switch (info.getInfoType())
        {
            case "email":sendEmail(info);break;
            case "sms":break;
            case "gzh":break;
            default:break;
        }


        //迁移到历史表
        infoSendQueueHistoryMapper.insert(mapperUtils.map(info, InfoSendQueueHistory.class));
    }

    /**
     * 发送邮件
     * @param info
     */
    private void sendEmail(InfoSendQueue info)
    {
        EmailSendMsg emailSendMsg = (EmailSendMsg)JsonUtil.parseObject(info.getInfoMsg(),EmailSendMsg.class);
        if(StringUtils.isBlank(emailSendMsg.getFrom())) {
            emailSendMsg.setFrom(info.getSender());
        }

        if(emailSendMsg.getTo()==null || emailSendMsg.getTo().size()<=0) {

            emailSendMsg.setTo(Arrays.asList(info.getReciver().split(",")));
        }


        emailUtil.sendMimeMail(emailSendMsg);
    }

}
