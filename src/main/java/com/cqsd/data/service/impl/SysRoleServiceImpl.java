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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMenu, QueryObject,SysRoleMenuMapper> implements SysRoleService {
	public SysRoleServiceImpl(SysRoleMenuMapper mapper) {
		super(mapper);
	}
	
	
	@Override
	public void save(Long id, ArrayList<Long> menusId) {
		mapper.deleteByPrimaryKey(id);
		if (menusId.isEmpty()){
			return;
		}
		mapper.save(id, menusId);
	}
	
	@Override
	public List<Long> selectMenuIdByRoleId(Long id) {
		return mapper.selectMenuIdByRoleId(id).stream().map(SysRoleMenu::getMenu_id).toList();
	}
}
