/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80021 (8.0.21)
 Source Host           : 192.168.43.35:3306
 Source Schema         : online_exam

 Target Server Type    : MySQL
 Target Server Version : 80021 (8.0.21)
 File Encoding         : 65001

 Date: 30/10/2023 16:29:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leaderinfo
-- ----------------------------
DROP TABLE IF EXISTS `leaderinfo`;
CREATE TABLE `leaderinfo`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '领导8位员工编号',
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
  `instno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属机构',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leaderinfo
-- ----------------------------
INSERT INTO `leaderinfo` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', 20, NULL, 0, NULL, 0, NULL, 0, 20, 0, NULL);
INSERT INTO `leaderinfo` VALUES ('11111111', '测试二', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL);
INSERT INTO `leaderinfo` VALUES ('1234567', '测试一', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL);
INSERT INTO `leaderinfo` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', NULL, NULL, 0, 120, 1, NULL, 0, 120, 1, NULL);
INSERT INTO `leaderinfo` VALUES ('92773863', '战峰', 'zhanfeng.sd', NULL, NULL, 0, NULL, 0, 120, 2, 120, 2, NULL);
INSERT INTO `leaderinfo` VALUES ('97238509', '于世杰', 'yushijie.sd', NULL, NULL, 0, NULL, 0, NULL, 0, 0, 0, NULL);

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
-- Records of others_evaluation
-- ----------------------------
INSERT INTO `others_evaluation` VALUES ('曲鹏飞', '05286913', NULL, NULL, '15', '100');
INSERT INTO `others_evaluation` VALUES ('于世杰', '97238509', NULL, NULL, '20', '120');
INSERT INTO `others_evaluation` VALUES ('徐秋云', '56630323', NULL, NULL, '65', NULL);
INSERT INTO `others_evaluation` VALUES ('左泰旭', '83636568', NULL, NULL, '25', '80');
INSERT INTO `others_evaluation` VALUES ('战峰', '92773863', NULL, NULL, NULL, '133.33333333333334');

-- ----------------------------
-- Table structure for question_manage
-- ----------------------------
DROP TABLE IF EXISTS `question_manage`;
CREATE TABLE `question_manage`  (
  `qu_id` int NOT NULL COMMENT '问题序号',
  `qu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题内容',
  `qu_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自测为1，他测为2',
  PRIMARY KEY (`qu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_manage
-- ----------------------------
INSERT INTO `question_manage` VALUES (1, '我会花时间和精力来确定同事们按已经达成一致的原则和标准工作。									\r\n我会花时间和精力来确定同事们按已经达成一致的原则和标准工作。', '1');
INSERT INTO `question_manage` VALUES (2, '我会努力让他人接受对组织运营最为有利的价值体系。', '1');
INSERT INTO `question_manage` VALUES (46, '领导者会花时间和精力来确定下属按已经达成一致的原则和标准工作。', '2');
INSERT INTO `question_manage` VALUES (47, '领导者会努力让他人接受对组织运营最为有利的价值体系。', '2');
INSERT INTO `question_manage` VALUES (66, '领导者尊重他人的尊严。', '2');
INSERT INTO `question_manage` VALUES (67, '领导者会适当放权，支持他人独立做出决定。', '2');
INSERT INTO `question_manage` VALUES (68, '领导者给与员工充分的空间去决定如何进行他们的工作。', '2');
INSERT INTO `question_manage` VALUES (69, '领导者确保员工能通过学习新的技能在工作成长，并得到自身发展。', '2');
INSERT INTO `question_manage` VALUES (70, '领导者会及时表扬对工作完成得好的人员。', '2');
INSERT INTO `question_manage` VALUES (71, '领导者让团队的同事知道他对大家胜任工作的能力充满信心。', '2');
INSERT INTO `question_manage` VALUES (72, '领导者以富有创意的方式对项目中做出突出贡献的员工进行奖励。', '2');
INSERT INTO `question_manage` VALUES (73, '领导者会当众表彰那些为实现共同价值而奋斗的人。', '2');
INSERT INTO `question_manage` VALUES (74, '领导者会用不同的方法为我们取得的成果进行庆祝。', '2');
INSERT INTO `question_manage` VALUES (75, '领导者对员工所做的贡献给许多表扬和支持。', '2');

