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
 * 概念分类列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "概念分类列表查询实体", description = "概念分类")
public class ConceptListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @Schema(description = "概念代码")
    private String code;
    /**
    * 概念名称
    */
    @Schema(description = "概念名称")
    private String name;
    /**
    * 来源 ts:tushare,ths:同花顺 90：东方财富
    */
    @Schema(description = "来源 ts:tushare,ths:同花顺 90：东方财富")
    private String src;
    /**
    * 交易所
    */
    @Schema(description = "交易所")
    private String exchange;
    /**
    * 上市日期开始
    */
    @Schema(description = "上市日期开始")
    private String listDateStart;
    /**
    * 上市日期结束
    */
    @Schema(description = "上市日期结束")
    private String listDateEnd;

    /**
    * N概念指数S特色指数
    */
    @Schema(description = "N概念指数S特色指数")
    private String type;
    /**
    * 成分个数
    */
    @Schema(description = "成分个数")
    private Integer count;


}
