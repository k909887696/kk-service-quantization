/*
 Navicat Premium Data Transfer

 Source Server         : test.90.126
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 192.168.90.126:3309
 Source Schema         : quantization

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 13/05/2023 14:56:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for max_pct_chg
-- ----------------------------
DROP TABLE IF EXISTS `max_pct_chg`;
CREATE TABLE `max_pct_chg`  (
  `market` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `max_pct_chg` double NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
