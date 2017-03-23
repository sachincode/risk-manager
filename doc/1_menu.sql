USE risk_control;
set names utf8mb4;

CREATE TABLE `rc_menu` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '菜单名',
  `url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'url',
  `sort_number` INT NOT NULL DEFAULT '0' COMMENT '排序编号',
  `parent_menu_id` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `remarks` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `created_on` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `created_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modified_on` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
  `modified_by` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `idx_menu_name` (`menu_name`),
  KEY `idx_modified_on` (`modified_on`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';


# 测试数据
INSERT INTO rc_menu(id, menu_name, sort_number, parent_menu_id)
VALUES (1, 'a', 1, 0), (2, 'b', 2, 0), (3, 'c', 3, 0), (4, 'aa1', 1, 1), (5, 'aa2', 2, 1),
  (6, 'cc1', 3, 3), (7, 'cc2', 1, 3), (8, 'bb1', 2, 2), (9, 'd', 2, 0);
