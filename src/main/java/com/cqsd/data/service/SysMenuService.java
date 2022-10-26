package com.cqsd.data.service;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;
import com.cqsd.data.vo.TreeData;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenus, QueryObject> {
	List<TreeData> getTreeData();
	
	SysMenus changeStat(Long id);
	
	List<TreeData> getAllTreeData();
}
