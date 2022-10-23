package com.cqsd.data.service;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;
import com.github.pagehelper.PageInfo;

public interface EmployeeRoleService extends BaseService<EmployeeRole, QueryObject> {
	 PageInfo<EmployeeRole> selectAllbyQueryObject(QueryObject queryObject);
}
