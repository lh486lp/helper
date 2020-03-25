package com.earn.helper.service.impl;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Product;
import com.earn.helper.mapper.ProductMapper;
import com.earn.helper.service.IProductService;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<Product> pageList(Product product) {
        Page<Product> page = new Page<>(product.getPage(), product.getSize());
        List<Product> records = productMapper.selectListPage(page, product);
        for (Product record : records) {
            if (!StringUtil.isNullAndBlank(record.getImgPath())) {
                record.setImgPath(Const.FILE_PATH + record.getImgPath());
            }
            if (!StringUtil.isNullAndBlank(record.getContent())) {
                record.setContent(record.getContent().replaceAll(Const.FILE_GET_METHOD_REG, Const.FILE_PATH));
            }
            if (!StringUtil.isNullAndBlank(record.getImgPaths())) {
                StringBuilder paths = new StringBuilder();
                String[] imgPaths = record.getImgPaths().split(",");
                for (String imgPath : imgPaths) {
                    paths.append(",").append(Const.FILE_PATH).append(imgPath);
                }
                record.setImgPaths(paths.substring(1).toString());
            }
        }
        return page.setRecords(records);
    }
}
