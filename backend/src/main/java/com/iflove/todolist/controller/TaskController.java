package com.iflove.todolist.controller;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.common.utils.RequestHolder;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;
import com.iflove.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@RestController
@RequestMapping("/api/task")
@Validated
@Tag(name = "任务模块")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // TODO 删除一个 task，修改 task，查询 task(日期)

    /**
     * 创建任务
     * @param req 创建任务请求
     * @return {@link RestBean}
     */
    @PostMapping("create")
    @Operation(summary = "创建任务",
            description = "创建任务",
            security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
    })
    public RestBean<Void> create(@RequestBody @Valid CreateTaskReq req) {
        taskService.create(req, RequestHolder.get().getUid());
        return RestBean.success();
    }
}
