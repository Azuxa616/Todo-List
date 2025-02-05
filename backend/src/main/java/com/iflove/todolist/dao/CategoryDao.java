package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.Category;
import com.iflove.todolist.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class CategoryDao extends ServiceImpl<CategoryMapper, Category> {
}
