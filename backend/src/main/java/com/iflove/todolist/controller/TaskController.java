package com.iflove.todolist.controller;

import com.iflove.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

    // TODO 创建一个 task，删除一个 task，修改 task，查询 task(日期)

}
