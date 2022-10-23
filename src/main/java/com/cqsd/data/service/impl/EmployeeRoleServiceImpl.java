package com.cqsd.data.service.impl;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.mapper.EmployeeRoleMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeRoleService;
import com.cqsd.data.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
	private final EmployeeRoleMapper service;
	
	public EmployeeRoleServiceImpl(EmployeeRoleMapper service) {
		this.service = service;
	}
	
	@Override
	public void save(EmployeeRole record) {
		service.insert(record);
	}
	
	@Override
	public PageInfo<EmployeeRole> selectAllbyQueryObject(QueryObject queryObject) {
		final Page<EmployeeRole> page = PageHelper.startPage(queryObject.current(), queryObject.limit());
		final var roles = service.selectAll();
		return new PageInfo<>(page);
	}
}
