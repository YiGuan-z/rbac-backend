package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Role;
import com.cqsd.data.mapper.RoleMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
	private final RoleMapper mapper;
	
	public RoleServiceImpl(RoleMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void save(Role record) {
		mapper.insert(record);
	}
	
	@Override
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateById(Role record) {
		mapper.updateByPrimaryKey(record);
	}
	
	@Override
	public Role findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageInfo<Role> findByQueryObject(QueryObject qo) {
		final Page<Role> page = PageHelper.startPage(qo.current(), qo.limit());
		mapper.selectForList(qo);
		return PageInfo.of(page);
	}
	
	@Override
	public List<Role> selectAll(QueryObject queryObject) {
		return RoleService.super.selectAll(queryObject);
	}
}
