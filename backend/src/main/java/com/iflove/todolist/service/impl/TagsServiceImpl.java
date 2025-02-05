package com.iflove.todolist.service.impl;

import com.iflove.todolist.common.exception.BusinessException;
import com.iflove.todolist.common.exception.TagsErrorEnum;
import com.iflove.todolist.dao.TagsDao;
import com.iflove.todolist.domain.entity.Tags;
import com.iflove.todolist.domain.vo.request.tags.CreateTagsReq;
import com.iflove.todolist.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cangjingyue
 * @description 针对表【tags(任务标签表)】的数据库操作Service实现
 * @createDate 2025-02-05 08:57:46
 */
@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {
    private final TagsDao tagsDao;

    /**
     * 创建用户的任务标签(用户独有), 可以一次创建多个
     * @param req 任务标签创建请求
     * @param uid 用户 id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateTagsReq req, Long uid) {
        List<Tags> exists = tagsDao.queryByTagsAndId(req.getTags(), uid);
        if (!exists.isEmpty()) {
            List<String> duplicateNames = exists.stream().map(Tags::getName).toList();
            throw new BusinessException(TagsErrorEnum.TAG_EXIST.getErrorCode(), duplicateNames + " 上述标签已存在，请勿重复创建");
        }
        tagsDao.create(req.getTags(), uid);
    }

}




