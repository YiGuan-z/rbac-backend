package com.cqsd.data.service.impl;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.mapper.SysRoleMenuMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysRoleService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMenu, QueryObject> implements SysRoleService {
	public SysRoleServiceImpl(SysRoleMenuMapper mapper) {
		super(mapper);
	}
}
