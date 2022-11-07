package com.cqsd.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MvcConfig.class)
@MapperScans({@MapperScan("com.cqsd.data.mapper")})
@ConfigurationProperties(prefix = "my.db")
public class AppConfig {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Bean(initMethod = "init",destroyMethod = "close")
	public DruidDataSourceWrapper dataSource() {
		final var wrapper = new DruidDataSourceWrapper();
		wrapper.setDriverClassName(driverClassName);
		wrapper.setUrl(url);
		wrapper.setUsername(username);
		wrapper.setPassword(password);
		return wrapper;
	}
	
}
