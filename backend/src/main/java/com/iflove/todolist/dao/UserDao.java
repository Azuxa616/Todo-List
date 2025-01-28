package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.User;
import com.iflove.todolist.mapper.UserMapper;
import org.springframework.stereotype.Service;

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
}