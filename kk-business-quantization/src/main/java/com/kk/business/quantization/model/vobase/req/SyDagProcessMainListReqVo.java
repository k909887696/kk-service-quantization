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
 * DAG执行实例主表列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-10-23
 */
@Data
@Schema(description = "DAG执行实例主表列表查询实体")
public class SyDagProcessMainListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 执行实例编码
    */
    @Schema(description = "执行实例编码")
    private String processId;
    /**
    * 执行实例名称
    */
    @Schema(description = "执行实例名称")
    private String processName;
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
    * 创建人编号
    */
    @Schema(description = "创建人编号")
    private String createId;
    /**
    * 创建人名称
    */
    @Schema(description = "创建人名称")
    private String createName;


}
