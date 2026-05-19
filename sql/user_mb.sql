/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80409 (8.4.9)
 Source Host           : localhost:3306
 Source Schema         : yupi

 Target Server Type    : MySQL
 Target Server Version : 80409 (8.4.9)
 File Encoding         : 65001

 Date: 19/05/2026 13:59:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_mb
-- ----------------------------
DROP TABLE IF EXISTS `user_mb`;
CREATE TABLE `user_mb`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'Mybatis Plus 测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_mb
-- ----------------------------
INSERT INTO `user_mb` VALUES (1, 'Jone', 18, 'test1@baomidou.com');
INSERT INTO `user_mb` VALUES (2, 'Jack', 20, 'test2@baomidou.com');
INSERT INTO `user_mb` VALUES (3, 'Tom', 28, 'test3@baomidou.com');
INSERT INTO `user_mb` VALUES (4, 'Sandy', 21, 'test4@baomidou.com');
INSERT INTO `user_mb` VALUES (5, 'Billie', 24, 'test5@baomidou.com');

SET FOREIGN_KEY_CHECKS = 1;
