package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.User;
import com.iflove.todolist.domain.vo.request.UserInfoModifyReq;
import com.iflove.todolist.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author IFLOVE
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-09-17 14:57:36
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

    /**
     * 根据名字获取User
     * @param name
     * @return
     */
    public User getUserByName(String name) {
        return lambdaQuery()
                .eq(User::getName, name)
                .one();
    }

    /**
     * 根据 uid 更新头像 url
     * @param url 头像 url
     * @param uid 用户 id
     */
    public void uploadAvatar(String url, Long uid) {
        lambdaUpdate()
                .eq(User::getId, uid)
                .set(User::getAvatar, url)
                .update();
    }

    public void modifyUserInfo(UserInfoModifyReq req, Long uid) {
        lambdaUpdate()
                .eq(User::getId, uid)
                .set(Objects.nonNull(req.getEmail()), User::getEmail, req.getEmail())
                .set(Objects.nonNull(req.getUsername()), User::getName, req.getUsername())
                .set(Objects.nonNull(req.getSignature()), User::getSignature, req.getSignature())
                .set(Objects.nonNull(req.getPhone()), User::getPhone, req.getPhone())
                .set(Objects.nonNull(req.getSex()), User::getSex, req.getSex())
                .update();
    }
}