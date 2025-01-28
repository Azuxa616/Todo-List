package com.iflove.todolist.controller;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.domain.vo.request.UserLoginReq;
import com.iflove.todolist.domain.vo.request.UserRegisterReq;
import com.iflove.todolist.domain.vo.response.UserLoginInfoResp;
import com.iflove.todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@RestController
@RequestMapping("api/user/auth")
@Validated
@Tag(name = "用户权限模块")
@RequiredArgsConstructor
public class AuthorizeController {
    private final UserService userService;

    /**
     * 用户登录
     * @param req 用户登录请求
     * @return {@link RestBean}<{@link UserLoginInfoResp}
     */
    @PostMapping("login")
    @Operation(summary = "登录", description = "用户登录", security = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "502", description = "参数校验失败"),
            @ApiResponse(responseCode = "501", description = "服务器内部错误")
    })
    public RestBean<UserLoginInfoResp> login(@RequestBody @Valid UserLoginReq req) {
        return RestBean.success(userService.login(req));
    }

    /**
     * 用户注册
     * @param userRegisterReq 注册信息
     * @return {@link RestBean}
     */
    @PostMapping("register")
    @Operation(summary = "注册", description = "用户注册", security = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "502", description = "参数校验失败"),
            @ApiResponse(responseCode = "501", description = "服务器内部错误")
    })
    public RestBean<Void> register(@RequestBody @Valid UserRegisterReq userRegisterReq) {
        userService.register(userRegisterReq);
        return RestBean.success();
    }
}
