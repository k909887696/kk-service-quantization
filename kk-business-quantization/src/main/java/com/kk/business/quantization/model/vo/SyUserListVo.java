package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 用户信息列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "用户信息列表查询实体", description = "用户信息")
public class SyUserListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private Date bDayStart;
    private Date bDayEnd;



}
