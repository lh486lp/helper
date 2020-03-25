package com.earn.helper.enume;

/**
 * @author luhui
 * @since 2019/1/9 22:51
 */
public enum AccountTypeEnum {
    /**
     * 0-其他 1-推广任务 2-直接任务返佣 3-间接任务返佣 4-邀请奖励 5-城主奖励 6-提现 7-购物
     */
    qt("其他", 0, 1.0),
    rw("推广任务", 1, 1.0),
    zjfy("直接任务返佣", 1, 1.0),
    jjfy("间接任务返佣", 1, 0.5),
    yq("邀请奖励", 1, 1.0),
    cz("城主奖励", 1, 1.0),
    tx("提现", -1, 0.25),
    sh("审核", -1, 1.0),
    gw("购物", -1, 1.0);

    private String typeName;
    /**
     * 收益/支出
     */
    private Integer isGain;
    /**
     * 比率
     */
    private Double ratio;

    AccountTypeEnum(String typeName, Integer isGain, Double ratio) {
        this.typeName = typeName;
        this.isGain = isGain;
        this.ratio = ratio;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public Integer getIsGain() {
        return this.isGain;
    }

    public Double getRatio() {
        return this.ratio;
    }
}