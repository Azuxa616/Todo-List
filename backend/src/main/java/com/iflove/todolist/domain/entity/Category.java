package com.iflove.todolist.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * 任务分类表
 * @TableName category
 */
@TableName(value ="category")
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category implements Serializable {
    /**
     * 分类ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID，表示分类属于哪个用户
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}