package com.iflove.todolist.service.impl;

import com.iflove.todolist.common.exception.BusinessException;
import com.iflove.todolist.common.exception.CategoryErrorEnum;
import com.iflove.todolist.dao.CategoryDao;
import com.iflove.todolist.domain.entity.Category;
import com.iflove.todolist.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cangjingyue
 * @description 针对表【category(任务分类表)】的数据库操作Service实现
 * @createDate 2025-02-05 08:57:46
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    /**
     * 创建用户的任务分类(用户独有), 可以一次创建多个
     * @param categoryNameList 分类名列表
     * @param uid 用户 id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(List<String> categoryNameList, Long uid) {
        List<Category> exists = categoryDao.queryByNamesAndId(categoryNameList, uid);
        if (!exists.isEmpty()) {
            List<String> duplicateNames = exists.stream().map(Category::getName).toList();
            throw new BusinessException(CategoryErrorEnum.CATEGORY_DUPLICATE.getErrorCode(), duplicateNames + " 上述分类已存在，请勿重复创建");
        }
        categoryDao.create(categoryNameList, uid);

    }
}




