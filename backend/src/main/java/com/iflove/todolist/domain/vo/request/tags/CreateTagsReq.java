package com.iflove.todolist.domain.vo.request.tags;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Data
@Schema(description = "标签创建请求")
public class CreateTagsReq {

    @Schema(description = "标签列表(1 or n)")
    @NotEmpty(message = "标签列表不能为空")
    private List<@Length(min = 1, max = 10, message = "标签名称长度应该在 1-10之间") String> tags;
}
