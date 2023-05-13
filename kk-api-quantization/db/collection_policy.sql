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

 Date: 13/05/2023 14:55:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collection_policy
-- ----------------------------
DROP TABLE IF EXISTS `collection_policy`;
CREATE TABLE `collection_policy`  (
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '策略编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '策略名称',
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调度类型编号',
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调度类型方法',
  `invoke_cycle` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '执行周期（min：每几分钟，hour：每几小时，day:每天，week：每周，month：每月，year：每年）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `pre_run_time` datetime(0) NULL DEFAULT NULL COMMENT '预执行时间',
  `invoke_params` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '调度类型参数',
  `run_count` int(11) NULL DEFAULT NULL COMMENT '执行次数（超过10次自动挂起）',
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '异常信息',
  `invoke_cycle_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '执行周期大小',
  PRIMARY KEY (`policy_id`) USING BTREE,
  INDEX `idx_invoke_code`(`invoke_code`) USING BTREE,
  INDEX `idx_pre_run_time`(`pre_run_time`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_run_count`(`run_count`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection_policy
-- ----------------------------
INSERT INTO `collection_policy` VALUES ('P220000000001', '交易日历-更新', NULL, 'TradeCalTaskExecutor', 'day', '2022-10-18 11:32:36', '2023-05-16 01:30:00', '{}', 0, '', '30');
INSERT INTO `collection_policy` VALUES ('P220000000002', '国内货币月供应量-更新', NULL, 'CnmTaskExecutor', 'day', '2022-10-18 12:31:04', '2023-05-31 01:30:00', '{}', 0, '', '30');
INSERT INTO `collection_policy` VALUES ('P220000000003', '股票列表-每日增量更新', NULL, 'StockBasicTaskExecutor', 'day', '2022-10-18 12:44:53', '2023-05-10 16:00:00', '{}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000004', '股票列表-全量更新', NULL, 'StockBasicTaskExecutor', 'day', '2022-10-18 13:39:08', '2023-05-15 01:30:00', '{\"updateType\":\"cover\"}', 0, '', '7');
INSERT INTO `collection_policy` VALUES ('P220000000005', '日线行情-每日更新', NULL, 'DailyTaskExecutor', 'day', '2022-10-18 18:08:48', '2023-05-10 17:00:00', '{\"pageSize\":10,\"tradeDate\":\"today\"}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000006', '调度任务历史清理', NULL, 'CleanDataTaskExecutor', 'day', '2022-10-22 10:51:36', '2023-05-11 00:00:00', '{}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000007', '概念基础数据更新', NULL, 'ConceptTaskExecutor', 'day', '2022-10-22 10:57:12', '2023-05-10 16:00:00', '{}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000008', '个股资金流向-更新', NULL, 'MoneyFlowTaskExecutor', 'day', '2022-10-22 11:06:47', '2023-05-10 17:00:00', '{\"tradeDate\":\"today\"}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000009', '概念明细-每日更新', NULL, 'ConceptDetailTaskExecutor', 'day', '2022-11-09 11:24:58', '2023-05-16 16:10:00', '{\"pageSize\":50}', 0, '', '7');
INSERT INTO `collection_policy` VALUES ('P220000000010', 'KDJ运算历史数据处理', NULL, 'StockDayKdjTaskExecutor', 'year', '2022-11-22 17:19:29', '2122-11-22 00:00:00', '{\"pageSize\":50,\"startDate\":\"20200101\",\"endDate\":\"20221122\"}', 0, '', '100');
INSERT INTO `collection_policy` VALUES ('P220000000011', '个股Kdj数据-每日运算', NULL, 'StockDayKdjTaskExecutor', 'day', '2022-11-22 17:55:25', '2023-05-10 17:30:00', '{\"pageSize\":1000,\"tradeDate\":\"today\"}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000012', '个股kdj交叉点-每日运算', NULL, 'KdjCrossTaskExecutor', 'day', '2022-11-22 18:07:44', '2023-05-10 17:40:00', '{\"pageSize\":1000,\"tradeDate\":\"today\"}', 0, '', '1');
INSERT INTO `collection_policy` VALUES ('P220000000013', '个股kdj交叉点历史数据运算', '', 'KdjCrossTaskExecutor', 'year', '2022-11-22 18:36:50', '2122-11-23 14:39:11', '{\"pageSize\":50,\"startDate\":\"20220101\",\"endDate\":\"20221123\"}', 0, '', '100');
INSERT INTO `collection_policy` VALUES ('P220000000015', '个股每日指标-历史数据', '', 'DailyBasicTaskExecutor', 'year', '2022-11-24 17:18:11', '2122-11-24 00:00:00', '{\"pageSize\":10,\"startDate\":\"20120101\",\"endDate\":\"20221124\"}', 0, '', '100');
INSERT INTO `collection_policy` VALUES ('P220000000016', '个股每日指标-每日更新', NULL, 'DailyBasicTaskExecutor', 'day', '2022-11-24 17:19:57', '2023-05-10 16:00:00', '{\"pageSize\":10,\"tradeDate\":\"today\"}', 0, '', '1');

SET FOREIGN_KEY_CHECKS = 1;
