package com.cqsd.net.controller;

import com.cqsd.auth.security.entry.UserLoginInfo;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.auth.security.util.SecurityUtils;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.entry.TokenInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping(value = "/api/v1/employee")
public class LoginController {
	private final EmployeeService service;
	private final AuthenticationManager authenticationManager;
	
	
	public LoginController(EmployeeService service, AuthenticationManager authenticationManager) {
		this.service = service;
		this.authenticationManager = authenticationManager;
	}
	
	/**
	 * 需要两个login方法，一个验证表单登陆，一个验证二维码扫描登陆
	 * @param user
	 * @param verified
	 * @return
	 * @throws EmployeeService.LoginExeption
	 */
	@PostMapping(value = "/login/{t}")
	@ResponseBody
	public JsonResult<?> employeeLogin(@RequestBody User user, @PathVariable(value = "t" ,required = false)Long verified) throws EmployeeService.LoginExeption {
		final var token = login(user.username(), user.password(), verified);
		
		return JsonResult.success(token);
	}
	
	private String login(String username, String password, Long verified) {
		if (Objects.nonNull(verified)) {
			final var info = TokenManager.getTokenInfo(verified);
			//判断时间有效期如果失效，删除该未验证Token
			final var flag = System.currentTimeMillis() - info.getCreateTime().getTime() >= 60 * 10 * 1_000;
			
		}
		final var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		final var authenticate = authenticationManager.authenticate(authenticationToken);
		SecurityUtils.setAuthentication(authenticationToken);
		final var info = (UserLoginInfo) authenticate.getPrincipal();
		final var token = TokenManager.createToken();
		TokenManager.addUser(token, info);
		return token;
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
