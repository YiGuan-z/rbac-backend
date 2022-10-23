package com.cqsd.data.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//TODO 没写 controller
public class SysRoleMenu {
    /** 员工 id*/
    private Long role_id;

    /** 菜单 id*/
    private Long menu_id;
}