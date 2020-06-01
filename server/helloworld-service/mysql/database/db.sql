CREATE DATABASE IF NOT EXISTS logindemo;
USE logindemo;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    salt varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS `token`;
CREATE TABLE token (
    id int NOT NULL AUTO_INCREMENT,
    status int NOT NULL DEFAULT 1,
    userName varchar(255) NOT NULL,
    token varchar(255) NOT NULL,
    device varchar(255) NOT NULL,
    PRIMARY KEY(id)
);
