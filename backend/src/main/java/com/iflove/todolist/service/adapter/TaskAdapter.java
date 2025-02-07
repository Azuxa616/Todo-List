package com.iflove.todolist.service.adapter;

import com.iflove.todolist.domain.dto.TaskInfoDto;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;
import com.iflove.todolist.domain.vo.request.task.ModifyTaskReq;

import java.util.Date;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class TaskAdapter {

    public static TaskInfoDto buildTaskInfoDto(CreateTaskReq req, Long uid) {
        return doBuildTaskInfoDto(null, req.getTitle(), req.getCategoryName(), req.getDueDate(), req.getDescription(),  uid);
    }

    public static TaskInfoDto buildTaskInfoDto(ModifyTaskReq req, Long uid) {
        return doBuildTaskInfoDto(req.getId(), req.getTitle(), req.getCategoryName(), req.getDueDate(), req.getDescription(),  uid);
    }

    private static TaskInfoDto doBuildTaskInfoDto(Long id, String title, String categoryName, Date dueDate, String description, Long uid) {
        return TaskInfoDto.builder()
                .id(id)
                .title(title)
                .categoryName(categoryName)
                .dueDate(dueDate)
                .description(description)
                .uid(uid)
                .build();
    }
}
