package com.cqsd.auth.security.handler;

import com.cqsd.auth.security.util.SecurityUtils;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查授权
 *
 * @author caseycheng
 * @date 2022/11/4-20:53
 **/
@Component
@Slf4j
public class UnauthorizedHandler implements AccessDeniedHandler {
	/**
	 * Handles an access denied failure.
	 *
	 * @param request               that resulted in an <code>AccessDeniedException</code>
	 * @param response              so that the user agent can be advised of the failure
	 * @param accessDeniedException that caused the invocation
	 * @throws IOException      in the event of an IOException
	 * @throws ServletException in the event of a ServletException
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		final var user = SecurityUtils.getLoginUser();
		//403
		final var status = HttpStatus.FORBIDDEN.value();
		log.warn("[{}访问拒绝]源ip{}想要访问资源{}，鉴权失败，请求已击毁,错误类型为{}", user.getUsername(), request.getRemoteAddr(), request.getRequestURI(), accessDeniedException.getClass());
		final var ret = JsonResult.failed(status, "你无权访问");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		JsonUtil.writeJson(response.getWriter(), ret);
		
	}
}
