package com.iflove.todolist.common.exception;

import lombok.AllArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 服务错误枚举
 */
@AllArgsConstructor
public enum CommonErrorEnum implements ErrorEnum {
    SYSTEM_ERROR(501, "服务器出错，请联系管理员"),
    PARAM_VALID(502, "参数校验失败"),
    LOCK_LIMIT(503, "请求频繁，请稍后再试"),
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
