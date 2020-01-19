package com.trash.collection.trash.address.service.impl;

import com.trash.collection.trash.address.domain.UserAddress;
import com.trash.collection.trash.address.dao.UserAddressMapper;
import com.trash.collection.trash.address.service.UserAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>implements UserAddressService {

}
