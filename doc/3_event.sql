USE risk_control;
set names utf8mb4;

DROP TABLE IF EXISTS `rc_event_type`;
CREATE TABLE `rc_event_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '事件类型编码',
  `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '事件类型名称',
  `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
  `type` TINYINT NOT NULL DEFAULT '1' COMMENT '事件类型:1同步2异步',
  `module` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '模块',
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '事件状态:1上线2下线',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_code` (`code`),
  KEY `idx_name` (`name`),
  KEY `idx_module` (`module`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件类型表';