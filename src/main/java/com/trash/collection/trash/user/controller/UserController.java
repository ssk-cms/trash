package com.trash.collection.trash.user.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.common.utils.DetailCookieUtil;
import com.trash.collection.trash.product.VO.PageVO;
import com.trash.collection.trash.user.JWTUtils.JwtProperties;
import com.trash.collection.trash.user.JWTUtils.JwtUtils;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.user.VO.UserInfo;
import com.trash.collection.trash.user.VO.UserVO;
import com.trash.collection.trash.user.dao.UserMapper;
import com.trash.collection.trash.user.domain.User;
import com.trash.collection.trash.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 系统用户 控制器
 *
 * @author seth
 * @since 2019-12-28
 */
@RestController
@RequestMapping("/user/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductKindService productKindService;

    @Autowired
    private JwtProperties prop;

    @Value("${fq.jwt.cookieName}")
    private String cookieName;

    /**
     * 注册
     */
    @PostMapping("/register")
    public Response register(@RequestBody User user, HttpServletRequest request) {
        if (Objects.isNull(user)) {
            return productKindService.judge("请传入参数");
        }
        if (Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword())) {
            return productKindService.judge("请输入用户名和密码");
        }
        userService.register(user, request);
        Response response = new Response();
        return response;
    }

    /**
     * 登录/登录授权
     */
    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpServletResponse response,HttpServletRequest request) throws Exception {
        Response response1 = new Response();
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = this.userMapper.selectByName(username);
        if (Objects.equals(user1.getStatus(), 0)) {
            return productKindService.judge("该用户已被禁用");
        }
        // 登录
        String token = userService.login(username, password);
        // 解析token
        UserInfo info = this.userService.getUserInfo(token);
        //根据用户姓名查询用户权限
        User userResult = this.userService.selectOne(new EntityWrapper<User>().eq("username", info.getUsername()));
        UserVO userVO = this.setUserVO(userResult);
        // 写入cookie
        DetailCookieUtil.set(response, cookieName, token, "127.0.0.1", "/", 1 * 24 * 60 * 60, false);
        return response1.setData(userVO);
    }

    /**
     * 检验登录状态
     */
    @GetMapping("/verify")
    public Response verify(@CookieValue("TRASH_TOKEN") String token, HttpServletResponse response) {
        if (Objects.isNull(token)) {
            return productKindService.judge("请重新登录");
        }
        Response response1 = new Response();
        try {
            // 解析token
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //根据用户姓名查询用户权限
            User userResult = this.userService.selectOne(new EntityWrapper<User>().eq("username", info.getUsername()));
            UserVO userVO = this.setUserVO(userResult);
            // 重新生成token,刷新token过期时间
            String newToken = JwtUtils.generateToken(info, prop.getPrivateKey(), prop.getExpire());

            // 写入cookie
            DetailCookieUtil.set(response, cookieName, newToken, "127.0.0.1", "/", 1 * 24 * 60 * 60, false);
            // 已登录，返回用户信息
            return response1.setData(userVO);
        } catch (Exception e) {
            // token已过期，或者 token被篡改
            return productKindService.judge("token已过期请重新登录");
        }
    }

    /**
     * 设置返回用户的用户信息
     */
    private UserVO setUserVO(User userResult) {
        UserVO userVO = new UserVO();
        userVO.setId(userResult.getUserId());
        userVO.setUsername(userResult.getUsername());
        userVO.setIsSuperuser(userResult.getIsSuperuser());
        return userVO;
    }

    /**
     * 管理员--查看所有用户信息
     * */
    @GetMapping("/getAllList")
    public Response getAllList(PageVO pageVO,String userName){
        Response response = new Response();
        response.setData(this.userService.getAllList(pageVO,userName));
        return response;
    }

    /**
     * 管理员--禁用用户账号
     * */
    @GetMapping("/forbiddenUser")
    public Response forbiddenUser(Integer userId){
        if (Objects.isNull(userId)){
            throw new RRException("请选择用户");
        }
        this.userService.forbiddenUser(userId);
        return new Response();
    }

    /**
     * 管理员--重置用户密码为123456
     * */
    @GetMapping("/resetPassword")
    public Response resetPassword( Integer userId){
        if (Objects.isNull(userId)){
            throw new RRException("请选择用户!");
        }
        this.userService.resetPassWord(userId);
        return new Response();
    }

    /**
     * 管理员--将普通用户设为管理员权限
     * */
    @GetMapping("/setSuperUser")
    public Response setSuperUser(Integer userId){
        if (Objects.isNull(userId)){
            throw new RRException("请选择用户!");
        }
        this.userService.setSuperUser(userId);
        return new Response();
    }

    /**
     * 用户-修改自己的密码
     * */
    @PostMapping("/updatePassword")
    public Response updatePassword(@RequestBody User user){
        if (Objects.isNull(user)){
            throw new RRException("请输入个人信息！");
        }
        if (Objects.isNull(user.getPassword())){
            throw new RRException("请输入密码！");
        }
        this.userService.updatePassword(user);
        return new Response();
    }
}

