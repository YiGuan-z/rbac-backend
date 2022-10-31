package com.cqsd.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Import(MvcConfig.class)
@ConfigurationProperties("db")
@PropertySource("classpath:db.properties")
public class AppConfig {
	private final Environment environment;
	
	public AppConfig(Environment environment) {
		this.environment = environment;
	}
	
	@Bean(initMethod = "init")
	public DruidDataSourceWrapper dataSource() {
		final var wrapper = new DruidDataSourceWrapper();
		wrapper.setDriverClassName(environment.getProperty("db.driverClassName"));
		wrapper.setUrl(environment.getProperty("db.url"));
		wrapper.setUsername(environment.getProperty("db.username"));
		wrapper.setPassword(environment.getProperty("db.password"));
//		wrapper.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		wrapper.setUrl("jdbc:mysql://49.232.150.194:3306/rbac");
//		wrapper.setUsername("root");
//		wrapper.setPassword("5201314zFy@");
		return wrapper;
	}
	
}
