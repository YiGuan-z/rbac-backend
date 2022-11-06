package com.cqsd.data.mapper;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee, QueryObject> {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
	
	Employee selectByUserName(String username);
	
	void deleteByBeathId(List<Long> ids);
	
	List<String> selectExpression(Long id);
	
	List<String> selectRole(Long id);
}