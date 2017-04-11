USE risk_control;
set names utf8mb4;

DROP TABLE IF EXISTS `rc_dict_table`;
CREATE TABLE `rc_dict_table` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tbl_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '字典表名',
  `tbl_name_cn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '字典表中文名',
  `tbl_desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '字典表描述',
  `tbl_type` TINYINT NOT NULL DEFAULT '1' COMMENT '字典表类型:1.通用字典表2.列表字典表',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_tbl_name` (`tbl_name`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

DROP TABLE IF EXISTS `rc_dict_entry`;
CREATE TABLE `rc_dict_entry` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tbl_id` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '字典表id',
  `dict_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '编码',
  `dict_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '名称',
  `dict_desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
  `dict_sort` INT NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `create_time` TIMESTAMP NOT NULL DEFAULT '1970-01-02' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `ext1` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '扩展字段3',
  `ext4` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '扩展字段4',
  `ext5` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '扩展字段5',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_tbl_id_code` (`tbl_id`, `dict_code`),
  KEY `idx_dict_code` (`dict_code`),
  KEY `idx_dict_name` (`dict_name`),
  KEY `idx_update_time` (`update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表条目详情';

DROP TABLE IF EXISTS `rc_dict_log`;
CREATE TABLE `rc_dict_log` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tbl_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '字典表名',
  `operator` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '操作人',
  `operate_type` TINYINT NOT NULL DEFAULT '0' COMMENT '操作类型 1新增2修改3删除',
  `operate_detail` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '操作详情',
  `operate_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_tbl_name` (`tbl_name`),
  KEY `idx_operator` (`operator`),
  KEY `idx_operate_time` (`operate_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表操作日志';
