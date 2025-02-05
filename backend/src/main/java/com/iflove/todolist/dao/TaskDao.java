package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.Task;
import com.iflove.todolist.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class TaskDao extends ServiceImpl<TaskMapper, Task> {
}
