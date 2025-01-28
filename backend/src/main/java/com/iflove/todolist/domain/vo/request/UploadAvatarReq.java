package com.iflove.todolist.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Data
@Schema(description = "头像上传请求")
public class UploadAvatarReq {
    @Schema(description = "头像 url")
    @NotBlank
    private String url;
}
