package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 概念分类列表返回实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "概念分类列表返回实体")
public class ConceptListResVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 概念代码
    */
    @Schema(description ="概念代码")
    private String code;
    /**
    * 概念名称
    */
    @Schema(description ="概念名称")
    private String name;
    /**
    * 来源 ts:tushare,ths:同花顺 90：东方财富
    */
    @Schema(description ="来源 ts:tushare,ths:同花顺 90：东方财富")
    private String src;
    /**
    * 交易所
    */
    @Schema(description ="交易所")
    private String exchange;
    /**
    * 上市日期
    */
    @Schema(description ="上市日期")
    private String listDate;
    /**
    * N概念指数S特色指数
    */
    @Schema(description ="N概念指数S特色指数")
    private String type;
    /**
    * 成分个数
    */
    @Schema(description ="成分个数")
    private Integer count;


}
