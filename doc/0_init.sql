USE risk_control;
set names utf8mb4;

CREATE TABLE `rc_user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '昵称',
  `admin` TINYINT NOT NULL DEFAULT '0' COMMENT '是否超级管理员',
  `remarks` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `created_on` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `created_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modified_on` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  `modified_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_username` (`username`),
  KEY `idx_modified_on` (`modified_on`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
