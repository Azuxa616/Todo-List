package com.iflove.todolist.service;


import com.iflove.todolist.domain.vo.request.user.PasswordResetReq;
import com.iflove.todolist.domain.vo.request.user.UserInfoModifyReq;
import com.iflove.todolist.domain.vo.request.user.UserLoginReq;
import com.iflove.todolist.domain.vo.request.user.UserRegisterReq;
import com.iflove.todolist.domain.vo.response.user.UserInfoResp;
import com.iflove.todolist.domain.vo.response.user.UserLoginInfoResp;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface UserService {

    /**
     * 用户登录
     * @param req 用户登录请求
     * @return {@link UserLoginInfoResp}
     */
    UserLoginInfoResp login(UserLoginReq req);

    /**
     * 用户注册
     * @param userRegisterReq 注册信息
     */
    void register(UserRegisterReq userRegisterReq);

    /**
     * 用户登出
     */
    void logout();

    /**
     * 重置密码
     * @param req 新密码
     */
    void reset(PasswordResetReq req);

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return {@link UserInfoResp}
     */
    UserInfoResp getUserInfo(Long uid);

    /**
     * 上传头像
     * @param url 头像下载链接
     */
    void uploadAvatar(String url, Long uid);

    /**
     * 用户信息修改
     * @param req 用户信息修改请求
     */
    void modifyUserInfo(UserInfoModifyReq req, Long uid);
}
