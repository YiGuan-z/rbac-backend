package com.cqsd.data.service.impl;


import com.alibaba.fastjson.JSON;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DepartmentService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/application.xml")
class DepartmentServiceImplTest {
	@Autowired
	private DepartmentService service;
	
	@Test
	void testDept() {
		final var departments = service.selectAll();
		departments.forEach(System.out::println);
	}
	@Test
	void testQo(){
		final var object = service.findByQueryObject(new QueryObject(null, 3, 5));
		System.out.println(JSON.toJSONString(object));
	}
	
}