package com.earn.common.util;

import com.earn.common.config.SystemConfig;

/**
 * @author luhui
 * @since 2018/12/23 18:19
 */
public class Const {
    private static final int BASE_CODE = 10000000;

    /**
     * 获取图片接口地址
     */
    public static final String FILE_GET_METHOD_REG = "/system/sysFile/getFile\\?fileName=";
    public static final String FILE_PATH = SystemConfig.getSysteminfo("file_host") + "/system/sysFile/getFile?fileName=";
    /**
     * 演示系统账户
     */
    public static String DEMO_ACCOUNT = "test";
    /**
     * 自动去除表前缀
     */
    public static String AUTO_REOMVE_PRE = "true";
    /**
     * 停止计划任务
     */
    public static String STATUS_RUNNING_STOP = "stop";
    /**
     * 开启计划任务
     */
    public static String STATUS_RUNNING_START = "start";
    /**
     * 通知公告阅读状态-未读
     */
    public static String OA_NOTIFY_READ_NO = "0";
    /**
     * 通知公告阅读状态-已读
     */
    public static int OA_NOTIFY_READ_YES = 1;
    /**
     * 部门根节点id
     */
    public static Integer DEPT_ROOT_ID = 0;
    /**
     * 缓存方式
     */
    public static String CACHE_TYPE_REDIS = "redis";

    public static String LOG_ERROR = "error";
    /**
     * 金牌
     */
    public static final Integer GOLD = 2;
    /**
     * 任务奖励
     */
    private static final String GOLD_REWARD_KEY = "gold_reward_price";
    /**
     * 每日任务数量
     */
    private static final String GOLD_TASK_COUNT_KEY = "gold_task_count";
    /**
     * 推广奖励
     */
    private static final String GOLD_INVITATION_KEY = "gold_invitation_reward";
    /**
     * 升级金牌城主的粉丝数量
     */
    private static final String GOLD_CASTELLAN_COUNT_KEY = "gold_castellan_count";
    /**
     * 金牌城主团队新增一名成员，城主获得的奖励
     */
    private static final String GOLD_CASTELLAN_REWARD_KEY = "gold_castellan_reward";
    /**
     * 钻石
     */
    public static final Integer DIAMOND = 3;
    /**
     * 任务奖励
     */
    private static final String DIAMOND_REWARD_KEY = "diamond_reward_price";
    /**
     * 每日任务数量
     */
    private static final String DIAMOND_TASK_COUNT_KEY = "diamond_task_count";
    /**
     * 推广奖励
     */
    private static final String DIAMOND_INVITATION_KEY = "diamond_invitation_reward";
    /**
     * 升级钻石城主的钻石粉丝数量
     */
    private static final String DIAMOND_CASTELLAN_COUNT_KEY = "diamond_castellan_count";
    /**
     * 钻石城主团队新增一名钻石成员，城主获得的奖励
     */
    private static final String DIAMOND_CASTELLAN_REWARD_KEY = "diamond_castellan_reward";
    /**
     * 任务完成时间限制
     */
    public static final long TASK_LIMIT_TIME = 1000 * 60 * 60 * 2;

    /**
     * 生成推广码
     *
     * @param id
     * @return
     */
    public static String getRcode(Integer id) {
        return BASE_CODE + id + "";
    }

    /**
     * 获取任务奖励
     *
     * @param userLevel
     * @return
     */
    public static Double getTaskReward(Integer userLevel) {
        return getTaskReward(userLevel, null);
    }

    /**
     * 获取每日限制任务数量
     *
     * @param level
     * @return
     */
    public static Double getTaskCount(Integer level) {
        if (DIAMOND.equals(level)) {
            return StringUtil.doubleFormat(SystemConfig.getSysteminfo(DIAMOND_TASK_COUNT_KEY));
        }else {
            return StringUtil.doubleFormat(SystemConfig.getSysteminfo(GOLD_TASK_COUNT_KEY));
        }
    }

    /**
     * 获取任务奖励
     *
     * @param userLevel
     * @param invitationlevel
     * @return
     */
    public static Double getTaskReward(Integer userLevel, Integer invitationlevel) {
        if (DIAMOND.equals(userLevel) && (invitationlevel == null || DIAMOND.equals(invitationlevel))) {
            return StringUtil.doubleFormat(SystemConfig.getSysteminfo(DIAMOND_REWARD_KEY));
        }else {
            return StringUtil.doubleFormat(SystemConfig.getSysteminfo(GOLD_REWARD_KEY));
        }
    }

    /**
     * 获取推广奖励
     *
     * @param userLevel
     * @param parentlevel
     * @return
     */
    public static Double getInvitationReward(Integer userLevel, Integer parentlevel) {
        return StringUtil.doubleFormat(SystemConfig.getSysteminfo(
                (DIAMOND.equals(userLevel) && DIAMOND.equals(parentlevel)) ? DIAMOND_INVITATION_KEY : GOLD_INVITATION_KEY));
    }

    /**
     * 获取升级对应城主的粉丝数量
     *
     * @param level
     * @return
     */
    public static Integer getCastellanCount(Integer level) {
        return StringUtil.numFormat(SystemConfig.getSysteminfo(DIAMOND.equals(level) ? DIAMOND_CASTELLAN_COUNT_KEY : GOLD_CASTELLAN_COUNT_KEY));
    }

    /**
     * 获取城主奖励
     *
     * @param castellanLevel
     * @param userLevel
     * @return
     */
    public static Double getCastellanReward(Integer castellanLevel, Integer userLevel) {
        return StringUtil.doubleFormat(SystemConfig.getSysteminfo(
                (DIAMOND.equals(castellanLevel) && DIAMOND.equals(userLevel)) ? DIAMOND_CASTELLAN_REWARD_KEY : GOLD_CASTELLAN_REWARD_KEY));
    }
}
