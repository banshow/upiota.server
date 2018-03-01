
-- ----------------------------
-- Table structure for up_authority
-- ----------------------------
DROP TABLE IF EXISTS `up_authority`;
CREATE TABLE `up_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `authority_code`(`authority_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_authority
-- ----------------------------
INSERT INTO `up_authority` VALUES (1, 'login_user_read');
INSERT INTO `up_authority` VALUES (2, 'user_list_read');

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色标识,主键',
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
-- Table structure for up_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `up_role_authority`;
CREATE TABLE `up_role_authority`  (
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`authority_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `idx_role_id_authority_id`(`role_id`, `authority_id`) USING BTREE,
  CONSTRAINT `up_role_authority_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `up_authority` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `up_role_authority_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `up_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of up_role_authority
-- ----------------------------
INSERT INTO `up_role_authority` VALUES (1, 1);
INSERT INTO `up_role_authority` VALUES (2, 1);
INSERT INTO `up_role_authority` VALUES (1, 2);
INSERT INTO `up_role_authority` VALUES (2, 2);

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
  `create_at` datetime NOT NULL COMMENT '创建时间',
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
