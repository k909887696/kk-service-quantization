package com.kk.api.quantization;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.kk.common.web.listener.ApplicationStartedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Set;


@SpringBootApplication
@EnableMPP
@ComponentScan(basePackages ={ "com.kk.api"
        ,"com.kk.common.web"
        , "com.kk.common.dao"
        ,"com.kk.common.base"
        ,"com.kk.business"})
@MapperScan("com.kk.business.*.dao.mapper")
@EnableDiscoveryClient
public class QuantizationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantizationApiApplication.class, args);
    }
}
