package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 系统设置-数据策略删除实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@ApiModel(value = "系统设置-数据策略删除实体", description = "系统设置-数据策略")
public class CollectionPolicyDeleteVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 策略编号
    */
    @ApiModelProperty("策略编号")
    private String policyId;


}
