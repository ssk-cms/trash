package com.trash.collection.trash.address.service.impl;

import com.trash.collection.trash.address.domain.UserAddress;
import com.trash.collection.trash.address.dao.UserAddressMapper;
import com.trash.collection.trash.address.service.UserAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>implements UserAddressService {

    /**
     * 新增地址信息
     * */
    @Override
    @Transactional
    public void addAddress(UserAddress userAddress){
        Date date = new Date();
        userAddress.setAddressType(1)
                .setState(1)
                .setCreateTime(date)
                .setModifyTime(date);
        this.baseMapper.insert(userAddress);
    }
}
