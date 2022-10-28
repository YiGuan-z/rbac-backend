package com.cqsd.data.service;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeRoleService extends BaseService<EmployeeRole, QueryObject> {
	 List<Long> selectAllbyId(Long id);
	
	 List<EmployeeRole> selectAll() ;
	
	void save(Long id, ArrayList<Long> roles);
}
