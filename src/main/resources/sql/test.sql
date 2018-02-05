/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 05/02/2018 17:27:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `score_sum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成绩',
  `score_avg` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平均成绩',
  `age` int(11) NULL DEFAULT NULL COMMENT '1男0女',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '小明', '355', '84', 1);
INSERT INTO `student` VALUES (2, '小王', '187', '62.3', 1);
INSERT INTO `student` VALUES (4, '柱子', '230', '76.7', 1);
INSERT INTO `student` VALUES (5, '大毛', '', '', 0);
INSERT INTO `student` VALUES (6, '亮子', '0', '0', 1);
INSERT INTO `student` VALUES (18, '莉莉', '288.5', '88.5', 20);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 'add', 2);
INSERT INTO `t_permission` VALUES (2, 'del', 1);
INSERT INTO `t_permission` VALUES (3, 'update', 2);
INSERT INTO `t_permission` VALUES (4, 'query', 3);
INSERT INTO `t_permission` VALUES (5, 'user:query', 1);
INSERT INTO `t_permission` VALUES (6, 'user:edit', 2);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin');
INSERT INTO `t_role` VALUES (2, 'manager');
INSERT INTO `t_role` VALUES (3, 'normal');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'tom', '123456');
INSERT INTO `t_user` VALUES (2, 'jack', '123456');
INSERT INTO `t_user` VALUES (3, 'rose', '123456');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (1, 3);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (2, 3);
INSERT INTO `t_user_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for up_dict
-- ----------------------------
DROP TABLE IF EXISTS `up_dict`;
CREATE TABLE `up_dict`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父id',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `label` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nidx_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_dict
-- ----------------------------
INSERT INTO `up_dict` VALUES (1, 0, 'A', 'aaa', 'ddd', 10);
INSERT INTO `up_dict` VALUES (2, 0, 'A', 'aaa', 'ddd', 10);

-- ----------------------------
-- Table structure for up_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_menu`;
CREATE TABLE `up_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父id',
  `menu_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单标识',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标窗口',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_menu
-- ----------------------------
INSERT INTO `up_menu` VALUES (1, 0, 'dashboard', 'Dashboard', 'dashboard', 'dashboard', NULL);
INSERT INTO `up_menu` VALUES (2, 1, 'analysis', '分析页', NULL, '/dashboard/analysis', NULL);
INSERT INTO `up_menu` VALUES (3, 1, 'monitor', '监控页', NULL, '/dashboard/monitor', NULL);
INSERT INTO `up_menu` VALUES (4, 1, 'workplace', '工作台', NULL, '/dashboard/workplace', NULL);
INSERT INTO `up_menu` VALUES (5, 0, 'form', '表单页', 'form', 'form', NULL);
INSERT INTO `up_menu` VALUES (6, 5, 'basic-form', '基础表单', NULL, '/form/basic-form', NULL);
INSERT INTO `up_menu` VALUES (7, 5, 'step-form', '分步表单', NULL, '/form/step-form', NULL);
INSERT INTO `up_menu` VALUES (8, 5, 'advanced-form', '高级表单', NULL, '/form/advanced-form', NULL);
INSERT INTO `up_menu` VALUES (9, 0, 'list', '列表页', 'table', 'list', NULL);
INSERT INTO `up_menu` VALUES (10, 9, 'table-list', '查询表格', NULL, '/list/table-list', NULL);
INSERT INTO `up_menu` VALUES (11, 9, 'basic-list', '标准列表', NULL, '/list/basic-list', NULL);
INSERT INTO `up_menu` VALUES (12, 9, 'card-list', '卡片列表', NULL, '/list/card-list', NULL);

-- ----------------------------
-- Table structure for up_resource
-- ----------------------------
DROP TABLE IF EXISTS `up_resource`;
CREATE TABLE `up_resource`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(10) NOT NULL DEFAULT 0 COMMENT '父id',
  `resource_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型(MENU-菜单,URL-url)',
  `resource_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `resource_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源标识,唯一',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_resource_key`(`resource_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_resource
-- ----------------------------
INSERT INTO `up_resource` VALUES (1, 0, 'URL', '字典列表', 'dict:list', '/dict/list');
INSERT INTO `up_resource` VALUES (2, 0, 'URL', '当前用户信息查询', 'user_currentInfo', '/user/currentInfo');
INSERT INTO `up_resource` VALUES (3, 0, 'URL', '用户列表查询', 'user:list', '/user/list');

-- ----------------------------
-- Table structure for up_role
-- ----------------------------
DROP TABLE IF EXISTS `up_role`;
CREATE TABLE `up_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色标识,主键',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_role
-- ----------------------------
INSERT INTO `up_role` VALUES (1, 'admin', '管理员');
INSERT INTO `up_role` VALUES (2, 'user', '普通用户');

-- ----------------------------
-- Table structure for up_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `up_role_resource`;
CREATE TABLE `up_role_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_role_resource`(`role_id`, `resource_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of up_role_resource
-- ----------------------------
INSERT INTO `up_role_resource` VALUES (2, 2, 1);
INSERT INTO `up_role_resource` VALUES (4, 1, 1);
INSERT INTO `up_role_resource` VALUES (5, 1, 2);

-- ----------------------------
-- Table structure for up_user
-- ----------------------------
DROP TABLE IF EXISTS `up_user`;
CREATE TABLE `up_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户标识,主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_user
-- ----------------------------
INSERT INTO `up_user` VALUES (1, 'admin', '$2a$10$P8fKrE.gDOQBQug4dbF9Se1nj7yOSoEHCGh7yuH4S4CnYe2gCFO82', '2017-09-20 14:48:58');

-- ----------------------------
-- Table structure for up_user_role
-- ----------------------------
DROP TABLE IF EXISTS `up_user_role`;
CREATE TABLE `up_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_user_role`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_user_role
-- ----------------------------
INSERT INTO `up_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
