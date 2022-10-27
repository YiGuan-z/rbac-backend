package com.cqsd.net.controller;

import com.cqsd.data.entry.EmployeeRole;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeRoleService;
import com.cqsd.data.service.SysRoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeRoleController extends BaseController<EmployeeRole, QueryObject> {
	
	public EmployeeRoleController(EmployeeRoleService service) {
		super(service);
	}
	
	private final EmployeeRoleService roleService = service instanceof EmployeeRoleService ? ((EmployeeRoleService) service) : null;
	
	
	@GetMapping("/role/{id}")
	public JsonResult<?> selectByPrimaryKey(@PathVariable("id") Long id) {
		return JsonResult.success(roleService.selectAllbyId(id));
	}
	
	@GetMapping("/role")
	public JsonResult<?> queryAll() {
		return JsonResult.success(service.selectAll());
	}
	
	@PutMapping("/role")
	public JsonResult<?> saveOrUpdate(@RequestBody PromissionSwap record) {
		roleService.save(record.id(),record.roles());
		return JsonResult.success();
	}
	public record PromissionSwap(Long id, ArrayList<Long> roles){}
	
}
