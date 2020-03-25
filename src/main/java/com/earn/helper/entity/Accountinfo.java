package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotation.TableField;
import com.earn.helper.enume.AccountTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 账户信息表
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Accountinfo extends BaseEntity<Accountinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @TableId
    private Integer accountId;

    /**
     * 用户ID
     */
    private Integer userId;
    private Userinfo userinfo;

    /**
     * 交易类型 1-推广任务 2-邀请奖励 3-任务返佣 4-提现 5-审核 6-购物
     */
    private Integer type;
    @TableField(exist = false)
    private AccountTypeEnum accountType;

    @TableField(exist = false)
    private String typeName;

    /**
     * 交易ID
     */
    private Integer typeId;

    /**
     * 数额
     */
    private Double amount;

    /**
     * 收益 -1-减少 1-增加
     */
    private Integer isGain;

    /**
     * 支付图片
     */
    private String imgPath;

    /**
     * 支付类型 1-微信 2-支付宝
     */
    private Integer payType;

    /**
     * 支付密码
     */
    @TableField(exist = false)
    private String accountPassword;

    @TableField(exist = false)
    private MultipartFile file;

    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "Accountinfo{" + "accountId=" + accountId + ", userId=" + userId + ", type=" + accountType.getTypeName()
            + ", typeId=" + typeId + ", amount=" + amount + ", isGain=" + isGain + ", visible=" + getVisible()
            + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + "}";
    }
}
