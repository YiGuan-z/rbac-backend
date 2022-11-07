package com.cqsd.net.controller;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.HttpContrroller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.cqsd.data.vo.JsonResult.success;

@RequestMapping("/api/v1/system")
@RestController
public class SysMenusController extends HttpContrroller<SysMenus, QueryObject, SysMenuService> {
	
	public SysMenusController(SysMenuService service) {
		super(service);
	}
	
	/**
	 * 查询树结构
	 *
	 * @return treeData
	 */
	@GetMapping("/menu")
	public JsonResult<?> getByQueryObject() {
		final var treeData = service.getAllTreeData();
		return success(treeData);
	}
	
	@GetMapping("/menus")
	public JsonResult<?> queryAll(boolean all) {
		List<SysMenus> ret = service.selectAll();
		if (all) {
			ret = service.selectAll().stream().filter(v -> v.getType() != 2).toList();
		}
		return JsonResult.success(ret);
	}
	
	/**
	 * 获取一个资源
	 *
	 * @param id
	 * @return
	 */
	@Override
	@GetMapping("/menu/{id}")
	public JsonResult<?> getById(@PathVariable("id") Long id) {
		return success(service.findById(id));
	}
	
	/**
	 * 保存或更新一个资源，通过是否有id判断
	 *
	 * @param record
	 * @return
	 */
	@Override
	@PostMapping("/menu")
	public JsonResult<?> saveOrUpdate(@RequestBody SysMenus record) {
		if (Objects.isNull(record.getId())) {
			service.save(record);
		} else {
			service.updateById(record);
		}
		return success(record);
	}
	
	/**
	 * 删除一个资源
	 *
	 * @param id
	 * @return
	 */
	@Override
	@DeleteMapping("/menu/{id}")
	public JsonResult<?> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return success(id);
	}
	
	@GetMapping("/menu/routers")
	public JsonResult<?> getRouter(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
		final var user = TokenManager.getUser(token);
		final var treeData = service.selectByEmployeeId(user.getId());
//			final var treeData = service.getTreeData();
		return success(treeData);
	}
	
	@PatchMapping("/menu/{id}")
	public JsonResult<?> changeStatus(@PathVariable("id") Long id) {
		final var sysMenus = service.changeStat(id);
		return success(sysMenus.getStatus());
	}
}
