package com.iflove.todolist.domain.vo.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 分类名传递请求
 */

@Data
@Schema(description = "分类名传递请求")
public class CategoryNameReq {

    @Schema(description = "分类名列表(1 or n), 分类名长度不大于10")
    @NotEmpty(message = "分类名列表不能为空")
    private List<@Length(min = 1, max = 10, message = "分类名称长度应该在 1-10之间") String> categoryNameList;
}
