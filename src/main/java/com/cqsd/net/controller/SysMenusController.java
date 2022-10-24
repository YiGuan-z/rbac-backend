package com.cqsd.net.controller;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.mapper.SysMenusMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.service.SysRoleService;
import com.cqsd.data.service.base.BaseService;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;

import static com.cqsd.data.vo.JsonResult.*;

@RequestMapping("/system")
@RestController
public class SysMenusController extends BaseController<SysMenus, QueryObject> {
	
	public SysMenusController(SysMenuService service) {
		super(service);
	}
	
	/**
	 * 通过查询对象查询
	 *
	 * @param queryObject
	 * @return
	 */
	@Override
	@GetMapping("/menus")
	public JsonResult<?> getByQueryObject(QueryObject queryObject) {
		return success(service.findByQueryObject(queryObject));
	}
	
	/**
	 * 获取一个资源
	 *
	 * @param id
	 * @return
	 */
	@Override
	@GetMapping("/menus/{id}")
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
		service.save(record);
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
}
