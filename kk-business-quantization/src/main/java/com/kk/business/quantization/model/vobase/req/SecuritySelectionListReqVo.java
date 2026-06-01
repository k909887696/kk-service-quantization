package com.kk.business.quantization.model.vobase.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.kk.common.base.model.BasePage;
/**
 * <p>
 * 个人自选股列表查询实体
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Data
@Schema(description = "个人自选股列表查询实体")
public class SecuritySelectionListReqVo extends BasePage{

    private static final long serialVersionUID = 1L;

    private String tsCode;
    private String tradeDateStart;
    private String tradeDateEnd;

    private String selectType;


}
