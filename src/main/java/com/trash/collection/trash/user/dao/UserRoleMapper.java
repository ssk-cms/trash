package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
