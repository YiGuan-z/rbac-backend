package com.cqsd.net.controller;

import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/employee")
public class LoginController {
	private final EmployeeService service;
	
	public LoginController(EmployeeService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/login")
	@ResponseBody
	public JsonResult<?> employeeLogin(@RequestBody User user) {
		try {
			final var token = service.login(user.username(), user.password());
			return JsonResult.success(token);
		} catch (Exception e) {
			return JsonResult.failed(e.getMessage());
		}
	}
	
	@GetMapping(value = "/info")
	public JsonResult<?> getUserInfo(@RequestHeader(value = TokenManager.TOKEN_NAME,required = false) String token) {
		if (!StringUtils.hasLength(token)){
			return JsonResult.failed(400,"token 不能为空");
		}
		final var user = TokenManager.getUser(token);
		return JsonResult.success(user);
	}
	
	@GetMapping(value = "/logout")
	public JsonResult<?> logout(@RequestHeader(value = TokenManager.TOKEN_NAME,required = false)String token){
		TokenManager.remove(token);
		return JsonResult.success();
	}
	
	private record User(String username, String password) {
	}
}
