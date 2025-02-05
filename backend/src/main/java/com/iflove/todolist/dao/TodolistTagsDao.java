package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.TodolistTags;
import com.iflove.todolist.mapper.TodolistTagsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class TodolistTagsDao extends ServiceImpl<TodolistTagsMapper, TodolistTags> {

    /**
     * 建立标签-任务映射关系
     * @param tagNameList 标签名列表
     * @param taskId 任务 id
     */
    public void mapTagsToTask(List<String> tagNameList, Long taskId) {
        baseMapper.mapTagsToTask(tagNameList, taskId);
    }
}
