package com.cqsd.net.controller;

import com.cqsd.data.entry.Role;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.RoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/role")
public class RoleController extends BaseController<Role, QueryObject,RoleService> {
	
	public RoleController(RoleService service) {
		super(service);
	}
	
	@GetMapping("/info")
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return JsonResult.success(service.findByQueryObject(queryObject));
	}
	@GetMapping("/infos")
	public JsonResult<?> selectAll(){
		return JsonResult.success(service.selectAll());
	}
	
	@GetMapping("/info/{id}")
	public JsonResult<?> getById(@PathVariable("id") Long id) {
		return JsonResult.success(service.findById(id));
	}
	
	@PostMapping("/info")
	public JsonResult<?> saveOrUpdate(@RequestBody Role record) {
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
	
	@DeleteMapping("/info/{id}")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return JsonResult.success(id);
	}
}
