package com.cqsd.data.mapper;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface EmployeeRoleMapper extends BaseMapper<EmployeeRole, QueryObject> {
	int insert(EmployeeRole record);
	
	List<EmployeeRole> selectAll();
	
	int deleteByPrimaryKey(Long id);
	
	List<EmployeeRole> findByPrimaryKey(Long id);
	
	void saveList(@Param("id") Long id, @Param("roles") ArrayList<Long> roles);
}