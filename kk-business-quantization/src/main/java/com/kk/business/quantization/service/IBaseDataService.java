package com.kk.business.quantization.service;

import com.kk.business.quantization.model.res.BaseDataItemMapGetRes;
import com.kk.business.quantization.model.vo.BaseDataItemMapGetVo;

public interface IBaseDataService {

    /**
     * 查询基础数据字典
     * @param vo
     * @return
     */
    public BaseDataItemMapGetRes getBaseDataItemMap(BaseDataItemMapGetVo vo);
}
