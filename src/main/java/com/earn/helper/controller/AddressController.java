package com.earn.helper.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.common.annotation.ActiveUser;
import com.earn.helper.entity.Address;
import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.service.IAddressService;
import com.earn.common.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    @ResponseBody
    @GetMapping("/list")
    public R list(Address address) {
        Page<Address> list = addressService.pageList(address);
        return new R<Page<Address>>().setData(list);
    }

    @ResponseBody
    @GetMapping("/setDefault/{id}")
    public R setDefault(@ActiveUser UserLogin userLogin, @PathVariable("id") Integer id) {
        Integer count1 = addressService.removeDefault(userLogin.getUserinfo().getUserId());
        if (count1 < 1) {
            log.info("没有更新");
        }
        Integer count2 = addressService.setDefault(id);
        if (count2 < 1) {
            return R.error("设置失败！");
        }
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(@ActiveUser UserLogin userLogin, Address address) {
        address.setUserId(userLogin.getUserinfo().getUserId());
        address.setOpsUserId(userLogin.getUserinfo().getUserId());
        if (address.getDefaultAddr() != null && address.getDefaultAddr()) {
            Integer count1 = addressService.removeDefault(userLogin.getUserinfo().getUserId());
            if (count1 < 1) {
                log.info("没有更新");
            }
        }
        if (StringUtil.numFormat(address.getAddrId()) > 0) {
            address.setUpdateTime(new Date());
        }

        return getR(address.insertOrUpdate());
    }
}
