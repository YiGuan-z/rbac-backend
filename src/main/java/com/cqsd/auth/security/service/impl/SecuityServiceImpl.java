package com.cqsd.auth.security.service.impl;

import com.cqsd.auth.security.entry.UserLoginInfo;
import com.cqsd.auth.security.service.SecuityService;
import com.cqsd.auth.security.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author caseycheng
 * @date 2022/11/6-10:59
 **/
@Service("auth")
public class SecuityServiceImpl implements SecuityService {
	@Override
	public boolean hasAuthority(String expression) {
		final var expressions = SecurityUtils.getLoginUser(UserLoginInfo.class).getExpressions();
		return expressions.contains("*:*:*") || expressions.contains(expression);
	}
	
	@Override
	public boolean hasRole(String role) {
		if (!role.startsWith("ROLE_")) {
			role = "ROLE_" + role;
		}
		final var expressions = SecurityUtils.getLoginUser(UserLoginInfo.class).getExpressions();
		return expressions.contains("ROLE_admin") || expressions.contains(role);
	}
}
