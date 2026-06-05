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
 * @since 2026-06-04
 */
@Data
@TableName("sy_user")
@Schema(description = "用户信息")
public class SyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("b_day")
    private Date bDay;


}
