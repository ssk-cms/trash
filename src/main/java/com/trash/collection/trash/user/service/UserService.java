package com.trash.collection.trash.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.trash.collection.trash.product.VO.PageVO;
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

    /**
     * 管理员--查看所有用户信息
     * */
    Page getAllList(PageVO pageVO, String userName);

    /**
     * 管理员--禁用用户账号
     * */
    void forbiddenUser(Integer userId);

    /**
     *管理员--重置用户密码为123456
     * */
    void resetPassWord(Integer userId);

    /**
     * 管理员--将普通用户设为管理员权限
     * */
    void setSuperUser(Integer userId);
}
