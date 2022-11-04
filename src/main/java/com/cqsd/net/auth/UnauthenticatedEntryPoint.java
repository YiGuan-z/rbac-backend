package com.cqsd.net.auth;

import com.cqsd.data.vo.JsonResult;
import com.cqsd.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author caseycheng
 * @date 2022/11/4-20:37
 **/
@Slf4j
@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {
	/**
	 * Commences an authentication scheme.
	 * <p>
	 * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
	 * attribute named
	 * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
	 * with the requested target URL before calling this method.
	 * <p>
	 * Implementations should modify the headers on the <code>ServletResponse</code> as
	 * necessary to commence the authentication process.
	 *
	 * @param request       that resulted in an <code>AuthenticationException</code>
	 * @param response      so that the user agent can begin authentication
	 * @param authException that caused the invocation
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		if (authException instanceof UsernameNotFoundException) {
			log.warn("[登陆异常处理] 登陆用户名错误");
		} else if (authException instanceof BadCredentialsException) {
			log.warn("[登陆异常处理] 用户密码错误");
		}else {
			log.warn("[其它异常] 系统出现异常");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		final var failed = JsonResult.failed(401, "failed");
		JsonUtil.writeJson(response.getWriter(), failed);
		
	}
}
