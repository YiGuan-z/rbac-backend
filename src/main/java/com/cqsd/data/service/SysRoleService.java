package com.cqsd.data.service;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface SysRoleService extends BaseService<SysRoleMenu, QueryObject> {
	void save(Long id, ArrayList<Long> menusId);
}
