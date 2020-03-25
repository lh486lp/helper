package com.earn.system.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表数据
 * 
 * @author 卢惠
 * @since 2019/2/3 上午10:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TableDO {
    // 表的名称
    private String tableName;
    // 表的备注
    private String comments;
    // 表的主键
    private ColumnDO pk;
    // 表的列名(不包含主键)
    private List<ColumnDO> columns;
    // 模块名 如：sys_user => sysuser
    private String modelName;
    // 类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    // 类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "TableDO{" + "tableName='" + tableName + '\'' + ", modelName='" + modelName + '\'' + ", comments='"
            + comments + '\'' + ", pk=" + pk + ", columns=" + columns + ", className='" + className + '\''
            + ", classname='" + classname + '\'' + '}';
    }
}
