package com.cqsd.data.service;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<Employee, QueryObject> {
	String login(String username, String password);
	
	void deleteByIds(List<Long> ids);
}
