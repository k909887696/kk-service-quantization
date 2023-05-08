package com.kk.api.quantization;

import com.kk.business.quantization.utils.SerialNoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuantizationApiApplicationTests {

    @Test
    void contextLoads() {

       System.out.println(SerialNoUtil.getSingleNextId("TK",8));
    }



}
