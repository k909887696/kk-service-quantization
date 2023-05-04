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
 * 
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@TableName("sy_user")
@ApiModel(value = "对象", description = "")
public class SyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户账号
    */
    @ApiModelProperty("用户账号")
    @TableId("user_id")
    private String userId;

    /**
    * 用户账号
    */
    @ApiModelProperty("用户账号")
    @TableField("user_name")
    private String userName;

    /**
    * 用户账号
    */
    @ApiModelProperty("用户账号")
    @TableField("user_password")
    private String userPassword;

    /**
    * 用户账号
    */
    @ApiModelProperty("用户账号")
    @TableField("create_time")
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SyUser{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userPassword=" + userPassword +
            ", createTime=" + createTime +
        "}";
    }
}
