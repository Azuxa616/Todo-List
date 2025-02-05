package com.iflove.todolist.service;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
* @author cangjingyue
* @description 针对表【category(任务分类表)】的数据库操作Service
* @createDate 2025-02-05 08:57:46
*/
public interface CategoryService {

    /**
     * 创建用户的任务分类(用户独有), 可以一次创建多个
     * @param categoryNameList 分类名列表
     * @param uid 用户 id
     */
    void create(List<String> categoryNameList, Long uid);
}
