drop database todolist;
create database todolist;
use todolist;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    `name`        VARCHAR(100) NOT NULL UNIQUE COMMENT '用户昵称',
    `password`    varchar(100) NOT NULL comment '用户密码',
    `avatar`      VARCHAR(255) NULL COMMENT '用户头像',
    `email`       VARCHAR(100) NOT NULL UNIQUE COMMENT '用户邮箱',
    `phone`       VARCHAR(100) NOT NULL UNIQUE COMMENT '用户手机号',
    `signature`   VARCHAR(255) NULL COMMENT '用户个性签名',
    `sex`         INT          NOT NULL COMMENT '性别 1为男性，2为女性',
    `create_time` DATETIME     NOT NULL DEFAULT Now() COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;
ALTER TABLE
    `user`
    ADD INDEX `user_name_index` (`name`);
ALTER TABLE
    `user`
    ADD INDEX `user_email_index` (`email`);
ALTER TABLE
    `user`
    ADD INDEX `user_phone_index` (`phone`);
ALTER TABLE
    `user`
    ADD INDEX `user_create_time_index` (`create_time`);
ALTER TABLE
    `user`
    ADD INDEX `user_update_time_index` (`update_time`);

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '任务id',
    `user_id`     BIGINT       NOT NULL COMMENT '用户id，关联用户表',
    `title`       VARCHAR(255) NOT NULL COMMENT '任务标题',
    `description` TEXT         NULL COMMENT '任务描述',
    `status`      INT          NOT NULL DEFAULT 0 COMMENT '任务状态 0: 未完成, 1: 已完成',
    `star`        INT          NOT NULL DEFAULT 0 COMMENT '任务星标 0: 无星标, 1: 有星标',
    `due_date`    DATETIME     NULL COMMENT '任务截止日期',
    `create_time` DATETIME     NOT NULL DEFAULT NOW() COMMENT '任务创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '任务修改时间',
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Todolist 任务表'
  ROW_FORMAT = Dynamic;

ALTER TABLE `task`
    ADD INDEX `task_user_id_index` (`user_id`);
ALTER TABLE `task`
    ADD INDEX `task_status_index` (`status`);
ALTER TABLE `task`
    ADD INDEX `task_star_index` (`star`);
ALTER TABLE `task`
    ADD INDEX `task_due_date_index` (`due_date`);
ALTER TABLE `task`
    ADD INDEX `task_create_time_index` (`create_time`);
ALTER TABLE `task`
    ADD INDEX `task_update_time_index` (`update_time`);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID',
    `user_id`     BIGINT       NOT NULL COMMENT '用户ID，表示分类属于哪个用户',
    `name`        VARCHAR(100) NOT NULL UNIQUE COMMENT '分类名称',
    `create_time` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '修改时间',
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '任务分类表'
  ROW_FORMAT = Dynamic;

ALTER TABLE `category`
    ADD INDEX `category_user_id_index` (`user_id`);
ALTER TABLE `category`
    ADD INDEX `category_name_index` (`name`);


DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '标签ID',
    `user_id`     BIGINT       NOT NULL COMMENT '用户ID，表示标签属于哪个用户',
    `name`        VARCHAR(100) NOT NULL COMMENT '标签名称',
    `create_time` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '修改时间',
    UNIQUE (`user_id`, `name`), -- 保证同一用户不能创建重复标签
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '任务标签表'
  ROW_FORMAT = Dynamic;

-- 索引优化
ALTER TABLE `tags`
    ADD INDEX `tags_user_id_index` (`user_id`);
ALTER TABLE `tags`
    ADD INDEX `tags_name_index` (`name`);

DROP TABLE IF EXISTS `todolist_tags`;
CREATE TABLE `todolist_tags`
(
    `task_id` BIGINT NOT NULL COMMENT '任务ID',
    `tag_id`  BIGINT NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`task_id`, `tag_id`),
    FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '任务与标签的关联表'
  ROW_FORMAT = Dynamic;

ALTER TABLE `task`
    ADD COLUMN `category_id` BIGINT NULL COMMENT '任务所属分类ID';
ALTER TABLE `task`
    ADD CONSTRAINT `fk_task_category`
        FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL;
ALTER TABLE `task`
    ADD INDEX `task_category_id_index` (`category_id`);