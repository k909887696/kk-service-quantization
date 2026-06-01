package com.kk.business.quantization.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import com.kk.common.base.model.BasePage;
import lombok.Data;

import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:37
 *  获取行情接口请求参数
 */
@Data
public class SearchDailyLeaderVo extends ExecutorBaseVo {

    /**
     * 概念分类编号
     */
    @Schema(description = "概念分类编号")
    private String conceptId;


}
