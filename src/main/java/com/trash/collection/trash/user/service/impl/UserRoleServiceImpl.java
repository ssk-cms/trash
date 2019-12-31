package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.UserRole;
import com.trash.collection.trash.user.dao.UserRoleMapper;
import com.trash.collection.trash.user.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户与角色对应关系 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>implements UserRoleService {

}
