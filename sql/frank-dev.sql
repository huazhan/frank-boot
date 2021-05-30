/*
 Navicat Premium Data Transfer

 Source Server         : 个人博客-正式
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : 47.107.184.125:3306
 Source Schema         : frank-prod

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 30/05/2021 14:36:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父部门ID',
  `ids` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '祖级ID列表',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '部门状态：0-禁用，1-正常',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '创建人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '0,1', '三国集团总部', 0, 'frank', '18777712345', NULL, '1', '2021-01-07 23:23:03', 'sys', '2021-04-24 15:40:35', 'admin', NULL);
INSERT INTO `sys_dept` VALUES (21, 1, '0,1,21', '东吴', 1, 'dongwu', '18777754321', NULL, '1', '2021-01-08 09:22:24', 'anonymousUser', '2021-04-24 15:40:59', 'admin', NULL);
INSERT INTO `sys_dept` VALUES (42, 1, '0,1,42', '西蜀', 2, 'xishu', '18681234567', NULL, '1', '2021-01-09 21:07:26', 'anonymousUser', '2021-04-24 15:41:19', 'admin', NULL);
INSERT INTO `sys_dept` VALUES (43, 1, '0,1,43', '北魏', 3, 'beiwei', '18775412451', NULL, '1', '2021-01-10 11:47:03', 'admin', '2021-04-24 15:41:52', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(64) NULL DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型：1-目录，2-菜单，3-按钮',
  `open_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '_iframe' COMMENT '打开方式：_blank-新窗口，_iframe-页签',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态：0-隐藏，1-显示',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '创建人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', '1', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-set-fill', 1, '2020-12-20 13:53:43', 'sys', '2021-01-09 13:36:45', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', 'user-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon ', 0, '2020-12-20 13:55:20', 'sys', '2021-01-09 22:02:04', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'role-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon ', 1, '2020-12-20 13:55:50', 'sys', '2021-01-09 22:02:10', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'menu-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon ', 3, '2020-12-20 13:56:17', 'sys', '2021-01-09 22:01:55', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, '部门管理', 'dept-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon ', 4, '2020-12-20 13:56:44', 'sys', '2021-01-09 22:01:45', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (9, 5, '新增', '', '3', '_iframe', '1', 'sys:dept:insert', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon ', 1, '2021-01-01 20:23:30', 'anonymousUser', '2021-01-09 22:00:49', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (10, 0, '首页', '', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-home', 0, '2021-01-01 21:11:56', 'anonymousUser', '2021-01-09 13:37:35', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (19, 2, '删除', '', '3', '_iframe', '1', 'sys:user:delete', 'layui-icon ', 1, '2021-01-07 18:45:30', 'admin', '2021-01-07 18:45:30', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (20, 2, '更新', '', '3', '_iframe', '1', 'sys:user:update', 'layui-icon ', 1, '2021-01-07 19:57:33', 'test', '2021-01-07 19:57:33', 'test', NULL);
INSERT INTO `sys_menu` VALUES (21, 0, '日志管理', '', '1', '_iframe', '1', '', 'layui-icon layui-icon-note', 1, '2021-01-09 13:29:47', 'admin', '2021-01-09 13:29:47', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (22, 21, '登录日志', 'loginlog-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon ', 1, '2021-01-09 13:30:20', 'admin', '2021-05-23 14:09:03', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (23, 21, '操作日志', 'operlog-list', '2', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon ', 2, '2021-01-09 13:31:14', 'admin', '2021-04-24 15:39:15', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (24, 2, '新增', '', '3', '_iframe', '1', 'sys:user:insert', 'layui-icon layui-icon layui-icon ', 0, '2021-01-09 21:33:25', 'admin', '2021-01-09 22:00:16', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (25, 3, '新增', '', '3', '_iframe', '1', 'sys:role:insert', 'layui-icon layui-icon layui-icon ', 0, '2021-01-09 21:34:01', 'admin', '2021-01-09 22:00:27', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (26, 3, '更新', '', '3', '_iframe', '1', 'sys:role:update', 'layui-icon ', 1, '2021-01-09 21:34:29', 'admin', '2021-01-09 21:34:29', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (27, 3, '删除', '', '3', '_iframe', '1', 'sys:role:delete', 'layui-icon ', 2, '2021-01-09 21:34:59', 'admin', '2021-01-09 21:34:59', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (28, 4, '新增', '', '3', '_iframe', '1', 'sys:menu:insert', 'layui-icon layui-icon layui-icon ', 0, '2021-01-09 21:35:35', 'admin', '2021-01-09 22:00:38', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (29, 4, '更新', '', '3', '_iframe', '1', 'sys:menu:update', 'layui-icon ', 1, '2021-01-09 21:35:55', 'admin', '2021-01-09 21:35:55', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (30, 4, '删除', '', '3', '_iframe', '1', 'sys:menu:delete', 'layui-icon ', 2, '2021-01-09 21:36:16', 'admin', '2021-01-09 21:36:16', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (31, 5, '更新', '', '3', '_iframe', '1', 'sys:dept:update', 'layui-icon ', 1, '2021-01-09 21:36:43', 'admin', '2021-01-09 21:36:43', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (32, 5, '删除', '', '3', '_iframe', '1', 'sys:dept:delete', 'layui-icon ', 2, '2021-01-09 21:37:02', 'admin', '2021-01-09 21:37:02', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (33, 0, '监控管理', '', '1', '_iframe', '1', '', 'layui-icon layui-icon layui-icon layui-icon-chart-screen', 3, '2021-05-30 14:30:56', 'admin', '2021-05-30 06:31:08', 'admin', NULL);
INSERT INTO `sys_menu` VALUES (34, 33, '服务监控', 'monitor-server', '2', '_iframe', '1', '', 'layui-icon ', 1, '2021-05-30 14:31:44', 'admin', '2021-05-30 14:31:44', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色key值',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '1', '2020-12-13 13:54:34', 'anonymousUser', '2021-01-09 21:29:25', 'admin', NULL);
INSERT INTO `sys_role` VALUES (4, '管理员', 'manage', '1', '2020-12-13 14:06:32', 'anonymousUser', '2021-01-09 22:20:49', 'admin', NULL);
INSERT INTO `sys_role` VALUES (14, '普通用户', 'user', '1', '2021-01-10 11:49:25', 'admin', '2021-01-10 16:16:28', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (1, 29);
INSERT INTO `sys_role_menu` VALUES (1, 30);
INSERT INTO `sys_role_menu` VALUES (1, 31);
INSERT INTO `sys_role_menu` VALUES (1, 32);
INSERT INTO `sys_role_menu` VALUES (4, 1);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 3);
INSERT INTO `sys_role_menu` VALUES (4, 4);
INSERT INTO `sys_role_menu` VALUES (4, 5);
INSERT INTO `sys_role_menu` VALUES (4, 9);
INSERT INTO `sys_role_menu` VALUES (4, 10);
INSERT INTO `sys_role_menu` VALUES (4, 19);
INSERT INTO `sys_role_menu` VALUES (4, 20);
INSERT INTO `sys_role_menu` VALUES (4, 21);
INSERT INTO `sys_role_menu` VALUES (4, 22);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (4, 24);
INSERT INTO `sys_role_menu` VALUES (4, 25);
INSERT INTO `sys_role_menu` VALUES (4, 26);
INSERT INTO `sys_role_menu` VALUES (4, 27);
INSERT INTO `sys_role_menu` VALUES (4, 28);
INSERT INTO `sys_role_menu` VALUES (4, 29);
INSERT INTO `sys_role_menu` VALUES (4, 30);
INSERT INTO `sys_role_menu` VALUES (4, 31);
INSERT INTO `sys_role_menu` VALUES (4, 32);
INSERT INTO `sys_role_menu` VALUES (5, 12);
INSERT INTO `sys_role_menu` VALUES (14, 1);
INSERT INTO `sys_role_menu` VALUES (14, 2);
INSERT INTO `sys_role_menu` VALUES (14, 3);
INSERT INTO `sys_role_menu` VALUES (14, 4);
INSERT INTO `sys_role_menu` VALUES (14, 5);
INSERT INTO `sys_role_menu` VALUES (14, 9);
INSERT INTO `sys_role_menu` VALUES (14, 10);
INSERT INTO `sys_role_menu` VALUES (14, 19);
INSERT INTO `sys_role_menu` VALUES (14, 20);
INSERT INTO `sys_role_menu` VALUES (14, 21);
INSERT INTO `sys_role_menu` VALUES (14, 22);
INSERT INTO `sys_role_menu` VALUES (14, 23);
INSERT INTO `sys_role_menu` VALUES (14, 24);
INSERT INTO `sys_role_menu` VALUES (14, 25);
INSERT INTO `sys_role_menu` VALUES (14, 26);
INSERT INTO `sys_role_menu` VALUES (14, 27);
INSERT INTO `sys_role_menu` VALUES (14, 28);
INSERT INTO `sys_role_menu` VALUES (14, 29);
INSERT INTO `sys_role_menu` VALUES (14, 30);
INSERT INTO `sys_role_menu` VALUES (14, 31);
INSERT INTO `sys_role_menu` VALUES (14, 32);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '性别：0-女，1-男',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-正常',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '创建人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'sys' COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'admin', '$2a$10$GRPaS5mfqp1zwXrizfhAueZJhuIjOn.ROSAb3Slp.8/b4ApxhJadm', '超级管理员', '1', '18777777777', NULL, '1', '2020-12-19 10:30:24', 'anonymousUser', '2021-01-09 21:53:59', 'admin', NULL);
INSERT INTO `sys_user` VALUES (35, 43, 'caocao', '$2a$10$GRPaS5mfqp1zwXrizfhAueZJhuIjOn.ROSAb3Slp.8/b4ApxhJadm', '曹操', '1', '18777777777', NULL, '1', '2020-12-19 17:03:39', 'anonymousUser', '2021-01-10 11:47:40', 'admin', NULL);
INSERT INTO `sys_user` VALUES (47, 42, 'liubei', '$2a$10$I8Dp1tA1ZdsSmjpa/qwgH.8FFU1XmcR2nroAJiSBoNRXjModLEU..', '刘备', '1', '18777777777', NULL, '1', '2021-01-09 21:54:35', 'admin', '2021-01-10 11:47:52', 'admin', NULL);
INSERT INTO `sys_user` VALUES (48, 42, 'zhangfei', '$2a$10$qJOhPFgafdLnRStl2lPpje4B2.KJwgCWn1QMgORiDdqpYp0XggC2.', '张飞', '1', '18777777777', NULL, '1', '2021-01-10 10:02:24', 'admin', '2021-01-10 11:48:07', 'admin', NULL);
INSERT INTO `sys_user` VALUES (49, 21, 'sunquan', '$2a$10$1n4mA93AuRBZbvg5O39ONeHdepRNAqgG.S7mLy27ltjh58dHFhfIK', '孙权', '1', '18777777777', NULL, '1', '2021-01-10 11:48:41', 'admin', '2021-01-10 11:48:52', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (35, 4);
INSERT INTO `sys_user_role` VALUES (47, 4);
INSERT INTO `sys_user_role` VALUES (49, 4);

SET FOREIGN_KEY_CHECKS = 1;
