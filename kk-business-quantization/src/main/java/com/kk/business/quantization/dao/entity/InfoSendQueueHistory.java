package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 信息发送队列历史表
 * </p>
 *
 * @author kk
 * @since 2021-12-29
 */
@TableName("info_send_queue_history")
@ApiModel(value = "信息发送队列历史表对象", description = "信息发送队列历史表")
public class InfoSendQueueHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 信息编号
    */
    @ApiModelProperty("信息编号")
    @TableId("info_id")
    private String infoId;

    /**
    * 信息内容
    */
    @ApiModelProperty("信息内容")
    @TableField("info_msg")
    private String infoMsg;

    /**
    * 接收人，可多个用逗号隔开
    */
    @ApiModelProperty("接收人，可多个用逗号隔开")
    @TableField("reciver")
    private String reciver;

    /**
    * 发送者，可空
    */
    @ApiModelProperty("发送者，可空")
    @TableField("sender")
    private String sender;

    /**
    * 发送时间
    */
    @ApiModelProperty("发送时间")
    @TableField("send_time")
    private Date sendTime;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 信息类型（email：邮件，sms：短信，gzh：公众号）
    */
    @ApiModelProperty("信息类型（email：邮件，sms：短信，gzh：公众号）")
    @TableField("info_type")
    private String infoType;

    /**
    * 执行次数（大于30次，不再处理）
    */
    @ApiModelProperty("执行次数（大于30次，不再处理）")
    @TableField("send_count")
    private Integer sendCount;

    /**
    * 异常信息
    */
    @ApiModelProperty("异常信息")
    @TableField("ex_msg")
    private String exMsg;

    /**
    * 预定发送时间
    */
    @ApiModelProperty("预定发送时间")
    @TableField("pre_send_time")
    private Date preSendTime;

    /**
    * 发送异常时间
    */
    @ApiModelProperty("发送异常时间")
    @TableField("ex_time")
    private Date exTime;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
    public String getInfoMsg() {
        return infoMsg;
    }

    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg;
    }
    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }
    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }
    public Date getPreSendTime() {
        return preSendTime;
    }

    public void setPreSendTime(Date preSendTime) {
        this.preSendTime = preSendTime;
    }
    public Date getExTime() {
        return exTime;
    }

    public void setExTime(Date exTime) {
        this.exTime = exTime;
    }

    @Override
    public String toString() {
        return "InfoSendQueueHistory{" +
            "infoId=" + infoId +
            ", infoMsg=" + infoMsg +
            ", reciver=" + reciver +
            ", sender=" + sender +
            ", sendTime=" + sendTime +
            ", createTime=" + createTime +
            ", infoType=" + infoType +
            ", sendCount=" + sendCount +
            ", exMsg=" + exMsg +
            ", preSendTime=" + preSendTime +
            ", exTime=" + exTime +
        "}";
    }
}
