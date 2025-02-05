package com.iflove.todolist.service.adapter;

import com.iflove.todolist.domain.dto.TaskInsertDto;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class TaskAdapter {

    public static TaskInsertDto buildTaskInsertDto(CreateTaskReq req, Long uid) {
        return TaskInsertDto.builder()
                .title(req.getTitle())
                .categoryName(req.getCategoryName())
                .dueDate(req.getDueDate())
                .description(req.getDescription())
                .uid(uid)
                .build();
    }
}
