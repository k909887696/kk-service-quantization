package com.kk.api.quantization;

import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.utils.SerialNoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QuantizationApiApplicationTests {

    @Test
    void contextLoads() {

       System.out.println(SerialNoUtil.getSingleNextId("TK",8));
    }

    @Resource(name = "StrongPoolTaskExecutor")
    public ITaskExecutor strongPoolTaskExecutor;

    @Test
    public  void testExecutor()
    {
        strongPoolTaskExecutor.executeTask("{\"tradeDate\":\"20230821\"}");
    }

}
