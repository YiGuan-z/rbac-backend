package com.cqsd.config;

import com.cqsd.net.Interceptor.CheckPermissionInterceptor;
import com.cqsd.net.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Bean
	public CheckPermissionInterceptor checkPermissionInterceptor() {
		return new CheckPermissionInterceptor();
	}
	
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	/**
	 * Add Spring MVC lifecycle interceptors for pre- and post-processing of
	 * controller method invocations and resource handler requests.
	 * Interceptors can be registered to apply to all requests or be limited
	 * to a subset of URL patterns.
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/employee/login")
				.excludePathPatterns("/employee/info");
		registry.addInterceptor(checkPermissionInterceptor())
				.addPathPatterns("/**");
	}
}
