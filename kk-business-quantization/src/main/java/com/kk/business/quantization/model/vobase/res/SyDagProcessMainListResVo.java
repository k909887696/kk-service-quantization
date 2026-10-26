package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * DAG执行实例主表列表返回实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema( description = "DAG执行实例主表列表返回实体")
public class SyDagProcessMainListResVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 执行实例编码
    */
    @Schema(description ="执行实例编码")
    private String processId;
    /**
    * 执行实例名称
    */
    @Schema(description ="执行实例名称")
    private String processName;
    /**
    * 创建时间
    */
    @Schema(description ="创建时间")
    private Date createTime;
    /**
    * 创建人编号
    */
    @Schema(description ="创建人编号")
    private String createId;
    /**
    * 创建人名称
    */
    @Schema(description ="创建人名称")
    private String createName;


}
