package com.iflove.todolist.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Data
@Schema(description = "密码重置请求")
public class PasswordResetReq {
    @Schema(description = "密码")
    @Length(min = 6, max = 20)
    @NotBlank
    private String password;
}
