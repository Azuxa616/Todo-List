package com.iflove.todolist.domain.vo.request.tags;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 标签名传递请求
 */

@Data
@Schema(description = "标签名传递请求")
public class TagsNameReq {

    @Schema(description = "标签名列表(1 or n), 标签名长度不大于10")
    @NotEmpty(message = "标签名列表不能为空")
    private List<@Length(min = 1, max = 10, message = "标签名称长度应该在 1-10之间") String> tagNameList;
}
