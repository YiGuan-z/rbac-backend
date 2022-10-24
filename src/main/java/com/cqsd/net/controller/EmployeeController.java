package com.cqsd.net.controller;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.aspectj.lang.reflect.NoSuchPointcutException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController<Employee,QueryObject> {
	
	public EmployeeController(EmployeeService service) {
		super(service);
	}
	
	@GetMapping("/user")
	@Override
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		final var result = service.findByQueryObject(queryObject);
		return JsonResult.success(result);
	}
	
	@GetMapping("/user/{id}")
	public JsonResult<?> getById(@PathVariable Long id) {
		return JsonResult.success(service.findById(id));
	}
	
	@PostMapping("/user")
	@Override
	public JsonResult<?> saveOrUpdate(@RequestBody Employee record) {
		try {
			if (Objects.isNull(record.getId())) {
				service.save(record);
			} else {
				service.updateById(record);
			}
			return JsonResult.success(record);
		} catch (Exception e) {
			return JsonResult.failed(e.getMessage());
		}
	}
	
	@DeleteMapping("/user/{id}")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return JsonResult.success(id);
	}
	@DeleteMapping("/user")
	public JsonResult<?> deleteBatch(@RequestBody ArrayList<Long> ids){
		if (service instanceof EmployeeService employeeService){
			employeeService.deleteByIds(ids);
			return JsonResult.success(ids.toArray());
		}
		throw new RuntimeException("方法未实现");
	}
	
	
}
