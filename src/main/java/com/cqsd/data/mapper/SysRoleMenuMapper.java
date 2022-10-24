package com.cqsd.data.mapper;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;

import java.util.List;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu, QueryObject> {
	int insert(SysRoleMenu record);
	
	List<SysRoleMenu> selectAll();
}