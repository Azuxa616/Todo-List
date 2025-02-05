package com.iflove.todolist.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * Todolist 任务表
 * @TableName task
 */
@TableName(value ="task")
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task implements Serializable {
    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id，关联用户表
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 任务标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 任务描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 任务状态 0: 未完成, 1: 已完成
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 任务星标 0: 无星标, 1: 有星标
     */
    @TableField(value = "star")
    private Integer star;

    /**
     * 任务截止日期
     */
    @TableField(value = "due_date")
    private Date due_date;

    /**
     * 任务创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 任务修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 任务所属分类ID
     */
    @TableField(value = "category_id")
    private Long category_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}