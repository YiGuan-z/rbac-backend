package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.utils.UserInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeMapper mapper;
	
	public EmployeeServiceImpl(EmployeeMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void save(Employee record) {
		mapper.insert(record);
	}
	
	@Override
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateById(Employee record) {
		mapper.updateByPrimaryKey(record);
	}
	
	@Override
	public Employee findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageInfo<Employee> findByQueryObject(QueryObject qo) {
		final Page<Employee> page = PageHelper.startPage(qo.current(), qo.limit());
		mapper.selectAll();
		return PageInfo.of(page);
	}
	
	@Override
	public String login(String username, String password) {
		Employee employee = mapper.selectByUserName(username);
		Objects.requireNonNull(employee, "用户不存在");
		if (employee.getPassword().equals(password)) {
			final var token = TokenManager.createToken();
			TokenManager.addUser(token, UserInfo.of(employee));
			return token;
		}
		throw new NullPointerException("用户密码错误");
	}
	
	@Override
	public void deleteByIds(List<Long> ids) {
		mapper.deleteByBeathId(ids);
	}
}
