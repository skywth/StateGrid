/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50558
 Source Host           : localhost:3306
 Source Schema         : test_grid

 Target Server Type    : MySQL
 Target Server Version : 50558
 File Encoding         : 65001

 Date: 27/09/2022 22:47:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test1
-- ----------------------------
DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1`  (
  `total` int(255) NULL DEFAULT NULL COMMENT '待选企业总数',
  `selectedSelect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '已选和未选企业数量',
  `selectedNS` int(255) NULL DEFAULT NULL COMMENT '已选未达标企业数量',
  `special` int(255) NULL DEFAULT NULL COMMENT '特殊情况企业数量',
  `estimateSurplus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预估缺口和剩余缺口数量'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test1
-- ----------------------------
INSERT INTO `test1` VALUES (253, '201_51', 1, 1, '1000万_200万');

-- ----------------------------
-- Table structure for test2
-- ----------------------------
DROP TABLE IF EXISTS `test2`;
CREATE TABLE `test2`  (
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日期',
  `day` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '周几',
  `gap` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '缺口额度和认领额度'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test2
-- ----------------------------
INSERT INTO `test2` VALUES ('xxxx-xx-xx', '星期一', '200_150');
INSERT INTO `test2` VALUES ('xxxx-xx-xx', '星期二', '300_200');

-- ----------------------------
-- Table structure for test3
-- ----------------------------
DROP TABLE IF EXISTS `test3`;
CREATE TABLE `test3`  (
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '停电时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业类型',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态',
  `isCompletion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否达到指标',
  `checkedQuota` int(255) NULL DEFAULT NULL COMMENT '实际认领额度',
  `quota` int(255) NULL DEFAULT NULL COMMENT '应当认领额度',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '认领时间',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否可以催认领'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test3
-- ----------------------------
INSERT INTO `test3` VALUES ('xxxx-xx-xx', '万象集团', 'A', 'checked', 'true', 2000, 2000, 'xxxx-xx-xx xx:xx:xx', 'false');
INSERT INTO `test3` VALUES ('xxxx-xx-xx', '一二集团', 'B', 'checked', 'true', 1000, 1000, 'xxxx-xx-xx xx:xx:xx', 'false');

-- ----------------------------
-- Table structure for test4
-- ----------------------------
DROP TABLE IF EXISTS `test4`;
CREATE TABLE `test4`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业类型',
  `baseline` int(255) NULL DEFAULT NULL COMMENT '日基线',
  `feature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '企业性质',
  `standby1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备用字段1',
  `standby2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备用字段2'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test4
-- ----------------------------
INSERT INTO `test4` VALUES ('企业id', '企业名称', '企业类型A/B/高耗能/C/D', 100, 'continuity', '123', '123');
INSERT INTO `test4` VALUES ('企业2', '企业二', 'B', 100, 'continuity', '456', '456');

SET FOREIGN_KEY_CHECKS = 1;
