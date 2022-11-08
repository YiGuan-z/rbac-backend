package com.cqsd.data.vo;

import com.cqsd.data.mapper.SysMenusMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

@SpringBootTest
class TreeDataTest {
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private SysMenusMapper mapper;
	
	@Test
	void testGetTree() throws JsonProcessingException {
		var rowData = mapper.selectAll();
		rowData = rowData.stream()
				.filter(v -> v.getType() == 0 || v.getType() == 1)
				.filter(v -> v.getStatus() == 0)
				.collect(Collectors.toList());
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
		
		final var treeDataList = rowData.parallelStream()
				.filter(v -> {
					if (v.getParent_id() != null) {
						//通过父id找到系统管理
						final var treeData = cacheMap.get(v.getParent_id());
						//将当前对象设置进去
						final var data = cacheMap.get(v.getId());
						treeData.getChildren().add(data);
						//设置父对象属性
						final var parent = new TreeData.Parent(treeData.getId(), treeData.getTitle());
						treeData.setParent(parent);
					}
					return v.getParent_id() == null;
				}).map(v -> cacheMap.get(v.getId())).toList();
		final var string = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(treeDataList);
		
		System.out.println(string);

//		rowData.forEach(System.out::println);
	}
	
	
}