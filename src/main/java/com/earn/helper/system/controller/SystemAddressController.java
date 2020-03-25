package com.earn.helper.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.Address;
import com.earn.helper.entity.R;
import com.earn.helper.service.IAddressService;
import com.earn.helper.system.service.ISystemAddressService;
import com.earn.system.entity.SysUserLogin;
import lombok.extern.slf4j.Slf4j;

/**
 * 收货地址表 luhui
 * 
 * @since 2019-02-11 10:56:09
 */

@Slf4j
@Controller
@RequestMapping("/system/address")
public class SystemAddressController {
    @Autowired
    private ISystemAddressService systemAddressService;
    @Autowired
    private IAddressService addressService;

    @GetMapping()
    String Address() {
        return "helper/address/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Address> addressList = systemAddressService.list(query);
        int total = systemAddressService.count(query);
        PageUtils pageUtils = new PageUtils(addressList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/address/add";
    }

    @GetMapping("/edit/{addrId}")
    String edit(@PathVariable("addrId") Integer addrId, Model model) {
        Address address = systemAddressService.get(addrId);
        model.addAttribute("address", address);
        return "helper/address/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Address address) {
        if (systemAddressService.save(address) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Address address) {
        systemAddressService.update(address);
        return R.ok();
    }

    @ResponseBody
    @GetMapping("/setDefault/{id}")
    public R setDefault(@PathVariable("id") Integer id) {
        Address address = systemAddressService.get(id);
        Integer count1 = addressService.removeDefault(address.getUserId());
        if (count1 < 1) {
            log.info("没有更新");
        }
        Integer count2 = addressService.setDefault(id);
        if (count2 < 1) {
            return R.error("设置失败！");
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer addrId) {
        if (systemAddressService.remove(addrId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] addrIds) {
        if (systemAddressService.batchRemove(addrIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
