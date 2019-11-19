CREATE DATABASE mybatis_test;

USE mybatis_test;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户编号',
    username VARCHAR(50) COMMENT '登录账号',
    password VARCHAR(50) COMMENT '登录密码',
    nickname VARCHAR(20) COMMENT '用户昵称'
);

INSERT INTO users VALUES(1, 'admin', 'admin', '管理员');
INSERT INTO users VALUES(2, 'manager', 'manager', '管理员');
INSERT INTO users VALUES(3, 'administrator', 'administrator', '管理员');