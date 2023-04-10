CREATE DATABASE `mian_shi`CHARACTER SET utf8mb4;

CREATE TABLE `mian_shi`.`class` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                         `cls_name` varchar(20) NOT NULL COMMENT '班级名称',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `cls_name` (`cls_name`)
);
CREATE TABLE `mian_shi`.`student` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `stu_no` int(11) NOT NULL COMMENT '学号',
    `stu_name` varchar(20) NOT NULL COMMENT '姓名',
    `stu_gender` enum('1','0') NOT NULL DEFAULT '0' COMMENT '性别',
    `stu_age` int(11) DEFAULT NULL COMMENT '年龄',
    `stu_class_id` int(11) DEFAULT NULL COMMENT '班级',
    `stu_native_place` varchar(100) DEFAULT NULL COMMENT '籍贯',
    PRIMARY KEY (`id`),
    UNIQUE KEY `stu_no` (`stu_no`)
);
