package com.kk.business.quantization.dao.mapper;

import com.kk.business.quantization.dao.entity.SerialNo;
import com.kk.common.dao.mapper.RootMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 自定义主键序号 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2021-12-17
 */

public interface SerialNoMapper extends RootMapper<SerialNo> {

    /**
     * 更新下一个值
     * @param size 获取区间大小
     * @param serialName 前缀名
     * @return
     */
    public int updateNext(int size,String serialName);

}
