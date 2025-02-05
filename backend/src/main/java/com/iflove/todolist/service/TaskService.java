package com.iflove.todolist.service;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.domain.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;
import jakarta.validation.Valid;

/**
* @author cangjingyue
* @description 针对表【task(Todolist 任务表)】的数据库操作Service
* @createDate 2025-02-05 08:57:46
*/
public interface TaskService {

    /**
     * 创建任务
     * @param req 创建任务请求
     * @param uid 用户 id
     */
    void create(CreateTaskReq req, Long uid);
}
