package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.Category;
import com.iflove.todolist.domain.entity.Category;
import com.iflove.todolist.mapper.CategoryMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class CategoryDao extends ServiceImpl<CategoryMapper, Category> {

    /**
     * 创建用户的任务分类(用户独有), 可以一次创建多个
     * @param categoryNameList 任务分类列表
     * @param uid 用户 id
     */
    public void create(@NotNull List<String> categoryNameList, Long uid) {
        baseMapper.insert(categoryNameList
                .stream()
                .map(name -> Category.builder().user_id(uid).name(name).build())
                .collect(Collectors.toList())
        );
    }

    /**
     * 根据分类名和用户 id 查询是否存在
     * @param categoryNameList 分类名列表
     * @param uid 用户 id
     * @return 分类列表
     */
    public List<Category> queryByNamesAndId(@NotNull List<String> categoryNameList, Long uid) {
        return lambdaQuery()
                .eq(Category::getUser_id, uid)
                .in(Category::getName, categoryNameList)
                .list();
    }

    /**
     * 删除用户的任务分类(用户独有)，可以一次删除多个
     * @param categoryNameList 分类名列表
     * @param uid 用户 id
     */
    public void delete(List<String> categoryNameList, Long uid) {
        lambdaUpdate()
                .eq(Category::getUser_id, uid)
                .in(Category::getName, categoryNameList)
                .remove();
    }
}
