package com.earn.helper.controller;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Product;
import com.earn.helper.entity.R;
import com.earn.helper.service.IProductService;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @ResponseBody
    @GetMapping("/list")
    public R list(Product product) {
        Page<Product> list = productService.pageList(product);
        return new R<Page<Product>>().setData(list);
    }

    @ResponseBody
    @GetMapping("/get/{id}")
    public R list(@PathVariable("id") Integer id) {
        Product product = new Product().selectById(id);
        if (!StringUtil.isNullAndBlank(product.getContent())) {
            product.setContent(product.getContent().replaceAll(Const.FILE_GET_METHOD_REG, Const.FILE_PATH));
        }
        if (!StringUtil.isNullAndBlank(product.getImgPath())) {
            product.setImgPath(Const.FILE_PATH + product.getImgPath());
        }
        if (!StringUtil.isNullAndBlank(product.getImgPaths())) {
            StringBuilder paths = new StringBuilder();
            String[] imgPaths = product.getImgPaths().split(",");
            for (String imgPath : imgPaths) {
                paths.append(",").append(Const.FILE_PATH).append(imgPath);
            }
            product.setImgPaths(paths.substring(1).toString());
        }
        return new R<Product>().setData(product);
    }
}
