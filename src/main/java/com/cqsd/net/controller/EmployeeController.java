package com.cqsd.net.controller;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@GetMapping("/user")
	public JsonResult<?> getEmployeeByQueryObject(QueryObject queryObject) {
		final var result = service.findByQueryObject(queryObject);
		return JsonResult.success(result);
	}
	
	@GetMapping("/user/{id}")
	public JsonResult<?> getById(@PathVariable Long id) {
		return JsonResult.success(service.findById(id));
	}
	
	@PostMapping("/user")
	public JsonResult<?> saveOrUpdate(@RequestBody Employee employee) {
		try {
			if (Objects.isNull(employee.getId())) {
				service.save(employee);
			} else {
				service.updateById(employee);
			}
			return JsonResult.success(employee);
		} catch (Exception e) {
			return JsonResult.failed(e.getMessage());
		}
	}
	
	@DeleteMapping("/user/{id}")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return JsonResult.success(id);
	}
	
	
}
