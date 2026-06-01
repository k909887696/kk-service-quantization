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
 * 申万行业分类列表查询实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "申万行业分类列表查询实体", description = "申万行业分类")
public class IndexClassifyListVo extends BasePage{

    private static final long serialVersionUID = 1L;

    /**
    * 指数代码
    */
    @Schema(description = "指数代码")
    private String indexCode;
    /**
    * 行业名称
    */
    @Schema(description = "行业名称")
    private String industryName;
    /**
    * 父级代码
    */
    @Schema(description = "父级代码")
    private String parentCode;
    /**
    * 行业名称
    */
    @Schema(description = "行业名称")
    private String level;
    /**
    * 行业代码
    */
    @Schema(description = "行业代码")
    private String industryCode;
    /**
    * 	是否发布了指数
    */
    @Schema(description = "	是否发布了指数")
    private String isPub;
    /**
    * 行业分类（SW申万）
    */
    @Schema(description = "行业分类（SW申万）")
    private String src;


}
