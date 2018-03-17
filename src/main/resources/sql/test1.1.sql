/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 17/03/2018 15:39:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for up_authority
-- ----------------------------
DROP TABLE IF EXISTS `up_authority`;
CREATE TABLE `up_authority`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `authority_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限码',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_authority_code`(`authority_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_authority
-- ----------------------------
INSERT INTO `up_authority` VALUES (1, 'login_user_read', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_authority` VALUES (2, 'user_list_read', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_dict
-- ----------------------------
DROP TABLE IF EXISTS `up_dict`;
CREATE TABLE `up_dict`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `parent_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `label` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nk_type`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_dict
-- ----------------------------
INSERT INTO `up_dict` VALUES (1, 0, 'A', 'aaa', 'ddd', 10, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_dict` VALUES (2, 0, 'A', 'aaa', 'ddd', 10, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_menu`;
CREATE TABLE `up_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `parent_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `menu_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单标识',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标窗口',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_menu
-- ----------------------------
INSERT INTO `up_menu` VALUES (1, 0, 'dashboard', 'Dashboard', 'dashboard', 'dashboard', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (2, 1, 'analysis', '分析页', NULL, '/dashboard/analysis', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (3, 1, 'monitor', '监控页', NULL, '/dashboard/monitor', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (4, 1, 'workplace', '工作台', NULL, '/dashboard/workplace', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (5, 0, 'form', '表单页', 'form', 'form', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (6, 5, 'basic-form', '基础表单', NULL, '/form/basic-form', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (7, 5, 'step-form', '分步表单', NULL, '/form/step-form', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (8, 5, 'advanced-form', '高级表单', NULL, '/form/advanced-form', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (9, 0, 'list', '列表页', 'table', 'list', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (10, 9, 'table-list', '查询表格', NULL, '/list/table-list', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (11, 9, 'basic-list', '标准列表', NULL, '/list/basic-list', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_menu` VALUES (12, 9, 'card-list', '卡片列表', NULL, '/list/card-list', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_role
-- ----------------------------
DROP TABLE IF EXISTS `up_role`;
CREATE TABLE `up_role`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '角色标识,主键',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_role
-- ----------------------------
INSERT INTO `up_role` VALUES (1, 'admin', '管理员', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_role` VALUES (2, 'user', '普通用户', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `up_role_authority`;
CREATE TABLE `up_role_authority`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_id_authority_id`(`role_id`, `authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_role_authority
-- ----------------------------
INSERT INTO `up_role_authority` VALUES (1, 1, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_role_authority` VALUES (2, 2, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_role_authority` VALUES (3, 1, 2, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `up_role_authority` VALUES (4, 2, 2, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_user
-- ----------------------------
DROP TABLE IF EXISTS `up_user`;
CREATE TABLE `up_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '用户标识,主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_user
-- ----------------------------
INSERT INTO `up_user` VALUES (1, 'admin', '$2a$10$P8fKrE.gDOQBQug4dbF9Se1nj7yOSoEHCGh7yuH4S4CnYe2gCFO82', '2017-09-20 14:48:58', '0000-00-00 00:00:00');
INSERT INTO `up_user` VALUES (2, 'aaa', 'aaa', '2018-03-16 15:45:46', '0000-00-00 00:00:00');
INSERT INTO `up_user` VALUES (3, 'aaaa', 'aaa', '2018-03-16 15:47:06', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for up_user_role
-- ----------------------------
DROP TABLE IF EXISTS `up_user_role`;
CREATE TABLE `up_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of up_user_role
-- ----------------------------
INSERT INTO `up_user_role` VALUES (1, 1, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
