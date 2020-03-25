package com.earn.helper.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.common.util.StringUtil;
import com.earn.helper.entity.Product;
import com.earn.helper.entity.R;
import com.earn.helper.system.service.ISystemProductService;
import com.earn.system.entity.SysUserLogin;

/**
 * 商品信息表 luhui
 * 
 * @since 2019-02-11 10:56:15
 */

@Controller
@RequestMapping("/system/product")
public class SystemProductController {
    @Autowired
    private ISystemProductService productService;
    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping()
    String Product() {
        return "helper/product/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Product> productList = productService.list(query);
        int total = productService.count(query);
        PageUtils pageUtils = new PageUtils(productList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/product/add";
    }

    @GetMapping("/edit/{productId}")
    String edit(@PathVariable("productId") Integer productId, Model model) {
        Product product = productService.get(productId);
        model.addAttribute("product", product);
        model.addAttribute("imgPaths",
            StringUtil.isNullAndBlank(product.getImgPaths()) ? "" : product.getImgPaths().split(","));
        return "helper/product/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(@ActiveUser SysUserLogin userLogin, Product product) {
        product.setOpsUserId(userLogin.getSysUser().getUserId());
        if (productService.save(product) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Product product) {
        productService.update(product);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer productId) {
        if (productService.remove(productId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] productIds) {
        if (productService.batchRemove(productIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
