package com.cqsd.data.service.impl;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.mapper.EmployeeRoleMapper;
import com.cqsd.data.service.EmployeeRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
	private final EmployeeRoleMapper mapper;
	
	public EmployeeRoleServiceImpl(EmployeeRoleMapper service) {
		this.mapper = service;
	}
	
	@Override
	public void save(EmployeeRole record) {
		mapper.deleteByPrimaryKey(record.getEmployee_id());
		mapper.insert(record);
	}
	
	@Override
	public List<Long> selectAllbyId(Long id) {
		return mapper.findByPrimaryKey(id).stream().map(EmployeeRole::getRole_id).toList();
	}
	
	@Override
	public List<EmployeeRole> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public void save(Long id, ArrayList<Long> roles) {
		mapper.deleteByPrimaryKey(id);
		if (Objects.isNull(roles)){
			return;
		}
		mapper.saveList(id,roles);
	}
}
