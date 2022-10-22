package com.cqsd.data.mapper;

import com.cqsd.data.entry.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
	
	Employee selectByUserName(String username);
//    List<>
}