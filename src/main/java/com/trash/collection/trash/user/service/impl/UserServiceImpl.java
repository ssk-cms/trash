package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.User;
import com.trash.collection.trash.user.dao.UserMapper;
import com.trash.collection.trash.user.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统用户 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

}
