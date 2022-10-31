package com.cqsd;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScans({@MapperScan("com.cqsd.data.mapper")})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
