package com.cqsd.net.controller;

import com.cqsd.data.entry.auth.UserLogin;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.utils.SecurityUtils;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.utils.TokenManager1;
import com.cqsd.data.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/employee")
public class LoginController {
	private final EmployeeService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public LoginController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public JsonResult<?> employeeLogin(@RequestBody User user) throws EmployeeService.LoginExeption {
//		final var token = service.login(user.username(), user.password());
		final var authenticationToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
		final var authenticate = authenticationManager.authenticate(authenticationToken);
		SecurityUtils.setAuthentication(authenticationToken);
		final var info = (UserLogin)authenticate.getPrincipal();
		final var token = TokenManager1.createToken();
		info.setToken(token);
		TokenManager1.addUser(token,info);
		return JsonResult.success(token);
	}

	@GetMapping(value = "/info")
	public JsonResult<?> getUserInfo(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
		if (!StringUtils.hasLength(token)) {
			return JsonResult.failed(400, "token 不能为空");
		}
		final var user = TokenManager.getUser(token);
		return JsonResult.success(user);
	}

	@GetMapping(value = "/logout")
	public JsonResult<?> logout(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
		TokenManager.remove(token);
		return JsonResult.success();
	}

	private record User(String username, String password) {
	}
}
