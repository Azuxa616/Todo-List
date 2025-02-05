package com.iflove.todolist.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.todolist.domain.entity.Tags;
import com.iflove.todolist.mapper.TagsMapper;
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
public class TagsDao extends ServiceImpl<TagsMapper, Tags> {

    /**
     * 创建用户的任务标签(用户独有), 可以一次创建多个
     * @param tags 任务标签列表
     * @param uid 用户 id
     */
    public void create(@NotNull List<String> tags, Long uid) {
        baseMapper.insert(tags
                .stream()
                .map(tag -> Tags.builder().user_id(uid).name(tag).build())
                .collect(Collectors.toList())
        );
    }

    /**
     * 根据标签名和用户 id 查询是否存在
     * @param tags 标签名列表
     * @param uid 用户 id
     * @return 标签列表
     */
    public List<Tags> queryByTagsAndId(@NotNull List<String> tags, Long uid) {
        return lambdaQuery()
                .eq(Tags::getUser_id, uid)
                .in(Tags::getName, tags)
                .list();
    }
}
