package com.earn.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

/**
 * @author luhui
 * @since 2019/2/2 下午4:30
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Integer userId;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;
    // 密码
    private String password;
    // 部门
    private Integer deptId;
    private String deptName;
    // 头像
    private String imgPath;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户id
    private Integer userIdCreate;
    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    // 角色
    private List<Integer> roleIds;
    // 性别
    private Integer sex;
    // 出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    // 图片ID
    private Integer picId;
    // 现居住地
    private String liveAddress;
    // 爱好
    private String hobby;
    // 省份
    private String province;
    // 所在城市
    private String city;
    // 所在地区
    private String district;

    /**
     * 原密码
     */
    @TableField(exist = false)
    private String oldPassword;

    /**
     * 新密码
     */
    @TableField(exist = false)
    private String newPassword;

    @Override
    public String toString() {
        return "UserDO{" + "userId=" + userId + ", username='" + username + '\'' + ", name='" + name + '\''
            + ", password='" + password + '\'' + ", deptId=" + deptId + ", deptName='" + deptName + '\'' + ", email='"
            + email + '\'' + ", mobile='" + mobile + '\'' + ", status=" + status + ", userIdCreate=" + userIdCreate
            + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", roleIds=" + roleIds + ", sex=" + sex
            + ", birth=" + birth + ", picId=" + picId + ", liveAddress='" + liveAddress + '\'' + ", hobby='" + hobby
            + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", district='" + district + '\''
            + '}';
    }
}
