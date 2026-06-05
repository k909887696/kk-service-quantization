package com.kk.business.quantization.model.vobase.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 个股基本信息详情返回实体
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
@Data
@Schema( description = "个股基本信息详情返回实体")
public class StockBasicResVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * ts股票代码
    */
    @Schema(description = "ts股票代码")
    private String tsCode;
    /**
    * 股票代码
    */
    @Schema(description = "股票代码")
    private String symbol;
    /**
    * 股票名称
    */
    @Schema(description = "股票名称")
    private String name;
    /**
    * 地域
    */
    @Schema(description = "地域")
    private String area;
    /**
    * 所属行业
    */
    @Schema(description = "所属行业")
    private String industry;
    /**
    * 股票全称
    */
    @Schema(description = "股票全称")
    private String fullname;
    /**
    * 英文全称
    */
    @Schema(description = "英文全称")
    private String enname;
    /**
    * 市场类型（主板/创业板/科创板/CDR）
    */
    @Schema(description = "市场类型（主板/创业板/科创板/CDR）")
    private String market;
    /**
    * 交易所代码
    */
    @Schema(description = "交易所代码")
    private String exchange;
    /**
    * 交易货币
    */
    @Schema(description = "交易货币")
    private String currType;
    /**
    * 上市状态 L上市 D退市 P暂停上市
    */
    @Schema(description = "上市状态 L上市 D退市 P暂停上市")
    private String listStatus;
    /**
    * 上市日期
    */
    @Schema(description = "上市日期")
    private String listDate;
    /**
    * 退市日期
    */
    @Schema(description = "退市日期")
    private String delistDate;
    /**
    * 是否沪深港通标的，N否 H沪股通 S深股通
    */
    @Schema(description = "是否沪深港通标的，N否 H沪股通 S深股通")
    private String isHs;
    /**
    * 拼音缩写
    */
    @Schema(description = "拼音缩写")
    private String cnspell;


}
