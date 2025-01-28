package com.iflove.todolist.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 状态是否正常枚举类
 */
@AllArgsConstructor
@Getter
public enum SexEnum {
    MALE(1, "男 "),
    FEMALE(2, "女"),
    ;

    private final Integer status;
    private final String desc;

    private static final Map<Integer, SexEnum> cache;

    static {
        cache = Arrays.stream(SexEnum.values()).collect(Collectors.toMap(SexEnum::getStatus, Function.identity()));
    }

    public static SexEnum of(Integer type) {
        return cache.get(type);
    }

}
