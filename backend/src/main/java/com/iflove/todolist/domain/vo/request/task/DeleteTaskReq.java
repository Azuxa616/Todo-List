package com.iflove.todolist.domain.vo.request.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 任务删除请求
 */

@Data
@Schema(description = "任务删除请求")
public class DeleteTaskReq {

    @Schema(description = "任务 id")
    @NotNull
    private Long id;
}
