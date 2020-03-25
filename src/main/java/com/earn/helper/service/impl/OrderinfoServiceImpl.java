package com.earn.helper.service.impl;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Orderinfo;
import com.earn.helper.mapper.OrderinfoMapper;
import com.earn.helper.service.IOrderinfoService;

import java.util.List;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Service
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoMapper, Orderinfo> implements IOrderinfoService {
    @Autowired
    private OrderinfoMapper orderinfoMapper;

    @Override
    public Page<Orderinfo> pageList(Orderinfo orderinfo) {
        Page<Orderinfo> page = new Page<>(orderinfo.getPage(), orderinfo.getSize());
        List<Orderinfo> records = orderinfoMapper.selectListPage(page, orderinfo);
        for (Orderinfo record : records) {
            if (record.getProduct() != null && !StringUtil.isNullAndBlank(record.getProduct().getImgPath())) {
                record.getProduct().setImgPath(Const.FILE_PATH + record.getProduct().getImgPath());
            }
        }
        return page.setRecords(records);
    }

    @Override
    public Orderinfo getOrderById(Integer id) {
        Orderinfo orderinfo = orderinfoMapper.getOrderById(id);
        if (orderinfo.getProduct() != null && !StringUtil.isNullAndBlank(orderinfo.getProduct().getImgPath())) {
            orderinfo.getProduct().setImgPath(Const.FILE_PATH + orderinfo.getProduct().getImgPath());
        }
        return orderinfo;
    }
}
