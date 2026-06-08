package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 用户信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Data
@Schema(description = "用户信息列表查询实体")
public class SyUserListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 用户账号
    */
    @Schema(description = "用户账号")
    private String userId;
    /**
    * 用户名称
    */
    @Schema(description = "用户名称")
    private String userName;
    /**
    * 开通时间开始
    */
    @Schema(description ="开通时间开始")
    private Date joinTimeStart;
    /**
    * 开通时间结束
    */
    @Schema(description ="开通时间结束")
    private Date joinTimeEnd;

    /**
    * 密码
    */
    @Schema(description = "密码")
    private String password;
    /**
    * 账号类型(sm:管理员，su:普通账号）
    */
    @Schema(description = "账号类型(sm:管理员，su:普通账号）")
    private String userType;
    /**
    * 创建时间开始
    */
    @Schema(description ="创建时间开始")
    private Date createTimeStart;
    /**
    * 创建时间结束
    */
    @Schema(description ="创建时间结束")
    private Date createTimeEnd;

    /**
    * 邮箱
    */
    @Schema(description = "邮箱")
    private String email;
    /**
    * 所属组织架构
    */
    @Schema(description = "所属组织架构")
    private String parentNodeCode;
    /**
    * 账号状态（1：启用，0：禁用）
    */
    @Schema(description = "账号状态（1：启用，0：禁用）")
    private String status;
    /**
    * 操作时间开始
    */
    @Schema(description ="操作时间开始")
    private Date opTimeStart;
    /**
    * 操作时间结束
    */
    @Schema(description ="操作时间结束")
    private Date opTimeEnd;

    /**
    * 操作人
    */
    @Schema(description = "操作人")
    private String opId;
    /**
    * 操作人名称
    */
    @Schema(description = "操作人名称")
    private String opName;


}
