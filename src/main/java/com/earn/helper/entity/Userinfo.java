package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.earn.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Userinfo extends BaseEntity<Userinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 师父id
     */
    private Integer parentId;
    @TableField(exist = false)
    private Userinfo parent;

    /**
     * 登录名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 资金密码
     */
    private String accountPassword;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像
     */
    private String imgPath;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 推广码
     */
    private String rcode;

    /**
     * 等级 1-会员 2-金牌 3-钻石
     */
    private Integer vipLevel;

    /**
     * 城主类型 1-非城主 2-金牌城主 3-钻石城主
     */
    private Integer castellanLevel;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private String code;

    /**
     * 新密码
     */
    @TableField(exist = false)
    private String newPassword;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "Userinfo{" + "userId=" + userId + ", parentId=" + parentId + ", username=" + username + ", password="
            + password + ", accountPassword=" + accountPassword + ", nickName=" + nickName + ", imgPath=" + imgPath
            + ", idCard=" + idCard + ", phone=" + phone + ", rcode=" + rcode + ", vipLevel=" + vipLevel
            + ", castellanLevel=" + castellanLevel + ", visible=" + getVisible() + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + "}";
    }
}
