package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Accountinfo;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.enume.AccountTypeEnum;

/**
 * <p>
 * 账户信息表 服务类
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
public interface IAccountinfoService extends IService<Accountinfo> {

    /**
     * 分页查询列表
     *
     * @param accountinfo
     * @return
     */
    Page<Accountinfo> pageList(Accountinfo accountinfo);

    Double getAmount(Accountinfo accountinfo);

    Double getWithdraw(Accountinfo accountinfo);

    /**
     * 记录账户信息
     *  @param userinfo
     * @param type
     * @param reward
     * @param imgPath
     * @param payType
     * @param opsUserId
     */
    void addAccount(Userinfo userinfo, AccountTypeEnum type, Double reward, String imgPath, Integer payType,
        Integer opsUserId);
}
