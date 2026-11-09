-- 会员管理模块数据库初始化脚本
-- 适用于 MySQL 8.0+

-- ==================== 会员表 ====================
CREATE TABLE IF NOT EXISTS `member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `user_id` BIGINT NOT NULL COMMENT '关联的系统用户ID',
  `nickname` VARCHAR(64) NOT NULL COMMENT '会员昵称',
  `mobile` VARCHAR(11) NOT NULL COMMENT '手机号',
  `avatar` VARCHAR(255) DEFAULT '' COMMENT '头像URL',
  `level_id` BIGINT NOT NULL COMMENT '会员等级ID',
  `point` INT NOT NULL DEFAULT 0 COMMENT '积分',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态（0正常 1禁用）',
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `gender` TINYINT DEFAULT NULL COMMENT '性别（1男 2女）',

  -- 标准字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT NOT NULL DEFAULT 0 COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`, `deleted`) COMMENT '用户ID唯一',
  UNIQUE KEY `uk_mobile` (`mobile`, `deleted`) COMMENT '手机号唯一',
  KEY `idx_level_id` (`level_id`) COMMENT '等级索引',
  KEY `idx_create_time` (`create_time`) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员表';

-- ==================== 会员等级表 ====================
CREATE TABLE IF NOT EXISTS `member_level` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '等级ID',
  `name` VARCHAR(32) NOT NULL COMMENT '等级名称',
  `required_point` INT NOT NULL COMMENT '所需积分',
  `discount` DECIMAL(3,2) NOT NULL DEFAULT 1.00 COMMENT '折扣率（0.00-1.00）',
  `icon` VARCHAR(255) DEFAULT '' COMMENT '等级图标URL',

  -- 标准字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT NOT NULL DEFAULT 0 COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`, `deleted`) COMMENT '等级名称唯一',
  KEY `idx_required_point` (`required_point`) COMMENT '积分索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员等级表';

-- ==================== 初始化会员等级数据 ====================
INSERT INTO `member_level` (`id`, `name`, `required_point`, `discount`, `icon`) VALUES
(1, '普通会员', 0, 1.00, ''),
(2, '银卡会员', 1000, 0.95, ''),
(3, '金卡会员', 5000, 0.90, ''),
(4, 'VIP会员', 10000, 0.85, ''),
(5, '钻石会员', 50000, 0.80, '')
ON DUPLICATE KEY UPDATE
  `name` = VALUES(`name`),
  `required_point` = VALUES(`required_point`),
  `discount` = VALUES(`discount`);

-- ==================== 可选表：会员积分历史 ====================
-- 如果需要记录积分变更历史，可以创建此表
-- CREATE TABLE IF NOT EXISTS `member_point_history` (
--   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
--   `member_id` BIGINT NOT NULL COMMENT '会员ID',
--   `points` INT NOT NULL COMMENT '积分变更数量（正数增加，负数减少）',
--   `balance` INT NOT NULL COMMENT '变更后余额',
--   `reason` VARCHAR(255) NOT NULL COMMENT '变更原因',
--   `biz_type` VARCHAR(32) NOT NULL COMMENT '业务类型',
--   `biz_id` VARCHAR(64) DEFAULT '' COMMENT '业务ID',
--
--   -- 标准字段
--   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
--   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
--   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `deleted` BIT NOT NULL DEFAULT 0 COMMENT '是否删除',
--   `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
--
--   PRIMARY KEY (`id`),
--   KEY `idx_member_id` (`member_id`) COMMENT '会员ID索引',
--   KEY `idx_create_time` (`create_time`) COMMENT '创建时间索引'
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员积分历史表';

-- ==================== 可选表：会员标签 ====================
-- 如果需要会员标签功能，可以创建此表
-- CREATE TABLE IF NOT EXISTS `member_tag` (
--   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '标签ID',
--   `name` VARCHAR(32) NOT NULL COMMENT '标签名称',
--   `color` VARCHAR(16) DEFAULT '' COMMENT '标签颜色',
--
--   -- 标准字段
--   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
--   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
--   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `deleted` BIT NOT NULL DEFAULT 0 COMMENT '是否删除',
--   `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
--
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `uk_name` (`name`, `deleted`) COMMENT '标签名称唯一'
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员标签表';
--
-- CREATE TABLE IF NOT EXISTS `member_tag_relation` (
--   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
--   `member_id` BIGINT NOT NULL COMMENT '会员ID',
--   `tag_id` BIGINT NOT NULL COMMENT '标签ID',
--
--   -- 标准字段
--   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
--   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
--   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `deleted` BIT NOT NULL DEFAULT 0 COMMENT '是否删除',
--   `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
--
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `uk_member_tag` (`member_id`, `tag_id`, `deleted`) COMMENT '会员标签唯一',
--   KEY `idx_member_id` (`member_id`) COMMENT '会员ID索引',
--   KEY `idx_tag_id` (`tag_id`) COMMENT '标签ID索引'
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员标签关系表';
