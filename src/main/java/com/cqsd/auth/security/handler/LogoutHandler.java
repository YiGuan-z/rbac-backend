package com.cqsd.auth.security.handler;

import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.util.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功回掉函数，用于清除token
 * @author caseycheng
 * @date 2022/11/4-19:41
 **/
@Component
public class LogoutHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		final var token = request.getHeader(TokenManager.TOKEN_NAME);
		TokenManager.remove(token);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		JsonUtil.writeJson(response.getWriter(), JsonResult.success());
	}
}
