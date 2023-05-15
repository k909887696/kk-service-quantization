package com.kk.business.quantization.utils;

import com.kk.business.quantization.model.po.tushare.TushareBaseRes;
import com.kk.common.utils.HumpUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: kk
 * @Date: 2021/12/11 13:34
 */
public  class ThridDataUtils {



    /**
     * tushare 数据转map
     * @param tushareBaseRes
     * @return
     */
    public static List<Map<String,Object>> tushareResultHandler(TushareBaseRes tushareBaseRes, boolean isToHump,Map<String,String> fieldsMap )
    {
        if(tushareBaseRes==null || tushareBaseRes.getData()==null
                || tushareBaseRes.getData().getItems() ==null
                || tushareBaseRes.getData().getItems().size()<=0) return new ArrayList<>();
        List<Map<String,Object>> list = new ArrayList<>();
        if(fieldsMap!=null && fieldsMap.size()>0)//某些字段需要映射
        {
            for (String f : fieldsMap.keySet())
            {
                int fi = tushareBaseRes.getData().getFields().indexOf(f);
                if(fi!=-1)
                {
                    tushareBaseRes.getData().getFields().set(fi,fieldsMap.get(f));
                }
            }
        }

        if(isToHump)
        {
            for (int i=0;i< tushareBaseRes.getData().getFields().size();i++
            ) {
                tushareBaseRes.getData().getFields().set(i,
                        HumpUtil.lineToHump(tushareBaseRes.getData().getFields().get(i) )) ;
            }

        }

        for (List<Object> item : tushareBaseRes.getData().getItems()
        ) {
            Map<String,Object> row = new HashMap<>();
            int i=0;
            for (String field : tushareBaseRes.getData().getFields()
            ) {
                row.put(field,item.get(i));
                i++;
            }
            list.add(row);
        }
        return list;
    }

    /**
     * tushare 参数处理
     * @param obj
     * @return
     */
    public static Map<String,Object> tushareParamsHandler(Object obj) {
        if(obj==null) return null;

        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {

                e.printStackTrace();
                return null;
            }
            if (value == null) {
                value = "";
            }
            map.put(HumpUtil.humpToLineLowerCase(keyName), value);

        }
        return map;

    }


    /**
     * 东方财富数据字段映射
     * @param diff 原数据集
     * @param fields 字段映射关系
     * @param defaultFields 默认值
     * @return
     */
    public static List<Map<String,Object>> dfcfMapTranferHandler(List<Map<String,Object>> diff,Map<String,String> fields,Map<String,Object> defaultFields )
    {
        if(diff==null || fields==null) return diff;
        List<Map<String,Object>> diffRes = new ArrayList<>();

        for (int i =0;i<diff.size();i++)
        {
            Map<String,Object> m = new HashMap<>();
            if(diff.get(i)!=null)
            {
                for (String f : diff.get(i).keySet())
                {
                    if(fields.containsKey(f))
                    {
                        m.put(fields.get(f),diff.get(i).get(f));
                    }
                }
                if(defaultFields!=null && defaultFields.size()>0)//填充默认值
                {
                    for (String df : defaultFields.keySet())
                    {
                        m.put(df,defaultFields.get(df));
                    }
                }
                diffRes.add(m);
            }
        }

        return diffRes;
    }

    /**
     * 东方财富历史数据格式化
     * @param klines
     * @param fields
     * @return
     */
    public static List<Map<String,Object>> dfcfKlinesHandler(List<String> klines,Map<String,String> fields)
    {
        if(klines==null || klines==null) return null;
        List<Map<String,Object>> lines = new ArrayList<>();

        for (int i =0;i<klines.size();i++)
        {
            Map<String,Object> m = new HashMap<>();
            List<String> line = Arrays.stream(klines.get(i).split(",")).collect(Collectors.toList());
            int j=-1;
            for(String f : fields.keySet())
            {
                j++;
                if(f.equals("null"))
                    continue;
                m.put(f,line.get(j));
            }
            lines.add(m);

        }

        return lines;
    }


}
