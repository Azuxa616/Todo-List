package com.iflove.todolist.common.constant;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
public class RedisKey {
    private static final String BASE_KEY = "todolist:";

    // Token 白名单
    public static final String JWT_WHITE_LIST = "jwt:whitelist:%s";

    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
