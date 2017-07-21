USE risk_control;
set names utf8mb4;

DROP TABLE IF EXISTS `rc_mobile_data`;
CREATE TABLE `rc_mobile_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `prefix` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '前缀7位',
  `province` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '省份',
  `city` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '城市',
  `card_type` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '卡类型',
  `ownership` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '运营商',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_prefix` (`prefix`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='手机号归属地表';


DROP TABLE IF EXISTS `rc_ip_data`;
CREATE TABLE `rc_ip_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `start_ip` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '起始ip',
  `end_ip` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '结束ip',
  `country` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '省份',
  `city` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '城市',
  `card_type` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '卡类型',
  `ownership` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '运营商',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_start_end_ip` (`start_ip`, `end_ip`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ip归属地表';


DROP TABLE IF EXISTS `rc_personal_id_data`;
CREATE TABLE `rc_personal_id_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `prefix` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '前缀6位',
  `province` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '省份',
  `city` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '城市',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_prefix` (`prefix`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='身份证归属地表';


DROP TABLE IF EXISTS `rc_airport_code`;
CREATE TABLE `rc_airport_code` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `airport_code` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '三字码',
  `country` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '国家',
  `province` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '省份',
  `city` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '城市',
  `country_code` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '国家码',
  `province_code` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '省份码',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_airport_code` (`airport_code`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机场三字码';


DROP TABLE IF EXISTS `rc_train_station`;
CREATE TABLE `rc_train_station` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `station` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '车站',
  `country` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '省份',
  `city` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '城市',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_station` (`station`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='火车票站点城市表';