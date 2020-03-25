package com.earn.helper.service.impl;

import java.util.Date;
import java.util.List;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import com.earn.helper.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Accountinfo;
import com.earn.helper.enume.AccountTypeEnum;
import com.earn.helper.mapper.AccountinfoMapper;
import com.earn.helper.service.IAccountinfoService;

/**
 * <p>
 * 账户信息表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
@Service
public class AccountinfoServiceImpl extends ServiceImpl<AccountinfoMapper, Accountinfo> implements IAccountinfoService {
    @Autowired
    private AccountinfoMapper accountinfoMapper;

    @Override
    public Page<Accountinfo> pageList(Accountinfo accountinfo) {
        Page<Accountinfo> page = new Page<>(accountinfo.getPage(), accountinfo.getSize());
        List<Accountinfo> records = accountinfoMapper.selectListPage(page, accountinfo);
        for (Accountinfo record : records) {
            if (!StringUtil.isNullAndBlank(record.getImgPath())) {
                record.setImgPath(Const.FILE_PATH + record.getImgPath());
            }
            record.setTypeName(record.getAccountType().getTypeName());
        }
        return page.setRecords(records);
    }

    @Override
    public Double getAmount(Accountinfo accountinfo) {
        return accountinfoMapper.getAmount(accountinfo);
    }

    @Override
    public Double getWithdraw(Accountinfo accountinfo) {
        return accountinfoMapper.getWithdraw(accountinfo);
    }

    @Override
    public void addAccount(Userinfo userinfo, AccountTypeEnum type, Double reward, String imgPath, Integer payType,
        Integer opsUserId) {
        Accountinfo accountinfo = new Accountinfo();
        accountinfo.setUserId(userinfo.getUserId());
        accountinfo.setType(type.ordinal());
        accountinfo.setIsGain(type.getIsGain());
        accountinfo.setAmount(reward);
        if (imgPath != null) {
            accountinfo.setImgPath(imgPath);
        }
        if (payType != null) {
            accountinfo.setPayType(payType);
        }
        accountinfo.setOpsUserId(opsUserId);
        accountinfo.insert();
    }
}