-- ----------------------------
-- Table structure for score_manage
-- ----------------------------
DROP TABLE IF EXISTS `score_manage`;
CREATE TABLE `score_manage`  (
  `pj_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价人8位员工编号',
  `pj_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价人姓名',
  `pj_uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价人UASS号',
  `pj_instno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价人所属机构号',
  `bpj_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被评价人8位员工编号',
  `bpj_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '被评价人姓名',
  `bpj_uass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被评价人UASS号',
  `bpj_instno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评价人所属机构号',
  `score` double NULL DEFAULT NULL COMMENT '得分',
  `bpj_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评价人职等',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级为0，同级为1，下级为2'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score_manage
-- ----------------------------
INSERT INTO `score_manage` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', '', '05286913', '曲鹏飞', 'qupengfei.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', NULL, '97238509', '于世杰', 'yushijie.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', NULL, '92773863', '战峰', 'zhanfeng.sd', '金融科技部', 120, NULL, '0');
INSERT INTO `score_manage` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', NULL, '56630323', '徐秋云', 'xuqiuyun.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', NULL, '83636568', '左泰旭', 'zuotaixu.sd', '金融科技部', 120, NULL, '1');
INSERT INTO `score_manage` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', NULL, '97238509', '于世杰', 'yushijie.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', NULL, '92773863', '战峰', 'zhanfeng.sd', '金融科技部', 120, NULL, '0');
INSERT INTO `score_manage` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', NULL, '56630323', '徐秋云', 'xuqiuyun.sd', '金融科技部', 120, NULL, '1');
INSERT INTO `score_manage` VALUES ('97238509', '于世杰', 'yushijie.sd', NULL, '05286913', '曲鹏飞', 'qupengfei.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('97238509', '于世杰', 'yushijie.sd', NULL, '83636568', '左泰旭', 'zuotaixu.sd', '金融科技部', NULL, NULL, '1');
INSERT INTO `score_manage` VALUES ('97238509', '于世杰', 'yushijie.sd', NULL, '92773863', '战峰', 'zhanfeng.sd', '金融科技部', NULL, NULL, '0');
INSERT INTO `score_manage` VALUES ('83636568', '左泰旭', 'zuotaixu.sd', NULL, '12312312', '张三', 'zhangsan.sd', '金融科技部', NULL, NULL, '2');
INSERT INTO `score_manage` VALUES ('05286913', '曲鹏飞', 'qupengfei.sd', NULL, '12312312', '张三', 'zhangsan.sd', '金融科技部', NULL, NULL, '2');
INSERT INTO `score_manage` VALUES ('92738509', '于世杰', 'yushijie.sd', NULL, '12312312', '张三', 'zhangsan.sd', '金融科技部', NULL, NULL, '2');
INSERT INTO `score_manage` VALUES ('12312312', '张三', 'zhangsan.sd', NULL, '83636568', '左泰旭', 'zuotaixu.sd', '金融科技部', NULL, NULL, '0');
INSERT INTO `score_manage` VALUES ('12312312', '张三', 'zhangsan.sd', NULL, '05286913', '曲鹏飞', 'qupengfei.sd', '金融科技部', NULL, NULL, '0');
INSERT INTO `score_manage` VALUES ('12312312', '张三', 'zhangsan.sd', NULL, '97238509', '于世杰', 'yushijie.sd', '金融科技部', NULL, NULL, '0');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '8位员工编号',
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `userUass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'UASS编号',
  `userInstNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属机构号',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职等',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('曲鹏飞', '05286913', 'afb4b4dd0aae4e0d78ecc11e7013db206d33afd6ee4f20fbd7dcb386b6fa3051', 'qupengfei.sd', '金融科技部', '2', '10');
INSERT INTO `userinfo` VALUES ('左泰旭', '83636568', 'afb4b4dd0aae4e0d78ecc11e7013db206d33afd6ee4f20fbd7dcb386b6fa3051', 'zuotaixu.sd', '金融科技部', '2', '10');
INSERT INTO `userinfo` VALUES ('于世杰', '97238509', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'yushijie.sd', '金融科技部', '2', '10');
INSERT INTO `userinfo` VALUES ('管理员', 'admin', '85dfeb35f973274443e16425aeb748dabff93cd1bc7ab418544f1ba46cd7b1f7', 'admin', '管理员', '0', '0');

SET FOREIGN_KEY_CHECKS = 1;
