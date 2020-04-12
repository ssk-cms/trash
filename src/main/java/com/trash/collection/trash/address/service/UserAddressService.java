package com.trash.collection.trash.address.service;

import com.trash.collection.trash.address.domain.UserAddress;
import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface UserAddressService extends IService<UserAddress> {

    /**
     * 新增地址信息
     * */
    void addAddress(UserAddress userAddress);
}
