package com.iflove.todolist.controller;

import com.iflove.todolist.common.domain.vo.response.RestBean;
import com.iflove.todolist.common.utils.RequestHolder;
import com.iflove.todolist.domain.vo.request.category.CategoryNameReq;
import com.iflove.todolist.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@RestController
@RequestMapping("/api/category")
@Validated
@Tag(name = "分类模块")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // TODO 为任务添加分类，删除分类，为任务删除分类

    /**
     * 创建用户的任务分类(用户独有), 可以一次创建多个
     * @param req 分类名请求
     * @return {@link RestBean}
     */
    @PostMapping("create")
    @Operation(summary = "创建分类",
            description = "创建用户的任务分类(用户独有), 可以一次创建多个",
            security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
    })
    public RestBean<Void> create(@RequestBody @Valid CategoryNameReq req) {
        categoryService.create(req.getCategoryNameList(), RequestHolder.get().getUid());
        return RestBean.success();
    }
}
