package com.iflove.todolist.service;


import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.domain.vo.request.UserRegisterReq;
import com.iflove.todolist.domain.vo.response.UserLoginInfoResp;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return {@link UserLoginInfoResp}
     */
    UserLoginInfoResp login(String username, String password);

    /**
     * 用户注册
     * @param userRegisterReq 注册信息
     */
    void register(UserRegisterReq userRegisterReq);

}
