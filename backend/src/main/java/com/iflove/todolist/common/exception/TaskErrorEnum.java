package com.iflove.todolist.common.exception;

import lombok.AllArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@AllArgsConstructor
public enum TaskErrorEnum implements ErrorEnum {
    TASK_NOT_EXIST(40001, "任务不存在"),

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
