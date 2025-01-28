drop database todolist;
create database todolist;
use todolist;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id` BIGINT  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    `name` VARCHAR(100) NOT NULL UNIQUE COMMENT '用户昵称',
    `password` varchar(100) NOT NULL comment '用户密码',
    `avatar` VARCHAR(255) NULL COMMENT '用户头像',
    `email` VARCHAR(100) NOT NULL UNIQUE COMMENT '用户邮箱',
    `phone` VARCHAR(100) NOT NULL UNIQUE COMMENT '用户手机号',
    `signature` VARCHAR(255) NULL COMMENT '用户个性签名',
    `sex` INT NOT NULL COMMENT '性别 1为男性，2为女性',
    `create_time` DATETIME NOT NULL DEFAULT Now() COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT Now() on update NOW() COMMENT '修改时间'
)  ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;
ALTER TABLE
    `user` ADD INDEX `user_name_index`(`name`);
ALTER TABLE
    `user` ADD INDEX `user_email_index`(`email`);
ALTER TABLE
    `user` ADD INDEX `user_phone_index`(`phone`);
ALTER TABLE
    `user` ADD INDEX `user_create_time_index`(`create_time`);
ALTER TABLE
    `user` ADD INDEX `user_update_time_index`(`update_time`);
