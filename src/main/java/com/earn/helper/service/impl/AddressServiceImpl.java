package com.earn.helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Address;
import com.earn.helper.mapper.AddressMapper;
import com.earn.helper.service.IAddressService;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Page<Address> pageList(Address address) {
        Page<Address> page = new Page<>(address.getPage(), address.getSize());
        return page.setRecords(addressMapper.selectListPage(page, address));
    }

    @Override
    public Integer removeDefault(Integer userId) {
        Address address = new Address();
        address.setDefaultAddr(false);
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("default_addr", true);

        return addressMapper.update(address, wrapper);
    }

    @Override
    public Integer setDefault(Integer addrId) {
        Address address = new Address();
        address.setAddrId(addrId);
        address.setDefaultAddr(true);
        return addressMapper.updateById(address);
    }
}
