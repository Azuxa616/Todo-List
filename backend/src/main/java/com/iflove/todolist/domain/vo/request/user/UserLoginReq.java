package com.iflove.todolist.domain.vo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户登录请求
 */

@Schema(description = "用户登录请求")
@Data
public class UserLoginReq {

    @Length(min = 1, max = 20)
    @NotBlank
    @Schema(description = "用户名")
    private String name;

    @Length(min = 6, max = 20)
    @NotBlank
    @Schema(description = "密码")
    private String password;
}
