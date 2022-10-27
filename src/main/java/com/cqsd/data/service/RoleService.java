package com.cqsd.data.service;

import com.cqsd.data.entry.Role;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;

import java.util.List;

public interface RoleService extends BaseService<Role, QueryObject> {
	 List<Role> selectAll();
}
