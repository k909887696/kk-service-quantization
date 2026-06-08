package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 用户信息删除实体
 * </p>
 *
 * @author kk
 * @since 2026-06-08
 */
@Data
@Schema(description = "用户信息删除实体")
public class SyUserDeleteReqVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 用户账号
    */
    @Schema(description ="用户账号")
    private String userId;


}
