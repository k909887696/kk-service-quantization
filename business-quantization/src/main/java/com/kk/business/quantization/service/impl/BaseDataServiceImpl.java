package com.kk.business.quantization.service.impl;

import com.kk.business.quantization.constant.BaseDataItemMapType;
import com.kk.business.quantization.constant.InvokeCycleType;
import com.kk.business.quantization.model.res.BaseDataItemMapGetRes;
import com.kk.business.quantization.model.vo.BaseDataItemMapGetVo;
import com.kk.business.quantization.service.IBaseDataService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BaseDataServiceImpl implements IBaseDataService {

    /**
     * 查询基础数据字典
     * @param vo
     * @return
     */
    public BaseDataItemMapGetRes getBaseDataItemMap(BaseDataItemMapGetVo vo)
    {

        BaseDataItemMapGetRes res = new BaseDataItemMapGetRes();
        Map<String, Map<String,Object>> result = new HashMap<>();

        if(null != vo && vo.getQueryTypeList() != null) {
            vo.getQueryTypeList().forEach(queryType -> {
                Map<String,Object> items = new LinkedHashMap<>();
                // 调度周期类型
                if(queryType.toLowerCase().equals(BaseDataItemMapType.INVOKECYCLETYPE.toLowerCase()))
                {
                    InvokeCycleType.CODE_VALUE_MAP.forEach( (k,v) ->{
                        items.put(k,v);
                    });
                }
                result.put(queryType.toLowerCase(),items);
            });
        }
        res.setQueryTypeResult(result);
        res.setValue(true);
        return res;
    }
}
