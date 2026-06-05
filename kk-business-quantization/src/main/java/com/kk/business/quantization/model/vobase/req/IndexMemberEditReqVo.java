package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 申万行业明细编辑实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "申万行业明细编辑实体")
public class IndexMemberEditReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @Schema(description = "指数代码")
    private String indexCode;
    /**
    * 指数名称
    */
    @Schema(description = "指数名称")
    private String indexName;
    /**
    * 成分股票代码
    */
    @Schema(description = "成分股票代码")
    private String conCode;
    /**
    * 成分股票名称
    */
    @Schema(description = "成分股票名称")
    private String conName;
    /**
    * 纳入日期
    */
    @Schema(description = "纳入日期")
    private String inDate;
    /**
    * 	剔除日期
    */
    @Schema(description = "	剔除日期")
    private String outDate;
    /**
    * 是否最新Y是N否
    */
    @Schema(description = "是否最新Y是N否")
    private String isNew;


}
