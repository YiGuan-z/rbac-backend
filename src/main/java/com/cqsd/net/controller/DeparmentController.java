package com.cqsd.net.controller;

import com.cqsd.data.annotation.RequirePermission;
import com.cqsd.data.entry.Department;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DepartmentService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/deparment")
public class DeparmentController extends BaseController<Department, QueryObject,DepartmentService> {
	
	public DeparmentController(DepartmentService service) {
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
