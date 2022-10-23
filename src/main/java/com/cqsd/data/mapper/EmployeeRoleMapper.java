package com.cqsd.data.mapper;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;

import java.util.List;

public interface EmployeeRoleMapper extends BaseMapper<EmployeeRole, QueryObject> {
	int insert(EmployeeRole record);
	
	List<EmployeeRole> selectAll();
	
	List<EmployeeRole> selectByQueryObject(EmployeeRole employeeRole);
	
//	List<EmployeeRole> findByQueryObject(QueryObject qo);
}