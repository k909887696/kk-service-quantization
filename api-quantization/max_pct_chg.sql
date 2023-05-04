/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : quantization

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-10-17 20:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for max_pct_chg
-- ----------------------------
DROP TABLE IF EXISTS `max_pct_chg`;
CREATE TABLE `max_pct_chg` (
  `market` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_pct_chg` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of max_pct_chg
-- ----------------------------
INSERT INTO `max_pct_chg` VALUES ('CDR', '0.2');
INSERT INTO `max_pct_chg` VALUES ('创业板', '0.2');
INSERT INTO `max_pct_chg` VALUES ('科创板', '0.2');
INSERT INTO `max_pct_chg` VALUES ('中小板', '0.1');
INSERT INTO `max_pct_chg` VALUES ('主板', '0.1');
