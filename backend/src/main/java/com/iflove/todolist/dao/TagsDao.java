package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.Tags;
import com.iflove.todolist.mapper.TagsMapper;
import org.springframework.stereotype.Service;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@Service
public class TagsDao extends ServiceImpl<TagsMapper, Tags> {
}
