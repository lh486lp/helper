package com.earn.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 卢惠
 * @since 2019/2/1 上午10:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleMenu {
    private Integer id;
    private Integer roleId;
    private Integer menuId;

    @Override
    public String toString() {
        return "RoleMenuDO{" + "id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + '}';
    }
}
