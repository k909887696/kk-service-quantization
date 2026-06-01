package com.kk.business.quantization.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 申万行业明细查询详情实体
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
@Data
@Schema(name = "申万行业明细查询详情实体", description = "申万行业明细")
public class IndexMemberDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 指数代码
    */
    @Schema(description = "指数代码")
    private String indexCode;
    /**
    * 成分股票代码
    */
    @Schema(description = "成分股票代码")
    private String conCode;


}
