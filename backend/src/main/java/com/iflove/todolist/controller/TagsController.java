package com.iflove.todolist.controller;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.common.utils.RequestHolder;
import com.iflove.todolist.domain.vo.request.tags.TagsNameReq;
import com.iflove.todolist.service.TagsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@RestController
@RequestMapping("/api/tags")
@Validated
@Tag(name = "标签模块")
@RequiredArgsConstructor
public class TagsController {
    private final TagsService tagsService;

    // TODO 为任务添加标签，为任务删除标签

    /**
     * 创建用户的任务标签(用户独有), 可以一次创建多个
     * @param req 任务标签创建请求
     * @return {@link RestBean}
     */
    @PostMapping("create")
    @Operation(summary = "创建标签",
            description = "创建用户的任务标签(用户独有), 可以一次创建多个",
            security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
    })
    public RestBean<Void> create(@RequestBody @Valid TagsNameReq req) {
        tagsService.create(req.getTagNameList(), RequestHolder.get().getUid());
        return RestBean.success();
    }

    /**
     * 删除用户的任务标签(用户独有)，可以一次删除多个
     * @param req 任务标签删除请求
     * @return {@link RestBean}
     */
    @DeleteMapping("delete")
    @Operation(summary = "删除标签",
            description = "删除用户的任务标签(用户独有)，可以一次删除多个",
            security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
    })
    public RestBean<Void> delete(@RequestBody @Valid TagsNameReq req) {
        // FIXME 暂时不严谨，最终效果应该是查询是否有任务使用这个标签，如果都没有才能删除
        tagsService.delete(req.getTagNameList(), RequestHolder.get().getUid());
        return RestBean.success();
    }
}
