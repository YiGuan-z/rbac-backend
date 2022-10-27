package com.cqsd.net.Interceptor;

import com.alibaba.fastjson2.JSON;
import com.cqsd.data.annotation.RequeryPermission;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.utils.UserInfo;
import com.cqsd.data.vo.JsonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CheckPermissionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod handlerMethod) {
			final var annotation = handlerMethod.getMethodAnnotation(RequeryPermission.class);
			if (Objects.isNull(annotation)) return true;
			final var userInfo = TokenManager.getUser(request.getHeader(TokenManager.TOKEN_NAME));
			//如果是管理员就放行
			final var l = userInfo.getRoles().parallelStream().filter(v -> v.equalsIgnoreCase("admin")).count();
			return l == 1;
		}
		return true;
	}
}
