package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Department;
import com.cqsd.data.mapper.DepartmentMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DepartmentService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,QueryObject,DepartmentMapper> implements DepartmentService {
	
	public DepartmentServiceImpl(DepartmentMapper mapper) {
		super(mapper);
	}
}
