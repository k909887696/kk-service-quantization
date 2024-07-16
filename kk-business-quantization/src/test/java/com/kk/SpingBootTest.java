package com.kk;

import com.kk.business.quantization.service.executor.ITaskExecutor;
import com.kk.business.quantization.service.executor.impl.StrongPoolTaskExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import javax.annotation.Resource;

/**
 * @author kk
 * @since 2024/7/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StrongPoolTaskExecutor.class)
public class SpingBootTest {
    @Resource(name="StrongPoolTaskExecutor")
    public ITaskExecutor strongPoolTaskExecutor;
    @Test
    public void testMethod()
    {

        strongPoolTaskExecutor.executeTask("{\"tradeDate\":\"20240710\"}");
    }
}
