package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Role;
import com.cqsd.data.mapper.RoleMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.RoleService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, QueryObject,RoleMapper> implements RoleService {
	public RoleServiceImpl(RoleMapper mapper) {
		super(mapper);
	}
	
	public List<Role> selectAll() {
		return mapper.selectAll();
	}
}
