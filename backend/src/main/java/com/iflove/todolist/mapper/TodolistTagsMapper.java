package com.iflove.todolist.mapper;

import com.iflove.todolist.domain.entity.TodolistTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author cangjingyue
* @description 针对表【todolist_tags(任务与标签的关联表)】的数据库操作Mapper
* @createDate 2025-02-05 08:57:46
* @Entity com.iflove.todolist.domain.entity.TodolistTags
*/
public interface TodolistTagsMapper extends BaseMapper<TodolistTags> {

    void mapTagsToTask(@Param("tagNameList") List<String> tagNameList,
                       @Param("taskId") Long taskId);
}




