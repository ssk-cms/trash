package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * */
    User selectByName(String username);

    /**
     * 管理员--查看所有用户信息
     * */
    List<User> getAllList(Page page, @Param("userName") String userName);
}
