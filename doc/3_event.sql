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


DROP TABLE IF EXISTS `rc_event_property`;
CREATE TABLE `rc_event_property` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `prop_key` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '属性key',
  `prop_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '属性名',
  `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
  `data_type` TINYINT NOT NULL DEFAULT '0' COMMENT '数据类型0String',
  `busi_type` TINYINT NOT NULL DEFAULT '0' COMMENT '业务类型0无1phone2mail3bankNo4personalId5passport6ip7gps',
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态:1上线2下线',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_prop_key` (`prop_key`),
  KEY `idx_prop_name` (`prop_name`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件属性表';


DROP TABLE IF EXISTS `rc_event_type_property`;
CREATE TABLE `rc_event_type_property` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `event_type_id` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '事件类型id',
  `event_prop_id` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '事件属性id',
  `encrypt_type` TINYINT NOT NULL DEFAULT '0' COMMENT '加密类型0无1phone2mail3bankNo4personalId5passport',
  `sort_number` INT NOT NULL DEFAULT '0' COMMENT '排序序号',
  `dict_table_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '字典表解码表名',
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态:1上线2下线',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_type_prop` (`event_type_id`, `event_prop_id`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件类型属性表';