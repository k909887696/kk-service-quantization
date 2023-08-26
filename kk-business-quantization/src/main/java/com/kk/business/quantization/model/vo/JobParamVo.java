package com.kk.business.quantization.model.vo;

import lombok.Data;

/**
 * @author kk
 * @since 2023/8/24
 */
@Data
public class JobParamVo {
    /**
     * 执行渠道（可指定自定义执行渠道，优先级高可以单独设置job）
     */
    private String channel;

}
