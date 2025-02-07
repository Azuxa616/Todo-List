package com.iflove.todolist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskInfoDto {
    /**
     * 任务id
     */
    private Long id;

    /**
     * 用户id，关联用户表
     */
    private Long uid;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务截止日期
     */
    private Date dueDate;

    /**
     * 分类名
     */
    private String categoryName;
}
