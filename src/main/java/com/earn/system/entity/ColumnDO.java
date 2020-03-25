package com.earn.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 列的属性
 * @author 卢惠
 * @since 2019/2/3 上午10:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ColumnDO {
    // 列名
    private String columnName;
    // 列名类型
    private String dataType;
    // 列名备注
    private String comments;

    // 属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    // 属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    // 属性类型
    private String attrType;
    // auto_increment
    private String extra;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    @Override
    public String toString() {
        return "ColumnDO{" + "columnName='" + columnName + '\'' + ", dataType='" + dataType + '\'' + ", comments='"
            + comments + '\'' + ", attrName='" + attrName + '\'' + ", attrname='" + attrname + '\'' + ", attrType='"
            + attrType + '\'' + ", extra='" + extra + '\'' + '}';
    }
}
