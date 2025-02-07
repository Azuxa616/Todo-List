package com.iflove.todolist.service.impl;

import com.iflove.todolist.common.exception.BusinessException;
import com.iflove.todolist.common.exception.TaskErrorEnum;
import com.iflove.todolist.dao.CategoryDao;
import com.iflove.todolist.dao.TagsDao;
import com.iflove.todolist.dao.TaskDao;
import com.iflove.todolist.dao.TodolistTagsDao;
import com.iflove.todolist.domain.dto.TaskInfoDto;
import com.iflove.todolist.domain.entity.Category;
import com.iflove.todolist.domain.entity.Tags;
import com.iflove.todolist.domain.entity.Task;
import com.iflove.todolist.domain.vo.request.task.CreateTaskReq;
import com.iflove.todolist.domain.vo.request.task.ModifyTaskReq;
import com.iflove.todolist.service.TaskService;
import com.iflove.todolist.service.adapter.TaskAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cangjingyue
 * @description 针对表【task(Todolist 任务表)】的数据库操作Service实现
 * @createDate 2025-02-05 08:57:46
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    private final CategoryDao categoryDao;
    private final TagsDao tagsDao;
    private final TodolistTagsDao todolistTagsDao;

    /**
     * 创建任务
     * @param req 创建任务请求
     * @param uid 用户 id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateTaskReq req, Long uid) {
        // 如果分类名不为 null，判断分类是否存在，不存在则创建
        String categoryName = req.getCategoryName();
        createCategoryIfAbsent(uid, categoryName);
        // 如果标签名列表不为 null，判断标签是否存在，不存在则创建
        List<String> tagNameList = req.getTagNameList();
        createTagsIfAbsent(uid, tagNameList);
        // 创建任务
        TaskInfoDto dto = TaskAdapter.buildTaskInfoDto(req, uid);
        taskDao.create(dto);
        // 如果存在标签，创建任务-标签映射关系
        if (Objects.nonNull(tagNameList) && !tagNameList.isEmpty()) {
            todolistTagsDao.mapTagsToTask(tagNameList, dto.getId());
        }
    }

    /**
     * 如果标签名列表不为 null，判断标签是否存在，不存在则创建
     * @param uid 用户 id
     * @param tagNameList 标签名列表
     */
    private void createTagsIfAbsent(Long uid, List<String> tagNameList) {
        if (Objects.nonNull(tagNameList) && !tagNameList.isEmpty()) {
            Set<String> tags = tagsDao.queryByNamesAndId(tagNameList, uid).stream().map(Tags::getName).collect(Collectors.toSet());
            // 过滤已存在的标签
            List<String> filteredNameList = tagNameList.stream().filter(name -> !tags.contains(name)).toList();
            // 有标签不存在
            if (!filteredNameList.isEmpty()) {
                tagsDao.create(filteredNameList, uid);
            }
        }
    }

    /**
     * 如果分类名不为 null，判断分类是否存在，不存在则创建
     * @param uid 用户 id
     * @param categoryName 分类名
     */
    private void createCategoryIfAbsent(Long uid, String categoryName) {
        if (Objects.nonNull(categoryName)) {
            List<Category> categories = categoryDao.queryByNamesAndId(Collections.singletonList(categoryName), uid);
            // 分类不存在
            if (categories.isEmpty()) {
               categoryDao.create(Collections.singletonList(categoryName), uid);
            }
        }
    }

    /**
     * 删除任务
     * @param id 任务 id
     * @param uid 用户 id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id, Long uid) {
        checkTaskExists(id, uid);
        taskDao.delete(id, uid);
    }

    /**
     * 判断任务是否存在，如果不存在直接报错
     * @param id 任务 id
     * @param uid 用户 id
     */
    private void checkTaskExists(Long id, Long uid) {
        Task task = taskDao.queryByIdAndUid(id, uid);
        // 任务不存在
        if (Objects.isNull(task)) {
            throw new BusinessException(TaskErrorEnum.TASK_NOT_EXIST);
        }
    }

    /**
     * 修改任务
     * @param req 修改任务请求
     * @param uid 用户 id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ModifyTaskReq req, Long uid) {
        // 判断任务是否存在
        checkTaskExists(req.getId(), uid);
        // 如果分类名不为 null，判断分类是否存在，不存在则创建
        String categoryName = req.getCategoryName();
        createCategoryIfAbsent(uid, categoryName);
        // 如果标签名列表不为 null，判断标签是否存在，不存在则创建
        List<String> tagNameList = req.getTagNameList();
        createTagsIfAbsent(uid, tagNameList);
        // 修改任务
        taskDao.modify(TaskAdapter.buildTaskInfoDto(req, uid));
        // 标签的修改涉及到 todolist_tags 删除和添加
        // 如果需要标签修改，则先删除后添加
        if (Objects.nonNull(tagNameList) && !tagNameList.isEmpty()) {
            todolistTagsDao.deleteRelations(req.getId());
            todolistTagsDao.mapTagsToTask(tagNameList, req.getId());
        }
    }
}




