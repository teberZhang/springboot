package com.example.springboot.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.common.Constants;
import com.example.springboot.common.annotation.NeedAuth;
import com.example.springboot.common.annotation.PassToken;
import com.example.springboot.common.exception.CustomException;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    public static final String TOKEN_KEY = "token";
    public static final String USER_KEY = "userId";

    @Autowired
    AdminUserService adminUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        if (!method.isAnnotationPresent(NeedAuth.class)) {
            return true;
        }
        String token = request.getHeader(TOKEN_KEY);

        NeedAuth needAuth = method.getAnnotation(NeedAuth.class);
        if (!needAuth.required()) {
            return true;
        }
        if (StrUtil.isBlank(token)) {
            throw new CustomException(Constants.E40001, "请重新登录");
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new CustomException(Constants.E40001, "系统异常,请重新登录");
        }
        AdminUser adminUser = adminUserService.findUserById(Integer.parseInt(userId));
        if (adminUser == null) {
            throw new CustomException(Constants.E40001, "未找到该用户,请重新登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(adminUser.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new CustomException(Constants.E40001, "登录状态已过期,请重新登录");
        }
        if (StrUtil.isBlank(adminUser.getType())) {
            throw new CustomException(Constants.E40001, "当前用户身份无效");
        }
        request.setAttribute(USER_KEY, userId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
