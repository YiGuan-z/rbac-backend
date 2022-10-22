package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Department;
import com.cqsd.data.mapper.DepartmentMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DepartmentService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.data.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentMapper mapper;
	
	public DepartmentServiceImpl(DepartmentMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void save(Department record) {
		mapper.insert(record);
	}
	
	@Override
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateById(Department record) {
		mapper.updateByPrimaryKey(record);
	}
	
	@Override
	public Department findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageResult<Department> findByQueryObject(QueryObject qo) {
		final Page<Department> page = PageHelper.startPage(qo.current(), qo.limit());
		mapper.findByQueryObject(qo);
		return PageResult.of(qo.current(), qo.limit(), page.getTotal(), page);
	}
	
	@Override
	public Page<Department> selectAll(QueryObject queryObject) {
		Page<Department> page = PageHelper.startPage(queryObject.current(), queryObject.limit());
//		mapper.selectAll();
		mapper.findByQueryObject(queryObject);
		return page;
	}
}
