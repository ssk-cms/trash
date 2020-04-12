package com.trash.collection.trash.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.trash.collection.trash.user.VO.UserInfo;
import com.trash.collection.trash.user.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户 服务类
 *
 * @author seth
 * @since 2019-12-28
 */
public interface UserService extends IService<User> {

    /**
     * 注册账号
     * */
    void register(User user, HttpServletRequest request);

    /**
     * 登录
     * */
    String login(String username, String password) throws Exception;

    /**
     * 获取公钥
     * */
    UserInfo getUserInfo(String token) throws Exception;
}
