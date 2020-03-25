package com.earn.helper.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.common.annotation.ActiveUser;
import com.earn.helper.entity.Orderinfo;
import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.service.IOrderinfoService;
import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Slf4j
@RestController
@RequestMapping("/orderinfo")
public class OrderinfoController extends BaseController {
    @Autowired
    private IOrderinfoService orderinfoService;

    @ResponseBody
    @GetMapping("/list")
    public R list(Orderinfo orderinfo) {
        Page<Orderinfo> list = orderinfoService.pageList(orderinfo);
        return new R<Page<Orderinfo>>().setData(list);
    }

    @ResponseBody
    @GetMapping("/get/{id}")
    public R list(@PathVariable("id") Integer id) {
        Orderinfo orderinfo = orderinfoService.getOrderById(id);
        return new R<Orderinfo>().setData(orderinfo);
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(@ActiveUser UserLogin userLogin, Orderinfo orderinfo) {
        orderinfo.setUserId(userLogin.getUserinfo().getUserId());
        orderinfo.setOpsUserId(userLogin.getUserinfo().getUserId());
        try {
            if (StringUtil.numFormat(orderinfo.getOrderId()) > 0) {
                orderinfo.setUpdateTime(new Date());
                orderinfo.updateById();
            } else {
                orderinfo.insert();
                orderinfo.setOrderNo(Const.getRcode(orderinfo.getOrderId()));
                orderinfo.updateById();
            }

            return R.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return R.error();
        }
    }
}
