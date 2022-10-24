package com.cqsd.data.service.impl;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.mapper.SysMenusMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.service.base.BaseServiceImpl;
import com.cqsd.data.vo.TreeData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenus, QueryObject> implements SysMenuService {
	public SysMenuServiceImpl(SysMenusMapper mapper) {
		super(mapper);
	}
	
	@Override
	public List<TreeData> getTreeData() {
		var rowData = mapper.selectAll();
		rowData = rowData.stream()
				.filter(v -> v.getType() == 0 || v.getType() == 1)
				.collect(Collectors.toList());
		final var cacheMap = rowData.stream()
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
		
		return rowData.stream()
				.filter(v -> {
					if (v.getParent_id() != null) {
						//通过父id找到系统管理
						final var treeData = cacheMap.get(v.getParent_id());
						//将当前对象设置进去
						final var data = cacheMap.get(v.getId());
						treeData.getChildren().add(data);
						//设置父对象属性
						final var parent = new TreeData.Parent(data.getId(), data.getTitle());
						treeData.setParent(parent);
					}
					return v.getParent_id() == null;
				}).map(v -> cacheMap.get(v.getId())).toList();
	}
}
