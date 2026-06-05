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
 * @since 2026-06-04
 */
@Data
@Schema(description = "用户信息列表查询实体")
public class SyUserListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private Date bDayStart;
    private Date bDayEnd;



}
