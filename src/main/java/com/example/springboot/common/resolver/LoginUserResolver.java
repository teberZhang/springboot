package com.example.springboot.common.resolver;

import com.example.springboot.common.annotation.LoginUser;
import com.example.springboot.common.interceptor.AuthenticationInterceptor;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private AdminUserService adminUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(AdminUser.class)
                && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory
    ) {
        Object attribute = nativeWebRequest.getAttribute(AuthenticationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (attribute == null) {
            return null;
        }
        return adminUserService.findUserById(Integer.parseInt((String) attribute));
    }
}
