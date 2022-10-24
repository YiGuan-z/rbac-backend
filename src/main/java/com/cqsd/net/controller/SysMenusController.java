package com.cqsd.net.controller;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.net.base.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
	@GetMapping("/menu")
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
		if (Objects.isNull(record.getId())){
			service.save(record);
		}else {
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
		if (service instanceof SysMenuService menuService) {
			final var treeData = menuService.getTreeData();
			return success(treeData);
		}
		return failed("方法未实现");
	}
}
