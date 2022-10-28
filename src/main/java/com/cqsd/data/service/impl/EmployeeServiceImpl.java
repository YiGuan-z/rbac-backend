package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.service.base.BaseServiceImpl;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.utils.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, QueryObject, EmployeeMapper> implements EmployeeService {
	public EmployeeServiceImpl(EmployeeMapper mapper) {
		super(mapper);
	}
	
	@Override
	public String login(String username, String password) throws LoginExeption{
		Employee employee = mapper.selectByUserName(username);
		Objects.requireNonNull(employee, "用户不存在");
		if (employee.getPassword().equals(password)) {
			final var token = TokenManager.createToken();
			final var userInfo = UserInfo.of(employee);
			TokenManager.addUser(token, userInfo);
			return token;
		}
		throw new LoginExeption("用户密码错误");
		
	}
	
	@Override
	public void deleteByIds(List<Long> ids) {
		mapper.deleteByBeathId(ids);
	}
	
	
}
