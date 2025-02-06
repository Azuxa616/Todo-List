package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.dto.TaskInsertDto;
import com.iflove.todolist.domain.entity.Task;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;
import com.iflove.todolist.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class TaskDao extends ServiceImpl<TaskMapper, Task> {

    /**
     * 创建任务
     * @param dto 任务插入
     */
    public void create(TaskInsertDto dto) {
        baseMapper.create(dto);
    }

    /**
     * 查询任务
     * @param id 任务 id
     * @param uid 用户 id
     */
    public Task queryByIdAndUid(Long id, Long uid) {
        return baseMapper.queryByIdAndUid(id, uid);
    }

    /**
     * 删除任务
     * @param id 任务 id
     * @param uid 用户 id
     */
    public void delete(Long id, Long uid) {
        lambdaUpdate()
                .eq(Task::getId, id)
                .eq(Task::getUser_id, uid)
                .remove();
    }
}
