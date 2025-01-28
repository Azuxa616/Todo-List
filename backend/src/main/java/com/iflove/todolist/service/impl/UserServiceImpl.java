package com.iflove.todolist.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.iflove.todolist.common.constant.Const;
import com.iflove.todolist.common.constant.RedisKey;
import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.common.utils.RedisUtil;
import com.iflove.todolist.dao.UserDao;
import com.iflove.todolist.domain.entity.User;
import com.iflove.todolist.domain.vo.request.UserRegisterReq;
import com.iflove.todolist.domain.vo.response.UserLoginInfoResp;
import com.iflove.todolist.service.UserService;
import com.iflove.todolist.service.adapter.UserAdapter;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserLoginInfoResp login(String username, String password) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new BadCredentialsException("验证失败");
        }
        User user = userDao.getUserByName(username);
        // 获取token
        String uuid = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, Const.EXPIRE_TIME);
        Date expireTime = calendar.getTime();
        Map<String, Object> map = Map.of("jwt_id", uuid, "username", username, "expire_time", expireTime, "uid", user.getId());
        String token = JWTUtil.createToken(map, Const.JWT_SIGN_KEY.getBytes());
        // 将token存入redis
        RedisUtil.set(RedisKey.getKey(RedisKey.JWT_WHITE_LIST, uuid), "", Const.EXPIRE_TIME, TimeUnit.HOURS);
        // 返回结果集
        return UserAdapter.buildUserLoginInfoResp(user, v -> {
            v.setToken(token);
            v.setExpireTime(expireTime);
        });
    }

    @Override
    public void register(UserRegisterReq userRegisterReq) {
        User user = User.builder()
                .name(userRegisterReq.getUsername())
                .password(passwordEncoder.encode(userRegisterReq.getPassword()))
                .sex(userRegisterReq.getSex())
                .build();
        userDao.save(user);
    }
}




