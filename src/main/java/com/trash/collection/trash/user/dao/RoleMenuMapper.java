package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与菜单对应关系 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}
