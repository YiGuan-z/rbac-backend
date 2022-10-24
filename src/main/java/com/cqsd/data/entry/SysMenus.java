package com.cqsd.data.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysMenus {
    /** 主键id*/
    private Long id;

    /** 菜单标题*/
    private String title;

    /** 菜单图标*/
    private String icon;

    /** 菜单路径*/
    private String path;

    /** 菜单类型(0=目录, 1=菜单, 2=按钮)*/
    private Integer type;

    /** 权限表达式(大模块:小模块:功能)*/
    private String expression;

    /** 前端组件(菜单类型为目录固定为 Layout, 否则就是views下的组件路径)*/
    private String component;

    /** 菜单状态(0=正常, 1=禁用)*/
    private Integer status;

    /** 父菜单 id*/
    private Long parent_id;

    /** 序号，用于控制菜单展示顺序*/
    private Integer seq;

    /** 创建时间*/
    private Date created_time;

    /** 更新时间*/
    private Date updated_time;
}