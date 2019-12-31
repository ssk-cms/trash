package com.trash.collection.trash.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.user.dao.MenuMapper;
import com.trash.collection.trash.user.domain.Menu;
import com.trash.collection.trash.user.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单管理 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>implements MenuService {

}
