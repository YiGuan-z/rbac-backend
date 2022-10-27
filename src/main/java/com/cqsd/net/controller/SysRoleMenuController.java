package com.cqsd.net.controller;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysRoleService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

import static com.cqsd.data.vo.JsonResult.success;

@RestController
@RequestMapping("/system")
public class SysRoleMenuController extends BaseController<SysRoleMenu, QueryObject> {
	
	public SysRoleMenuController(SysRoleService service) {
		super(service);
	}
	private final SysRoleService roleService= service instanceof SysRoleService ? ((SysRoleService) service) : null;
	
	
	/**
	 * 通过查询对象查询
	 *
	 * @param queryObject
	 * @return
	 */
	@Override
	@GetMapping("/roleMenu")
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return success(service.findByQueryObject(queryObject));
	}
	/**
	 * 保存一个资源
	 *
	 * @param record
	 * @return
	 */
	@Override
	@PostMapping("/roleMenu")
	public JsonResult<?> saveOrUpdate(SysRoleMenu record) {
		service.save(record);
		return success(record);
	}
	@PutMapping("/roleMenu")
	public JsonResult<?> putPromission(@RequestBody PromissionSwap swap){
		roleService.save(swap.id(),swap.menuId());
		return JsonResult.success();
	}
	@GetMapping("/roleMenu/{id}")
	public JsonResult<?> getRolePermission(@PathVariable Long id){
		return JsonResult.success(roleService.selectMenuIdByRoleId(id));
		
	}
	public record PromissionSwap(Long id,ArrayList<Long> menuId){}
	
}
