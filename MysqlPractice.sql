/*
 Navicat Premium Data Transfer

 Source Server         : sql_vertification
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 106.54.84.18:3306
 Source Schema         : MysqlPractice

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 21/12/2020 14:42:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_answer
-- ----------------------------
DROP TABLE IF EXISTS `tbl_answer`;
CREATE TABLE `tbl_answer`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `question` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `solution` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_answer
-- ----------------------------
INSERT INTO `tbl_answer` VALUES (1, '查询全体学生的详细记录', 'select * from tbl_student;', '2020-12-14 21:15:41', '2020-12-16 21:11:15');
INSERT INTO `tbl_answer` VALUES (2, '查询全体学生的学号和姓名', 'select Sno, Sname from Student;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (3, '查询全体学生的姓名及其出生年份', 'select Sname,year(now())-Sage from Student;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (4, '查询全体学生的姓名、出生年份和所在的院系，要求用小写字幕表示系名', 'select Sname NAME,year(now())-Sage BIRTHDAY,LOWER(Sdept) DEPARTMENT from Student;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (7, '查询所有不姓刘的学生的姓名、学号和性别', 'select Sname,Sno,Ssex from Student where Sname not like \'刘%\';', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (8, '查询全体学生情况，查询结果按所在系的系号升序排列，同一系中的学生按年龄降序排列', 'select * from Student order by Sdept,Sage desc;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (9, '查询学生总人数', 'select count(*) from Student;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (10, '查询选修了课程的学生人数', 'select count(distinct Sno) from SC;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (11, '计算选修1号课程的学生平均成绩', 'select avg(Grade) from SC where Cno=\'1\';', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (12, '查询选修1号课程的学生最高分数', 'select max(Grade) from SC where Cno=\'1\';', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (13, '查询学生201215121选修课程的总学分数', 'select sum(Ccredit) from SC,Course where Sno=\'201215121\' and SC.Cno=Course.Cno;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (14, '查询平均成绩大于等于80分的学生学号和平均成绩', 'select Sno,avg(Grade) from SC group by Sno having avg(Grade)>=80;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (15, '查询选修1号课程且成绩在90分以上的所有学生的学号和姓名', 'select Student.Sno,Sname from Student,SC where Student.Sno=SC.Sno and SC.Cno=\'1\' and SC.Grade>90;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (16, '查询一门课的间接先修课（即先修课的先修课）', 'select FIRST.Cno,SECOND.Cpno from Course FIRST,Course SECOND where FIRST.Cpno=SECOND.Cno;', '2020-12-14 21:15:41', '2020-12-14 21:15:41');
INSERT INTO `tbl_answer` VALUES (17, '', '', '2020-12-21 13:14:30', '2020-12-21 13:14:30');

-- ----------------------------
-- Table structure for tbl_answer_history
-- ----------------------------
DROP TABLE IF EXISTS `tbl_answer_history`;
CREATE TABLE `tbl_answer_history`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT 'user表的username 来查看某个用户的所有题目',
  `answer_id` bigint(32) NOT NULL COMMENT 'answer表id来查看某道题',
  `user_answers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '存放用户答案',
  `question_status` int(1) NOT NULL COMMENT '0代表未答题、1代表成功答案、2代表已经答题但是答案错误',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_answer_history
-- ----------------------------
INSERT INTO `tbl_answer_history` VALUES (1, 6, 1, 'select * from tbl_student;', 1, '2020-12-14 21:16:53', '2020-12-21 14:28:57');
INSERT INTO `tbl_answer_history` VALUES (2, 6, 2, 'select Sno, Sname from Student;', 1, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (3, 6, 3, 'select * from Student;', 2, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (4, 6, 4, 'select * from Student;', 0, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (24, 1, 1, 'select * from User;', 0, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (25, 2, 1, 'select * from tbl_student;', 1, '2020-12-14 21:16:53', '2020-12-17 20:23:18');
INSERT INTO `tbl_answer_history` VALUES (26, 2, 14, 'zXzxcsaca', 2, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (27, 1, 15, 'sadasad', 2, '2020-12-14 21:16:53', '2020-12-14 21:16:53');
INSERT INTO `tbl_answer_history` VALUES (28, 2, 3, 'fghjkl', 2, '2020-12-14 21:16:53', '2020-12-14 21:16:53');

-- ----------------------------
-- Table structure for tbl_class
-- ----------------------------
DROP TABLE IF EXISTS `tbl_class`;
CREATE TABLE `tbl_class`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class
-- ----------------------------
INSERT INTO `tbl_class` VALUES (1, '17软件工程2班', '2020-12-14 21:17:20', '2020-12-14 21:17:20');
INSERT INTO `tbl_class` VALUES (2, '17软件工程1班', '2020-12-14 21:17:20', '2020-12-14 21:17:20');
INSERT INTO `tbl_class` VALUES (3, '17计算机科学与技术2班', '2020-12-17 09:22:13', '2020-12-17 09:22:13');
INSERT INTO `tbl_class` VALUES (6, '17计算机科学与技术1班', '2020-12-17 09:41:32', '2020-12-17 09:41:32');

-- ----------------------------
-- Table structure for tbl_course
-- ----------------------------
DROP TABLE IF EXISTS `tbl_course`;
CREATE TABLE `tbl_course`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `c_no` int(11) NULL DEFAULT NULL,
  `c_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `c_pno` int(11) NULL DEFAULT NULL,
  `c_credit` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_course
-- ----------------------------
INSERT INTO `tbl_course` VALUES (1, 1, '数据库', 5, 4, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (2, 2, '数学', NULL, 2, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (3, 3, '信息系统', 1, 4, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (4, 4, '操作系统', 6, 3, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (5, 5, '数据结构', 7, 4, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (6, 6, '数据处理', NULL, 2, '2020-12-14 21:17:44', '2020-12-14 21:17:44');
INSERT INTO `tbl_course` VALUES (7, 7, 'PASCAL语言', 6, 4, '2020-12-14 21:17:44', '2020-12-14 21:17:44');

-- ----------------------------
-- Table structure for tbl_j
-- ----------------------------
DROP TABLE IF EXISTS `tbl_j`;
CREATE TABLE `tbl_j`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `j_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `j_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_j
-- ----------------------------
INSERT INTO `tbl_j` VALUES (1, 'J1', '三建', '北京', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (2, 'J2', '一汽', '长春', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (3, 'J3', '弹簧厂', '天津', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (4, 'J4', '造船厂', '天津', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (5, 'J5', '机车厂', '唐山', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (6, 'J6', '无线电厂', '常州', '2020-12-14 21:19:25', '2020-12-14 21:19:25');
INSERT INTO `tbl_j` VALUES (7, 'J7', '半导体厂', '南京', '2020-12-14 21:19:25', '2020-12-14 21:19:25');

-- ----------------------------
-- Table structure for tbl_p
-- ----------------------------
DROP TABLE IF EXISTS `tbl_p`;
CREATE TABLE `tbl_p`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `p_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `p_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `weight` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_p
-- ----------------------------
INSERT INTO `tbl_p` VALUES (1, 'P1', '螺母', '红', '12', '2020-12-14 21:19:47', '2020-12-14 21:19:47');
INSERT INTO `tbl_p` VALUES (2, 'P2', '螺栓', '绿', '17', '2020-12-14 21:19:47', '2020-12-14 21:19:47');
INSERT INTO `tbl_p` VALUES (3, 'P3', '螺丝刀', '蓝', '14', '2020-12-14 21:19:47', '2020-12-14 21:19:47');
INSERT INTO `tbl_p` VALUES (4, 'P4', '螺丝刀', '蓝', '14', '2020-12-14 21:19:47', '2020-12-14 21:19:47');
INSERT INTO `tbl_p` VALUES (5, 'P5', '凸轮', '蓝', '40', '2020-12-14 21:19:47', '2020-12-14 21:19:47');
INSERT INTO `tbl_p` VALUES (6, 'P6', '齿轮', '红', '30', '2020-12-14 21:19:47', '2020-12-14 21:19:47');

-- ----------------------------
-- Table structure for tbl_paper
-- ----------------------------
DROP TABLE IF EXISTS `tbl_paper`;
CREATE TABLE `tbl_paper`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `class_id` bigint(32) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_paper
-- ----------------------------
INSERT INTO `tbl_paper` VALUES (1, '测试试卷', 2, 1, '2020-12-15 20:18:16', '2020-12-15 20:18:16');
INSERT INTO `tbl_paper` VALUES (2, '测试试卷2.1', 2, 1, '2020-12-15 20:58:42', '2020-12-16 10:22:43');
INSERT INTO `tbl_paper` VALUES (5, '测试试卷5', 1, 1, '2020-12-15 22:31:35', '2020-12-15 22:31:35');
INSERT INTO `tbl_paper` VALUES (6, '期中', 3, 1, '2020-12-17 13:14:07', '2020-12-17 13:14:07');
INSERT INTO `tbl_paper` VALUES (10, '期末', 3, 1, '2020-12-17 16:45:24', '2020-12-17 16:45:24');
INSERT INTO `tbl_paper` VALUES (15, 'asdasd', 1, 1, '2020-12-17 22:31:05', '2020-12-17 22:31:05');
INSERT INTO `tbl_paper` VALUES (16, 'qweqwe', 1, 1, '2020-12-17 22:32:42', '2020-12-17 22:32:42');
INSERT INTO `tbl_paper` VALUES (17, 'zxczxczxc', 1, 1, '2020-12-17 22:33:08', '2020-12-17 22:33:08');
INSERT INTO `tbl_paper` VALUES (18, 'fghfghf', 1, 1, '2020-12-17 22:34:03', '2020-12-17 22:34:03');
INSERT INTO `tbl_paper` VALUES (20, 'vbnvbn', 1, 1, '2020-12-17 22:34:32', '2020-12-17 22:34:32');
INSERT INTO `tbl_paper` VALUES (21, 'sadsad', 1, 1, '2020-12-17 22:36:19', '2020-12-17 22:36:19');
INSERT INTO `tbl_paper` VALUES (22, 'adsad', 1, 1, '2020-12-18 16:53:06', '2020-12-18 16:53:06');
INSERT INTO `tbl_paper` VALUES (23, 'awedwqewq', 1, 1, '2020-12-21 13:13:55', '2020-12-21 13:13:55');

-- ----------------------------
-- Table structure for tbl_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `tbl_paper_answer`;
CREATE TABLE `tbl_paper_answer`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(32) NOT NULL,
  `answer_id` bigint(32) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_paper_answer
-- ----------------------------
INSERT INTO `tbl_paper_answer` VALUES (1, 1, 1, '2020-12-15 20:23:09', '2020-12-15 20:23:09');
INSERT INTO `tbl_paper_answer` VALUES (5, 5, 1, '2020-12-15 22:31:36', '2020-12-15 22:31:36');
INSERT INTO `tbl_paper_answer` VALUES (7, 2, 3, '2020-12-16 09:59:32', '2020-12-16 09:59:32');
INSERT INTO `tbl_paper_answer` VALUES (8, 2, 2, '2020-12-16 10:16:50', '2020-12-16 10:16:50');
INSERT INTO `tbl_paper_answer` VALUES (10, 6, 1, '2020-12-17 13:14:07', '2020-12-17 13:14:07');
INSERT INTO `tbl_paper_answer` VALUES (14, 10, 1, '2020-12-17 16:45:24', '2020-12-17 16:45:24');
INSERT INTO `tbl_paper_answer` VALUES (15, 10, 2, '2020-12-17 16:45:24', '2020-12-17 16:45:24');
INSERT INTO `tbl_paper_answer` VALUES (16, 10, 3, '2020-12-17 16:45:24', '2020-12-17 16:45:24');
INSERT INTO `tbl_paper_answer` VALUES (27, 15, 9, '2020-12-17 22:31:05', '2020-12-17 22:31:05');
INSERT INTO `tbl_paper_answer` VALUES (28, 15, 8, '2020-12-17 22:31:06', '2020-12-17 22:31:06');
INSERT INTO `tbl_paper_answer` VALUES (29, 15, 7, '2020-12-17 22:31:06', '2020-12-17 22:31:06');
INSERT INTO `tbl_paper_answer` VALUES (30, 16, 1, '2020-12-17 22:32:42', '2020-12-17 22:32:42');
INSERT INTO `tbl_paper_answer` VALUES (31, 16, 2, '2020-12-17 22:32:42', '2020-12-17 22:32:42');
INSERT INTO `tbl_paper_answer` VALUES (32, 16, 3, '2020-12-17 22:32:42', '2020-12-17 22:32:42');
INSERT INTO `tbl_paper_answer` VALUES (33, 17, 8, '2020-12-17 22:33:08', '2020-12-17 22:33:08');
INSERT INTO `tbl_paper_answer` VALUES (34, 17, 7, '2020-12-17 22:33:08', '2020-12-17 22:33:08');
INSERT INTO `tbl_paper_answer` VALUES (35, 17, 4, '2020-12-17 22:33:08', '2020-12-17 22:33:08');
INSERT INTO `tbl_paper_answer` VALUES (36, 18, 4, '2020-12-17 22:34:03', '2020-12-17 22:34:03');
INSERT INTO `tbl_paper_answer` VALUES (39, 20, 3, '2020-12-17 22:34:32', '2020-12-17 22:34:32');
INSERT INTO `tbl_paper_answer` VALUES (40, 21, 7, '2020-12-17 22:36:19', '2020-12-17 22:36:19');
INSERT INTO `tbl_paper_answer` VALUES (41, 21, 4, '2020-12-17 22:36:19', '2020-12-17 22:36:19');
INSERT INTO `tbl_paper_answer` VALUES (42, 22, 9, '2020-12-18 16:53:06', '2020-12-18 16:53:06');
INSERT INTO `tbl_paper_answer` VALUES (43, 22, 8, '2020-12-18 16:53:07', '2020-12-18 16:53:07');
INSERT INTO `tbl_paper_answer` VALUES (44, 22, 7, '2020-12-18 16:53:07', '2020-12-18 16:53:07');
INSERT INTO `tbl_paper_answer` VALUES (45, 23, 8, '2020-12-21 13:13:55', '2020-12-21 13:13:55');
INSERT INTO `tbl_paper_answer` VALUES (46, 23, 7, '2020-12-21 13:13:55', '2020-12-21 13:13:55');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES (1, 'admin', '', '2020-12-14 21:20:28', '2020-12-15 14:47:59');
INSERT INTO `tbl_role` VALUES (2, 'teacher', '', '2020-12-14 21:20:28', '2020-12-15 14:47:58');
INSERT INTO `tbl_role` VALUES (3, 'student', '', '2020-12-14 21:20:28', '2020-12-15 14:48:00');

-- ----------------------------
-- Table structure for tbl_s
-- ----------------------------
DROP TABLE IF EXISTS `tbl_s`;
CREATE TABLE `tbl_s`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `s_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `s_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_s
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_sc
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sc`;
CREATE TABLE `tbl_sc`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `s_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `c_no` int(11) NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_sc
-- ----------------------------
INSERT INTO `tbl_sc` VALUES (1, '201215121', 1, 92, '2020-12-14 21:22:04', '2020-12-14 21:22:04');
INSERT INTO `tbl_sc` VALUES (2, '201215121', 2, 85, '2020-12-14 21:22:04', '2020-12-14 21:22:04');
INSERT INTO `tbl_sc` VALUES (3, '201215121', 3, 88, '2020-12-14 21:22:04', '2020-12-14 21:22:04');
INSERT INTO `tbl_sc` VALUES (4, '201215122', 2, 90, '2020-12-14 21:22:04', '2020-12-14 21:22:04');
INSERT INTO `tbl_sc` VALUES (5, '201215122', 3, 80, '2020-12-14 21:22:04', '2020-12-14 21:22:04');

-- ----------------------------
-- Table structure for tbl_spj
-- ----------------------------
DROP TABLE IF EXISTS `tbl_spj`;
CREATE TABLE `tbl_spj`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `sno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qty` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_spj
-- ----------------------------
INSERT INTO `tbl_spj` VALUES (1, 'S1', 'P1', 'J1', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (2, 'S1', 'P1', 'J3', '100', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (3, 'S1', 'P1', 'J4', '700', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (4, 'S1', 'P2', 'J2', '100', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (5, 'S2', 'P3', 'J1', '400', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (6, 'S2', 'P3', 'J2', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (7, 'S2', 'P3', 'J4', '500', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (8, 'S2', 'P3', 'J5', '400', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (9, 'S2', 'P5', 'J1', '400', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (10, 'S2', 'P5', 'J2', '100', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (11, 'S3', 'P1', 'J1', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (12, 'S3', 'P3', 'J1', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (13, 'S4', 'P5', 'J1', '100', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (14, 'S4', 'P6', 'J3', '300', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (15, 'S4', 'P6', 'J4', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (16, 'S5', 'P2', 'J4', '100', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (17, 'S5', 'P3', 'J1', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (18, 'S5', 'P6', 'J2', '200', '2020-12-14 21:22:47', '2020-12-14 21:22:47');
INSERT INTO `tbl_spj` VALUES (19, 'S5', 'P6', 'J4', '500', '2020-12-14 21:22:47', '2020-12-14 21:22:47');

-- ----------------------------
-- Table structure for tbl_student
-- ----------------------------
DROP TABLE IF EXISTS `tbl_student`;
CREATE TABLE `tbl_student`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `s_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `s_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `s_sex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `s_age` int(11) NULL DEFAULT NULL,
  `s_dept` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_student
-- ----------------------------
INSERT INTO `tbl_student` VALUES (1, '201215121', '李勇', '男', 20, 'CS', '2020-12-14 21:23:31', '2020-12-14 21:23:31');
INSERT INTO `tbl_student` VALUES (2, '201215122', '刘晨', '女', 19, 'CS', '2020-12-14 21:23:31', '2020-12-14 21:23:31');
INSERT INTO `tbl_student` VALUES (3, '201215123', '王敏', '女', 18, 'MA', '2020-12-14 21:23:31', '2020-12-14 21:23:31');
INSERT INTO `tbl_student` VALUES (4, '201215125', '张立', '男', 19, 'IS', '2020-12-14 21:23:31', '2020-12-14 21:23:31');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '当前用户是否删除 0删除 1存在\r\n',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'admin', 'admin', '$2a$10$k.TlK9jJrL1BJcIiXnvKNekGHMXexYkJG/BquSGg9Rzwa3B2HC5BW', NULL, 1, '2020-12-14 21:24:13', '2020-12-14 21:24:13');
INSERT INTO `tbl_user` VALUES (2, '172101038', '高谢恩', '$2a$10$AI2ZtZqpqV3Fx1xyBwI1Nus4ZjEMyrArEaOazhWUIYBWYNPRAD0dW', NULL, 2, '2020-12-14 21:24:13', '2020-12-16 16:09:59');
INSERT INTO `tbl_user` VALUES (3, '172101034', '张满', '$2a$10$2g9URCkUcZBMXpueECWjL.oDF4VlDnzOaQtmIeGPHsSnL05Dz3kwa', NULL, 1, '2020-12-14 21:24:13', '2020-12-14 21:24:13');
INSERT INTO `tbl_user` VALUES (4, '172101030', '高学博', '$2a$10$l.zEcSqn.Ggkf6b8OTL/gep3Wyd8AmhgH7H399zjO9vHsEwQDWNzq', NULL, 1, '2020-12-14 21:24:13', '2020-12-14 21:24:13');
INSERT INTO `tbl_user` VALUES (5, 'LJ', '连洁', '$2a$10$Kd/IgrETGHV/k2r/DQ3VxOjj00.lcHIPO6NTc0uALfJDgfQE5/Rbu', NULL, 1, '2020-12-14 21:24:13', '2020-12-14 21:24:13');
INSERT INTO `tbl_user` VALUES (6, '172101040', '李雷', '$2a$10$ifmstHqxprbbU9SfGp/hTOf69vTrzBDo/TTFMeABf7hj42qrTJH4C', NULL, 1, '2020-12-14 21:24:13', '2020-12-14 21:24:13');
INSERT INTO `tbl_user` VALUES (7, 'lilei', '李雷', '$2a$10$rVKTWkG61wC0g5M/s4fVdenNKYhaOQ4ty5quhT3UFQ/1X/YjVo0nC', NULL, 1, '2020-12-16 15:42:34', '2020-12-16 16:30:37');
INSERT INTO `tbl_user` VALUES (8, 'zhaoqi', '赵起', '$2a$10$pJtseQ1qHHcK6zqMe6.pUeCvAQ2/elr8NO8ZL6adp3Ula5SRXaIx.', NULL, 1, '2020-12-16 15:55:09', '2020-12-16 15:55:09');

-- ----------------------------
-- Table structure for tbl_user_class
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_class`;
CREATE TABLE `tbl_user_class`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(32) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user_class
-- ----------------------------
INSERT INTO `tbl_user_class` VALUES (1, 1, 1, '2020-12-14 21:25:01', '2020-12-14 21:25:01');
INSERT INTO `tbl_user_class` VALUES (2, 1, 2, '2020-12-14 21:25:01', '2020-12-14 21:25:01');
INSERT INTO `tbl_user_class` VALUES (3, 1, 3, '2020-12-14 21:25:01', '2020-12-14 21:25:01');
INSERT INTO `tbl_user_class` VALUES (4, 2, 4, '2020-12-14 21:25:01', '2020-12-14 21:25:01');
INSERT INTO `tbl_user_class` VALUES (5, 2, 5, '2020-12-14 21:25:01', '2020-12-14 21:25:01');
INSERT INTO `tbl_user_class` VALUES (6, 1, 5, '2020-12-14 21:25:01', '2020-12-14 21:25:01');

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES (7, 1, 1, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (8, 2, 3, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (9, 3, 3, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (10, 4, 3, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (11, 5, 2, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (12, 6, 1, '2020-12-14 21:26:34', '2020-12-14 21:26:34');
INSERT INTO `tbl_user_role` VALUES (13, 7, 1, '2020-12-16 15:50:01', '2020-12-16 15:50:01');
INSERT INTO `tbl_user_role` VALUES (14, 8, 1, '2020-12-16 15:55:09', '2020-12-16 15:55:09');

SET FOREIGN_KEY_CHECKS = 1;
