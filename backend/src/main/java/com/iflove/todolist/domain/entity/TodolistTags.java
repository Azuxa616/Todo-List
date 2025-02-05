package com.iflove.todolist.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.*;

/**
 * 任务与标签的关联表
 * @TableName todolist_tags
 */
@TableName(value ="todolist_tags")
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodolistTags implements Serializable {
    /**
     * 任务ID
     */
    @TableField(value = "task_id")
    private Long task_id;

    /**
     * 标签ID
     */
    @TableField(value = "tag_id")
    private Long tag_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}