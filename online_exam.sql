/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80021 (8.0.21)
 Source Host           : 172.20.10.3:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 80021 (8.0.21)
 File Encoding         : 65001

 Date: 30/11/2023 11:10:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leaderinfo
-- ----------------------------
DROP TABLE IF EXISTS `leaderinfo`;
CREATE TABLE `leaderinfo`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领导8位员工编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'uass编号',
  `selfevaluation` int NULL DEFAULT NULL COMMENT '自评价得分',
  `superior` int NULL DEFAULT NULL COMMENT '上级评价平均分',
  `superiorNm` int NULL DEFAULT NULL COMMENT '上级评价人数',
  `equal` int NULL DEFAULT NULL COMMENT '同级评价平均分',
  `equalNm` int NULL DEFAULT NULL COMMENT '同级评价人数',
  `subordinate` int NULL DEFAULT NULL COMMENT '下级评价平均分',
  `subordinateNm` int NULL DEFAULT NULL COMMENT '下级评价人数',
  `totalscore` int NULL DEFAULT NULL COMMENT '总得分（上中下级评价平均分总和）',
  `totalNm` int NULL DEFAULT NULL COMMENT '总评价人数（上中下级评价人数总和）',
  `A` int NULL DEFAULT NULL,
  `B` int NULL DEFAULT NULL,
  `C` int NULL DEFAULT NULL,
  `D` int NULL DEFAULT NULL,
  `E` int NULL DEFAULT NULL,
  `markTotalScore` int NULL DEFAULT NULL COMMENT '根据题型得到总分',
  `instName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属机构'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for others_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `others_evaluation`;
CREATE TABLE `others_evaluation`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '8位员工编号',
  `uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'uass编号',
  `superior` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级评价平均分',
  `equal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同级评价平均分',
  `subordinate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下级评价平均分'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_manage
-- ----------------------------
DROP TABLE IF EXISTS `question_manage`;
CREATE TABLE `question_manage`  (
  `qu_id` int NOT NULL COMMENT '问题序号',
  `qu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题内容',
  `qu_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自测为1，他测为2',
  `qu_mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`qu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for score_manage
-- ----------------------------
DROP TABLE IF EXISTS `score_manage`;
CREATE TABLE `score_manage`  (
  `pj_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价人8位员工编号',
  `pj_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价人姓名',
  `pj_uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价人UASS号',
  `pj_instname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价人所属机构',
  `bpj_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评价人8位员工编号',
  `bpj_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被评价人姓名',
  `bpj_uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评价人UASS号',
  `bpj_instname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评价人所属机构',
  `score` double NULL DEFAULT NULL COMMENT '得分',
  `A` double NULL DEFAULT NULL,
  `B` double NULL DEFAULT NULL,
  `C` double NULL DEFAULT NULL,
  `D` double NULL DEFAULT NULL,
  `E` double NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级为0，同级为1，下级为2'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '8位员工编号',
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userUass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UASS编号',
  `userInstName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属机构',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职等'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
