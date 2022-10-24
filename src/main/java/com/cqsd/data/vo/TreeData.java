package com.cqsd.data.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TreeData {
	private Long id;
	private String title;
	private String path;
	private Integer type;
	private String expression;
	private String component;
	private Integer status;
	private Integer seq;
	private String icon;
	private Date createTime;
	private Date updateTime;
	private Parent parent;
	private List<TreeData> children = new ArrayList<>();
	
	public TreeData(Long id,
					String title,
					String path,
					Integer type,
					String expression,
					String component,
					Integer status,
					Integer seq,
					String icon,
					Date createTime,
					Date updateTime,
					Parent parent,
					List<TreeData> children) {
		this.id = id;
		this.title = title;
		this.path = path;
		this.type = type;
		this.expression = expression;
		this.component = component;
		this.status = status;
		this.seq = seq;
		this.icon = icon;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.parent = parent;
		this.children = children;
	}
	
	public static TreeData of(Long id,
							  String title,
							  String path,
							  Integer type,
							  String expression,
							  String component,
							  Integer status,
							  Integer seq,
							  String icon,
							  Date createTime,
							  Date updateTime,
							  Parent parent,
							  List<TreeData> children) {
		return new TreeData(id, title, path, type, expression, component, status, seq, icon, createTime, updateTime, parent, children);
	}
	
	public static TreeData of(Long id,
							  String title,
							  String path,
							  Integer type,
							  String expression,
							  String component,
							  Integer status,
							  Integer seq,
							  String icon,
							  Date createTime,
							  Date updateTime,
							  Parent parent) {
		return of(id, title, path, type, expression, component, status, seq, icon, createTime, updateTime, parent, new ArrayList<>());
	}
	
	public static TreeData of(Long id,
							  String title,
							  String path,
							  Integer type,
							  String expression,
							  String component,
							  Integer status,
							  Integer seq,
							  String icon,
							  Date createTime,
							  Date updateTime) {
		return of(id, title, path, type, expression, component, status, seq, icon, createTime, updateTime, null, new ArrayList<>());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (TreeData) obj;
		return Objects.equals(this.id, that.id) &&
				Objects.equals(this.title, that.title) &&
				Objects.equals(this.path, that.path) &&
				Objects.equals(this.type, that.type) &&
				Objects.equals(this.expression, that.expression) &&
				Objects.equals(this.component, that.component) &&
				Objects.equals(this.status, that.status) &&
				Objects.equals(this.seq, that.seq) &&
				Objects.equals(this.icon, that.icon) &&
				Objects.equals(this.createTime, that.createTime) &&
				Objects.equals(this.updateTime, that.updateTime) &&
				Objects.equals(this.parent, that.parent) &&
				Objects.equals(this.children, that.children);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, title, path, type, expression, component, status, seq, icon, createTime, updateTime, parent, children);
	}
	
	@Override
	public String toString() {
		return "TreeData[" +
				"id=" + id + ", " +
				"title=" + title + ", " +
				"path=" + path + ", " +
				"type=" + type + ", " +
				"expression=" + expression + ", " +
				"component=" + component + ", " +
				"status=" + status + ", " +
				"seq=" + seq + ", " +
				"icon=" + icon + ", " +
				"createTime=" + createTime + ", " +
				"updateTime=" + updateTime + ", " +
				"parent=" + parent + ", " +
				"children=" + children + ']';
	}
	
	@Setter
	@Getter
	public static final class Parent {
		private Long id;
		private String title;
		
		public Parent(Long id, String title) {
			this.id = id;
			this.title = title;
		}
		
		public static Parent of(Long id, String title) {
			return new Parent(id, title);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null || obj.getClass() != this.getClass()) return false;
			var that = (Parent) obj;
			return Objects.equals(this.id, that.id) &&
					Objects.equals(this.title, that.title);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id, title);
		}
		
		@Override
		public String toString() {
			return "Parent[" +
					"id=" + id + ", " +
					"title=" + title + ']';
		}
		
	}
}
