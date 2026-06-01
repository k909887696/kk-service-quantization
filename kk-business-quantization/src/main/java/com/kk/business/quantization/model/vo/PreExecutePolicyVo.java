package com.kk.business.quantization.model.vo;

import com.kk.common.base.model.BasePage;
import lombok.Data;

/**
 * @author kk
 * @since 2023/8/24
 */
@Data
public class PreExecutePolicyVo extends BasePage {
    /**
     * 调度作业参数
     */
    private JobParamVo jobParam;

}
