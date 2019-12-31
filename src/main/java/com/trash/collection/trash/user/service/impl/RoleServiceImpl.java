package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.Role;
import com.trash.collection.trash.user.dao.RoleMapper;
import com.trash.collection.trash.user.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>implements RoleService {

}
