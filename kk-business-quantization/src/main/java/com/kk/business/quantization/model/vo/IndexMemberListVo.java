package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 申万行业明细列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "申万行业明细列表查询实体", description = "申万行业明细")
public class IndexMemberListVo extends BasePage{

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
    * 纳入日期开始
    */
    @Schema(description = "纳入日期开始")
    private String inDateStart;
    /**
    * 纳入日期结束
    */
    @Schema(description = "纳入日期结束")
    private String inDateEnd;

    /**
    * 	剔除日期开始
    */
    @Schema(description = "	剔除日期开始")
    private String outDateStart;
    /**
    * 	剔除日期结束
    */
    @Schema(description = "	剔除日期结束")
    private String outDateEnd;

    /**
    * 是否最新Y是N否
    */
    @Schema(description = "是否最新Y是N否")
    private String isNew;


}
