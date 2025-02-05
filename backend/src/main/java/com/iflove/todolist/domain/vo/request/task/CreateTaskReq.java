package com.iflove.todolist.domain.vo.request.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 任务创建请求
 */

@Data
@Schema(description = "任务创建请求")
public class CreateTaskReq {

    @Schema(description = "任务标题")
    @NotBlank
    private String title;

    @Schema(description = "任务描述")
    @Nullable
    private String description;

    @Schema(description = "截止日期")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dueDate;

    @Schema(description = "分类名")
    @Length(min = 1, max = 10, message = "分类名称长度应该在 1-10之间")
    @Nullable
    private String categoryName;

    @Schema(description = "标签名列表")
    @Nullable
    private List<@Length(min = 1, max = 10, message = "标签名称长度应该在 1-10之间") String> tagNameList;
}
