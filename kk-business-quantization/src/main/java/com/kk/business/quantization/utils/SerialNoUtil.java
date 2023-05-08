package com.kk.business.quantization.utils;

import com.kk.business.quantization.service.ISerialNoService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class SerialNoUtil {

    @Resource
    public  ISerialNoService serialNoService;

    public static ISerialNoService serialNoServiceStatic;

    @PostConstruct
    public void postConstruct() {
        serialNoServiceStatic = serialNoService;
    }

    public static List<String> getMultiNextId(String prefix,String format,int bit,int size)
    {
        return serialNoServiceStatic.notsGetNextId(prefix,format,bit,size);
    }

    public static List<String> getMultiNextId(String prefix,int bit,int size)
    {
        return serialNoServiceStatic.notsGetNextId(prefix,"",bit,size);
    }
    public static String getSingleNextId(String prefix,String format,int bit)
    {
        return serialNoServiceStatic.notsGetNextId(prefix,format,bit,1).get(0);
    }

    public static String getSingleNextId(String prefix,String format)
    {
        return serialNoServiceStatic.notsGetNextId(prefix,format,10,1).get(0);
    }

    public static String getSingleNextId(String prefix,int bit)
    {
        return serialNoServiceStatic.notsGetNextId(prefix,"",bit,1).get(0);
    }
}
