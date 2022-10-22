package com.cqsd.data.vo;

import java.util.Collections;
import java.util.List;

public record PageResult<T>(Integer current, Integer limit, Long total, List<T> list) {
	public static <T>PageResult<T> of(Integer current, Integer limit, Long total, List<T> list){
		var cur=current;
		if (current==0){
			cur=1;
		}
		return new PageResult<>(cur, limit, total, list);
	}
	public static <T>PageResult<T> empty(){
		return of(1,10,0L, Collections.emptyList());
	}
}
