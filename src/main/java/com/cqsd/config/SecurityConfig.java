package com.cqsd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpFilter;

/**
 * @author caseycheng
 * @date 2022/11/4-19:36
 **/
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService service;
	private LogoutSuccessHandler logoutSuccessHandler;
	private AuthenticationEntryPoint authenticationEntryPoint;
	private HttpFilter tokenManager;
	private AccessDeniedHandler unauthorizedHandler;
	
	@Autowired
	public void setService(UserDetailsService service) {
		this.service = service;
	}
	
	@Autowired
	public void setLogoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler) {
		this.logoutSuccessHandler = logoutSuccessHandler;
	}
	
	@Autowired
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Autowired
	public void setTokenManager(HttpFilter tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	@Autowired
	public void setUnauthorizedHandler(AccessDeniedHandler unauthorizedHandler) {
		this.unauthorizedHandler = unauthorizedHandler;
	}
	
	//配置密码
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/**
	 * Override this method to configure the {@link HttpSecurity}. Typically subclasses
	 * should not invoke this method by calling super as it may override their
	 * configuration. The default configuration is:
	 *
	 * <pre>
	 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	 * </pre>
	 * <p>
	 * Any endpoint that requires defense against common vulnerabilities can be specified
	 * here, including public ones. See {@link HttpSecurity#authorizeRequests} and the
	 * `permitAll()` authorization rule for more details on public endpoints.
	 *
	 * @param http the {@link HttpSecurity} to modify
	 * @throws Exception if an error occurs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//关闭csrf防护
		http.csrf().disable()/*.ignoringAntMatchers("/api/v1/employee/login")*/.authorizeRequests()
				//将login设置为只能匿名调用
				.antMatchers("/api/v1/employee/login").permitAll()
				//对任何请求都进行鉴权操作
				.anyRequest().authenticated().and()
				//配置异常处理
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				//访问被拒绝处理器，用于响应json字符串
				.accessDeniedHandler(unauthorizedHandler).and()
				//添加记住我
				.rememberMe()
				//设置token存活时间
				.tokenValiditySeconds(60 * 60 * 24)
				//设置需要调用的用户认证service
				.userDetailsService(service)
				//设置记住我变量为ok
				.rememberMeParameter("ok").and().logout().logoutSuccessHandler(logoutSuccessHandler).logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/employee/logout")).and()
				//将自定义过滤器添加到用户名密码过滤器之前
				.addFilterBefore(tokenManager, UsernamePasswordAuthenticationFilter.class);
		
		
	}
}
