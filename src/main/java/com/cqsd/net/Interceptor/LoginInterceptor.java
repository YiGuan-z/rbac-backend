package com.cqsd.net.Interceptor;

import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.utils.TokenManager1;
import com.cqsd.data.vo.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final var token = request.getHeader(TokenManager.TOKEN_NAME);
		if (StringUtils.hasLength(token) && TokenManager1.include(token)) {
			return true;
		}
		final JsonResult<String> result = JsonResult.failed(403, "The user is not logged in, please log in and try again.");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
		return false;
	}
}
