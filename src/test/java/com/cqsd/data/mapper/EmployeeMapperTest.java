package com.cqsd.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/application.xml")
class EmployeeMapperTest {
	@Autowired
	private EmployeeMapper mapper;
	
	@Test
	void selectAll() {
		mapper.selectAll().forEach(System.out::println);
	}
}