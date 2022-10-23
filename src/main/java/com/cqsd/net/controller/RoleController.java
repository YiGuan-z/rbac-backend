package com.cqsd.net.controller;

import com.cqsd.data.entry.Role;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.RoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role, QueryObject> {
	private final RoleService service;
	
	public RoleController(RoleService service) {
		this.service = service;
	}
	
	@GetMapping("/info")
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return JsonResult.success(service.findByQueryObject(queryObject));
	}
	
	@GetMapping("/info/{id}")
	public JsonResult<?> getById(@PathVariable("id") Long id) {
		return JsonResult.success(service.findById(id));
	}
	
	@PostMapping("/info")
	public JsonResult<?> saveOrUpdate(@RequestBody Role department) {
		try {
			if (Objects.isNull(department.getId())) {
				service.save(department);
			} else {
				service.updateById(department);
			}
			return JsonResult.success(department);
		} catch (Exception e) {
			return JsonResult.failed(e.getMessage());
		}
	}
	
	@DeleteMapping("/info/{id}")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return JsonResult.success(id);
	}
}
