package com.cqsd.data.mapper;

import com.cqsd.data.entry.Department;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DepartmentMapper extends BaseMapper<Department, QueryObject> {
	
	int deleteByPrimaryKey(Long id);
	
	
	int insert(Department record);
	
	
	Department selectByPrimaryKey(Long id);
	
	List<Department> selectAll();
	
	
	int updateByPrimaryKey(Department record);
	
	
	List<Department> findByQueryObject(QueryObject qo);
}