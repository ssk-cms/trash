package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.RoleMenu;
import com.trash.collection.trash.user.dao.RoleMenuMapper;
import com.trash.collection.trash.user.service.RoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色与菜单对应关系 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>implements RoleMenuService {

}
