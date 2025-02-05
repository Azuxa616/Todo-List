package com.iflove.todolist.service;

import com.iflove.todolist.domain.vo.request.tags.CreateTagsReq;

/**
* @author cangjingyue
* @description 针对表【tags(任务标签表)】的数据库操作Service
* @createDate 2025-02-05 08:57:46
*/
public interface TagsService {

    /**
     * 创建用户的任务标签(用户独有), 可以一次创建多个
     * @param req 任务标签创建请求
     * @param uid 用户 id
     */
    void create(CreateTagsReq req, Long uid);
}
