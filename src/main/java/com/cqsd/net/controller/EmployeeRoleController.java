package com.cqsd.net.controller;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeRoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
public class EmployeeRoleController extends BaseController<EmployeeRole, QueryObject> {
	private final EmployeeRoleService service;
	
	public EmployeeRoleController(EmployeeRoleService service) {
		this.service = service;
	}
	
	@Override
	@GetMapping("/role")
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return JsonResult.success(service.findByQueryObject(queryObject));
	}
	
	@Override
	public JsonResult<?> getById(Long id) {
		return null;
	}
	@PostMapping("/role")
	@Override
	public JsonResult<?> saveOrUpdate(EmployeeRole department) {
		service.save(department);
		return JsonResult.success(department);
	}
	
	@Override
	public JsonResult<?> deleteById(Long id) {
		return null;
	}
}
