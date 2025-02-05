package com.iflove.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@AllArgsConstructor
public enum CategoryErrorEnum implements ErrorEnum {
    CATEGORY_DUPLICATE(30001, "分类名已存在"),
    ;
    private final Integer code;
    private final String msg;

    @Override
    public Integer getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }
}
