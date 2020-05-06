package com.trash.collection.trash.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.common.utils.CodecUtils;
import com.trash.collection.trash.product.VO.PageVO;
import com.trash.collection.trash.user.JWTUtils.JwtProperties;
import com.trash.collection.trash.user.JWTUtils.JwtUtils;
import com.trash.collection.trash.user.VO.UserInfo;
import com.trash.collection.trash.user.domain.User;
import com.trash.collection.trash.user.dao.UserMapper;
import com.trash.collection.trash.user.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * 系统用户 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtProperties prop;

    /**
     * 注册账号
     */
    @Override
    @Transactional
    public void register(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        this.validParameter(username, password);
        //校验用户名是否重复
        User userRsult = this.baseMapper.selectByName(username);
        if (Objects.nonNull(userRsult)) {
            throw new RRException("用户名已被注册！");
        }
        Date date = new Date();
        try {
            // 生成盐
            String salt = CodecUtils.generateSalt();
            // 写入数据库
            user.setPassword(CodecUtils.md5Hex(password, salt));
            user.setSalt(salt);
            user.setIsSuperuser(0);
            user.setStatus(1);
            user.setCreateTime(date)
                    .setModifyTime(date);
            this.baseMapper.insert(user);
        } catch (Exception e) {
            throw new RRException("注册失败");
        }
    }

    /**
     * 登录
     */
    @Override
    public String login(String username, String password) throws Exception {
        // 校验用户名和密码
        if (StringUtils.isBlank(username)) {
            throw new RRException("请输入用户名");
        }
        if (StringUtils.isBlank(password)) {
            throw new RRException("请输入密码");
        }
        User user = this.baseMapper.selectByName(username);

        if (Objects.isNull(user) || StringUtils.isBlank(user.getPassword())) {
            throw new RRException("没有该用户");
        }

        String afterEncodingPwd = CodecUtils.md5Hex(password, user.getSalt());
        if (!(user.getPassword().equals(afterEncodingPwd))) {
            throw new RRException("请输入正确的密码");
        }

        // return生成的token
        return JwtUtils.generateToken(new UserInfo(user.getUserId().longValue(), username), prop.getPrivateKey(), prop.getExpire());
    }

    /**
     * 获取公钥
     */
    @Override
    public UserInfo getUserInfo(String token) throws Exception {
        UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
        return info;
    }

    /**
     * 校验参数
     *
     * @param G_username
     * @param G_pwd
     */
    private void validParameter(String G_username, String G_pwd) {
        if (StringUtils.isBlank(G_username) || G_username.length() > 9 || G_pwd.length() > 10) {
            throw new RRException("用户名和密码不符合规范（用户名长度不大于9，密码长度不大于10）");
        }
        //判断用户名合法性
        for (int i = 0; i < G_username.length(); i++) {
            char c = G_username.charAt(i);
            if (
                    (int) c < 65 ||
                            ((int) c > 90 && (int) c < 97) ||
                            ((int) c > 122 && (int) c < 19968) ||
                            (int) c > 40869

            ) {
                //字符串含有非法字符
                throw new RRException("请输入合法的用户名！不能包含非法字符！");
            }
        }
        //判断密码合法性
        for (int i = 0; i < G_pwd.length(); i++) {
            char c = G_pwd.charAt(i);
            if (
                    (int) c < 48 ||
                            ((int) c > 57 && (int) c < 65) ||
                            ((int) c > 90 && (int) c < 97) ||
                            (int) c > 122

            ) {
                throw new RRException("密码请不要输入特殊字符！");
            }
        }
    }

    /**
     * 管理员--查看所有用户信息
     */
    @Override
    public Page getAllList(PageVO pageVO, String userName) {
        Page page = new Page(pageVO.getPageIndex(), pageVO.getPageSize());
        return page.setRecords(this.baseMapper.getAllList(page, userName));
    }

    /**
     * 管理员--禁用用户账号
     */
    @Override
    @Transactional
    public void forbiddenUser(Integer userId) {
        User user = new User();
        user.setUserId(userId)
                .setStatus(0)
                .setModifyTime(new Date());
        this.baseMapper.updateById(user);
    }

    /**
     * 管理员--重置用户密码为123456
     */
    @Override
    @Transactional
    public void resetPassWord(Integer userId) {
        String password = "123456";
        User user = new User();
        // 生成盐
        String salt = CodecUtils.generateSalt();
        // 写入数据库
        user.setPassword(CodecUtils.md5Hex(password, salt));
        user.setUserId(userId)
                .setModifyTime(new Date())
                .setSalt(salt);
        this.baseMapper.updateById(user);
    }

    /**
     * 管理员--将普通用户设为管理员权限
     */
    @Override
    @Transactional
    public void setSuperUser(Integer userId) {
        User user = new User();
        user.setUserId(userId)
                .setIsSuperuser(1)
                .setModifyTime(new Date());
        this.baseMapper.updateById(user);
    }

    /**
     * 用户-修改自己的密码
     */
    @Override
    @Transactional
    public void updatePassword(User user) {
        if (user.getPassword().length() > 10) {
            throw new RRException("密码不符合规范(密码长度不大于10）");
        }
        UserInfo userInfo = NotLoginedDotGo.getUser();
        // 生成盐
        String salt = CodecUtils.generateSalt();
        // 写入数据库
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        user.setUserId(userInfo.getId().intValue())
                .setModifyTime(new Date())
                .setSalt(salt);
        this.baseMapper.updateById(user);
    }
}
