package com.cqsd.data.utils;

import com.cqsd.data.entry.Employee;
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
	
	public void setRoles(String roles) {
		this.roles.add(roles);
	}
	
	private UserInfo(Long id, String username, String name, String avatar) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.avatar = avatar;
	}
	
	private UserInfo() {
	}
	
	public static UserInfo of(Long id, String username, String name, String avatar) {
		return new UserInfo(id, username, name, avatar);
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
