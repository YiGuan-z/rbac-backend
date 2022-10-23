package com.cqsd.data.qo;

import org.springframework.util.StringUtils;

import java.util.Objects;

public record QueryObject(String keyword, Integer current, Integer limit) {
	private Integer getStart() {
		return (current - 1) * limit;
	}
	
	public QueryObject(String keyword, Integer current, Integer limit) {
		if (StringUtils.hasLength(keyword)){
			this.keyword = keyword;
		}else {
			this.keyword=null;
		}
		if (current == null) {
			this.current = 1;
		} else {
			this.current = current;
		}
		if (Objects.isNull(limit)) {
			this.limit = 10;
		} else {
			this.limit = limit;
		}
	}
}
