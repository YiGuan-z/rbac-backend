package com.cqsd.net.Interceptor;

import com.cqsd.auth.aop.annotation.RequirePermission;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
public class CheckPermissionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private EmployeeMapper mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod handlerMethod) {
			final var annotation = handlerMethod.getMethodAnnotation(RequirePermission.class);
			if (Objects.isNull(annotation)) return true;
			final var userInfo = TokenManager.getUser(request.getHeader(TokenManager.TOKEN_NAME));
			//获取用户持有的权限表达式
			final var expression = mapper.selectExpression(userInfo.getId());
			//获取用户权限，获取当前注解上的所需权限，如果没有，就返回403
			//如果是管理员就放行
			if (expression.contains(annotation.value())) {
				return true;
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=utf-8");
				JsonUtil.writeJson(response.getWriter(),JsonResult.failed(403, "该用户没有权限"));
				return false;
			}
		}
		return true;
	}
}
