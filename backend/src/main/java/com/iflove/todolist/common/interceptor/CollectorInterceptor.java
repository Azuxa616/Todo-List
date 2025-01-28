package com.iflove.todolist.common.interceptor;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.jwt.JWTUtil;
import com.iflove.todolist.common.domain.dto.RequestInfo;
import com.iflove.todolist.common.utils.RequestHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 信息收集拦截器
 */
@Component
@Order(1)
@Slf4j
public class CollectorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestInfo info = new RequestInfo();
        String token = request.getHeader("Authorization").substring(7);
        // 设置 token
        info.setToken(token);
        Long uid = ((NumberWithFormat) JWTUtil.parseToken(token).getPayload("uid")).longValue();
        // 设置uid
        info.setUid(uid);
        RequestHolder.set(info);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
    }
}
