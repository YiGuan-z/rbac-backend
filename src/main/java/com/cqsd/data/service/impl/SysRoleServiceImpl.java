package com.cqsd.data.service.impl;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.mapper.SysRoleMenuMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysRoleService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMenu, QueryObject> implements SysRoleService {
	public SysRoleServiceImpl(SysRoleMenuMapper mapper) {
		super(mapper);
	}
	private final SysRoleMenuMapper roleMenuMapper =mapper instanceof SysRoleMenuMapper? (SysRoleMenuMapper) mapper :null;
	@Override
	public void save(Long id, ArrayList<Long> menusId) {
		roleMenuMapper.deleteByPrimaryKey(id);
		roleMenuMapper.save(id,menusId);
	}
	
	@Override
	public List<Long> selectMenuIdByRoleId(Long id) {
		return roleMenuMapper.selectMenuIdByRoleId(id);
	}
}
