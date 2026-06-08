package com.kk.business.quantization.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
 import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Data
@TableName("sy_user")
@Schema(description = "用户信息")
public class SyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户账号
    */
    @Schema(description = "用户账号")
    @TableId("user_id")
    private String userId;

    /**
    * 用户名称
    */
    @Schema(description = "用户名称")
    @TableField("user_name")
    private String userName;

    /**
    * 开通时间
    */
    @Schema(description = "开通时间")
    @TableField("join_time")
    private Date joinTime;

    /**
    * 密码
    */
    @Schema(description = "密码")
    @TableField("password")
    private String password;

    /**
    * 账号类型(sm:管理员，su:普通账号）
    */
    @Schema(description = "账号类型(sm:管理员，su:普通账号）")
    @TableField("user_type")
    private String userType;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
    * 邮箱
    */
    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    /**
    * 所属组织架构
    */
    @Schema(description = "所属组织架构")
    @TableField("parent_node_code")
    private String parentNodeCode;

    /**
    * 账号状态（1：启用，0：禁用）
    */
    @Schema(description = "账号状态（1：启用，0：禁用）")
    @TableField("status")
    private String status;

    /**
    * 操作时间
    */
    @Schema(description = "操作时间")
    @TableField("op_time")
    private Date opTime;

    /**
    * 操作人
    */
    @Schema(description = "操作人")
    @TableField("op_id")
    private String opId;

    /**
    * 操作人名称
    */
    @Schema(description = "操作人名称")
    @TableField("op_name")
    private String opName;


}
