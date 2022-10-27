package com.cqsd.data.utils;

import com.cqsd.data.entry.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
public class UserInfo {
	private Long id;
	private String username;
	private String name;
	private String avatar;
	private List<String> roles = new ArrayList<>();
	@JsonIgnore
	private Boolean admin;
	
	private UserInfo() {
	}
	
	
	
	public static UserInfo of(Employee obj) {
		final UserInfo info = new UserInfo();
		BeanUtils.copyProperties(obj, info);
		return info;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", UserInfo.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.add("name='" + name + "'")
				.add("avatar='" + avatar + "'")
				.toString();
	}
}
