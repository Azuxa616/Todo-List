package com.iflove.todolist.domain.vo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户信息修改请求
 */

@Schema(description = "用户信息修改请求")
@Data
public class UserInfoModifyReq {
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$", message = "用户名只能包含字母、数字或汉字")
    @Length(min = 1, max = 10, message = "用户名长度应在1到10之间")
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "性别")
    @Min(value = 1, message = "性别只能为1或2")
    @Max(value = 2, message = "性别只能为1或2")
    private Integer sex;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "用户邮箱")
    private String email;

    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号格式不正确")
    @Schema(description = "用户手机号")
    private String phone;

    @Schema(description = "用户个性签名")
    private String signature;
}
