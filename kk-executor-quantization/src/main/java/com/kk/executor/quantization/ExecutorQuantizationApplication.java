package com.kk.executor.quantization;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kk 2026-05-28 00:38:13
 */

@SpringBootApplication
@EnableMPP
@ComponentScan(basePackages ={ "com.kk.executor"
		,"com.kk.common"
		,"com.kk.business"})
@MapperScan("com.kk.business.*.dao.mapper")
public class ExecutorQuantizationApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExecutorQuantizationApplication.class, args);
	}

}