package com.filecloud.api.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user_id") == null) {
            System.out.println("user_id為null");
            response.setStatus(401); 
            return false; // 中斷後續處理
        }
        System.out.println("session中的uid為= " + request.getSession().getAttribute("user_id"));
        return true; // 放行
    }
}
