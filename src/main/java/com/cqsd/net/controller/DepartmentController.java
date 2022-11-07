package com.cqsd.net.controller;

import com.cqsd.auth.aop.annotation.RequirePermission;
import com.cqsd.data.entry.Department;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DepartmentService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.HttpContrroller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController extends HttpContrroller<Department, QueryObject,DepartmentService> {
	
	public DepartmentController(DepartmentService service) {
		super(service);
	}
	
	@GetMapping("/dept")
	@PreAuthorize("hasAuthority('system:department:query')")
	//hasAuthority需要一条权限
	//hasRole需要一个角色
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return JsonResult.success(service.findByQueryObject(queryObject));
	}
	
	@GetMapping("/dept/{id}")
	public JsonResult<?> getById(@PathVariable("id") Long id) {
		return JsonResult.success(service.findById(id));
	}
	
	@PostMapping("/dept")
	@RequirePermission("system:department:saveOrUpdate")
	public JsonResult<?> saveOrUpdate(@RequestBody Department record) {
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
	
	@DeleteMapping("/dept/{id}")
//	@RequirePermission("system:department:delete")
	@PreAuthorize("hasRole('root')")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return JsonResult.success(id);
	}
}
