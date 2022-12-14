package com.cqsd.data.service.impl;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.mapper.SysMenusMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.service.base.BaseServiceImpl;
import com.cqsd.data.vo.TreeData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenus, QueryObject, SysMenusMapper> implements SysMenuService {
	public SysMenuServiceImpl(SysMenusMapper mapper) {
		super(mapper);
	}
	
	@Override
	public List<TreeData> getAllTreeData() {
		var rowData = mapper.selectAll();
		return getTree(rowData);
	}
	
	private List<TreeData> getTree(List<SysMenus> rowData) {
		final var cacheMap = rowData.parallelStream()
				.map(sysMenus -> TreeData.of(sysMenus.getId(),
						sysMenus.getTitle(),
						sysMenus.getPath(),
						sysMenus.getType(),
						sysMenus.getExpression(),
						sysMenus.getComponent(),
						sysMenus.getStatus(),
						sysMenus.getSeq(),
						sysMenus.getIcon(),
						sysMenus.getCreated_time(),
						sysMenus.getUpdated_time()
				))
				.collect(Collectors.toMap(TreeData::getId, v -> v));
		
		return rowData.parallelStream()
				.filter(v -> {
					if (v.getParent_id() != null) {
						//通过父id找爹
						final var treeData = cacheMap.get(v.getParent_id());
						//将当前儿子设置进去
						final var data = cacheMap.get(v.getId());
						treeData.getChildren().add(data);
						//设置父对象属性
						treeData.setParent(TreeData.Parent.of(treeData.getId(), treeData.getTitle()));
					}
					return v.getParent_id() == null;
				}).map(v -> cacheMap.get(v.getId())).toList();
	}
	
	@Override
	public List<TreeData> getTreeData() {
		var rowData = mapper.selectAll();
		rowData = rowData.stream()
				.filter(v -> v.getType() == 0 || v.getType() == 1)
				.filter(v -> v.getStatus() == 0)
				.collect(Collectors.toList());
		return getTree(rowData);
	}
	@Transactional
	@Override
	public SysMenus changeStat(Long id) {
		final var menus = mapper.selectByPrimaryKey(id);
		menus.setStatus(menus.getStatus() == 0 ? 1 : 0);
		mapper.updateByPrimaryKey(menus);
		return menus;
	}
	
	@Override
	@Transactional
	public void updateById(SysMenus record) {
		record.setUpdated_time(new Date());
		super.updateById(record);
	}
	
	@Override
	public List<SysMenus> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public List<TreeData> selectByEmployeeId(Long id) {
		var menus = mapper.selectByEmployeeId(id);
		menus = menus.stream().filter(v -> v.getType() != 2).filter(v -> v.getStatus() == 0).toList();
		return getTree(menus);
	}
}
