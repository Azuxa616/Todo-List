package com.iflove.todolist.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.jwt.JWTUtil;
import com.iflove.todolist.common.constant.Const;
import com.iflove.todolist.common.constant.RedisKey;
import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.common.exception.BusinessException;
import com.iflove.todolist.common.exception.CommonErrorEnum;
import com.iflove.todolist.common.exception.UserErrorEnum;
import com.iflove.todolist.common.utils.RedisUtil;
import com.iflove.todolist.common.utils.RequestHolder;
import com.iflove.todolist.dao.UserDao;
import com.iflove.todolist.domain.entity.User;
import com.iflove.todolist.domain.vo.request.PasswordResetReq;
import com.iflove.todolist.domain.vo.request.UserInfoModifyReq;
import com.iflove.todolist.domain.vo.request.UserLoginReq;
import com.iflove.todolist.domain.vo.request.UserRegisterReq;
import com.iflove.todolist.domain.vo.response.UserInfoResp;
import com.iflove.todolist.domain.vo.response.UserLoginInfoResp;
import com.iflove.todolist.service.UserService;
import com.iflove.todolist.service.adapter.UserAdapter;
import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 用户登录
     * @param req 用户登录请求
     * @return {@link UserLoginInfoResp}
     */
    @Override
    public UserLoginInfoResp login(UserLoginReq req) {
        String username = req.getName();
        String password = req.getPassword();
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

    /**
     * 用户注册
     * @param userRegisterReq 注册信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterReq userRegisterReq) {
        User user = User.builder()
                .name(userRegisterReq.getUsername())
                .password(passwordEncoder.encode(userRegisterReq.getPassword()))
                .sex(userRegisterReq.getSex())
                .email(userRegisterReq.getEmail())
                .phone(userRegisterReq.getPhone())
                .signature(userRegisterReq.getSignature())
                .build();
        userDao.save(user);
    }

    /**
     * 用户登出
     */
    @Override
    public void logout() {
        String token = RequestHolder.get().getToken();
        String jwtId = (String) JWTUtil.parseToken(token).getPayload("jwt_id");
        // 禁止重复登出
        if (!RedisUtil.hasKey(RedisKey.getKey(RedisKey.JWT_WHITE_LIST, jwtId))) {
            throw new BusinessException(UserErrorEnum.FREQUENT_LOGOUT_ERROR);
        }
        // 登出
        RedisUtil.del(RedisKey.getKey(RedisKey.JWT_WHITE_LIST, jwtId));
    }

    /**
     * 重置密码
     * @param req 新密码
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reset(PasswordResetReq req) {
        User user = userDao.getById(RequestHolder.get().getUid());
        // 用户不存在
        if (Objects.isNull(user)) {
            throw new BusinessException(UserErrorEnum.USER_NOT_FOUND);
        }
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        // 更新密码
        userDao.updateById(user);
    }

    @Override
    public UserInfoResp getUserInfo(Long uid) {
        User user = userDao.getById(uid);
        // 用户不存在
        if (Objects.isNull(user)) {
            throw new BusinessException(UserErrorEnum.USER_NOT_FOUND);
        }
        return UserAdapter.buildUserInfoResp(user);
    }

    /**
     * 上传头像
     * @param url 头像下载链接
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadAvatar(String url, Long uid) {
        boolean valid = Validator.isUrl(url);
        if (!valid) {
            throw new ValidationException("非法链接");
        }
        userDao.uploadAvatar(url, uid);
    }

    /**
     * 用户信息修改
     * @param req 用户信息修改请求
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyUserInfo(UserInfoModifyReq req, Long uid) {
        userDao.modifyUserInfo(req, uid);
    }
}




