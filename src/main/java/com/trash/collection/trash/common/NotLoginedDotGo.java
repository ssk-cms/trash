package com.trash.collection.trash.common;

import com.trash.collection.trash.common.utils.CookieUtils;
import com.trash.collection.trash.user.JWTUtils.JwtProperties;
import com.trash.collection.trash.user.JWTUtils.JwtUtils;
import com.trash.collection.trash.user.VO.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未登录则不放行
 */
@Slf4j
@Component
public class NotLoginedDotGo implements HandlerInterceptor {

    private JwtProperties prop;

    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public NotLoginedDotGo(JwtProperties prop) {
        this.prop = prop;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        // 获取cookie中的token
        String token = CookieUtils.getCookieValue(request, prop.getCookieName());
        try {
            // 解析token
            UserInfo user = JwtUtils.getInfoFromToken(token, prop.getPublicKey());

            // 传递user
//            request.setAttribute("user", user);
            threadLocal.set(user);   // 不需要自己设置k值，否则开发人员自己会传错，也只有当前线程才能取到v值
            // 放行
            return  true;
        } catch (Exception e) {
            log.error("[拦截器] 解析用户身份失败! ", e);
            throw new RRException("请重新登录！");
        }

    }

    public static UserInfo getUser() {
        return threadLocal.get();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }


}

