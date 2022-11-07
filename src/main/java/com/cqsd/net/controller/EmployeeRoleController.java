package com.cqsd.net.controller;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeRoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import com.cqsd.net.base.HttpContrroller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/employee")
public class EmployeeRoleController extends HttpContrroller<EmployeeRole, QueryObject,EmployeeRoleService> {
	
	public EmployeeRoleController(EmployeeRoleService service) {
		super(service);
	}
	
	
	@GetMapping("/role/{id}")
	public JsonResult<?> selectByPrimaryKey(@PathVariable("id") Long id) {
		return JsonResult.success(service.selectAllbyId(id));
	}
	
	@GetMapping("/role")
	public JsonResult<?> queryAll() {
		return JsonResult.success(service.selectAll());
	}
	
	@PutMapping("/role")
	public JsonResult<?> saveOrUpdate(@RequestBody PromissionSwap record) {
		service.save(record.id(),record.roles());
		return JsonResult.success();
	}
	public record PromissionSwap(Long id, ArrayList<Long> roles){}
	
}
