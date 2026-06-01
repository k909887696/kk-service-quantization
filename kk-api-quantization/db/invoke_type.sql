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

 Date: 13/05/2023 14:57:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for invoke_type
-- ----------------------------
DROP TABLE IF EXISTS `invoke_type`;
CREATE TABLE `invoke_type`  (
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '調用類型編號(beanId)',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类型名称',
  `invoke_object` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类型对象',
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类型方法',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `invoke_params` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '调用类型参数',
  PRIMARY KEY (`invoke_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invoke_type
-- ----------------------------
INSERT INTO `invoke_type` VALUES ('CleanDataTaskExecutor', '调度任务历史清理', 'CleanDataTaskExecutor', NULL, NULL, '{\"holdDataDay\":\"保留多少天数据 默认3天\"}');
INSERT INTO `invoke_type` VALUES ('CnmTaskExecutor', '国内货币月供应量', 'CnmTaskExecutor', '', '2021-01-10 22:44:26', '{}');
INSERT INTO `invoke_type` VALUES ('ConceptDailydfcfTaskExecutor', '东方财富概念日线', 'ConceptDailydfcfTaskExecutor', NULL, NULL, '{\"start_date\":\"20180101\",\"end_date\":\"20190101\",\"PageSize\":200}');
INSERT INTO `invoke_type` VALUES ('ConceptDetaildfcfTaskExecutor', '东方财富概念股明细', 'ConceptDetaildfcfTaskExecutor', NULL, NULL, NULL);
INSERT INTO `invoke_type` VALUES ('ConceptDetailTaskExecutor', '概念股明细', 'ConceptDetailTaskExecutor', NULL, NULL, '{\"id\":\" 概念分类ID （id来自概念股分类接口）\",\"ts_code\":\" 股票代码 （以上参数二选一）\"}');
INSERT INTO `invoke_type` VALUES ('ConceptDetailthsTaskExecutor', '同花顺概念明细', 'ConceptDetailthsTaskExecutor', NULL, NULL, '{}');
INSERT INTO `invoke_type` VALUES ('ConceptdfcfTaskExecutor', '东方财富概念分类', 'ConceptdfcfTaskExecutor', NULL, NULL, '{\"PageIndex\":1,\"PageSize\":500}');
INSERT INTO `invoke_type` VALUES ('ConceptMoneyFlowdfcfTaskExecutor', '东方财富概念资金流', 'ConceptMoneyFlowdfcfTaskExecutor', '', NULL, '{}');
INSERT INTO `invoke_type` VALUES ('ConceptTaskExecutor', '概念股分类', 'ConceptTaskExecutor', NULL, NULL, '{\"src\":\"来源，默认为ts\"}');
INSERT INTO `invoke_type` VALUES ('ConceptthsTaskExecutor', '同花顺概念', 'ConceptthsTaskExecutor', NULL, NULL, '{\"PageIndex\":1,\"PageSize\":500}');
INSERT INTO `invoke_type` VALUES ('DailyBasicTaskExecutor', '个股每日指标', 'DailyBasicTaskExecutor', NULL, '2022-11-24 17:11:14', '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('DailyTaskExecutor', '日线行情', 'DailyTaskExecutor', NULL, NULL, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('IndexBasicTaskExecutor', '指数基本信息', 'IndexBasicTaskExecutor', '', '2021-01-03 22:59:21', '{}');
INSERT INTO `invoke_type` VALUES ('IndexClassifyTaskExecutor', '申万行业分类', 'IndexClassifyTaskExecutor', NULL, NULL, NULL);
INSERT INTO `invoke_type` VALUES ('IndexDailyTaskExecutor', '指数日线行情', 'IndexDailyTaskExecutor', '', '2021-01-03 22:59:40', '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('IndexMemberTaskExecutor', '申万行业成分', 'IndexMemberTaskExecutor', NULL, NULL, NULL);
INSERT INTO `invoke_type` VALUES ('KdjCrossEmailTaskExecutor', 'KDJ交叉点邮件', 'KdjCrossEmailTaskExecutor', '', '2021-05-30 15:13:30', '{\"reciver_email\":[\"909887696@qq.com\"]}');
INSERT INTO `invoke_type` VALUES ('KdjCrossTaskExecutor', '个股日线Kdj交叉点', 'KdjCrossTaskExecutor', NULL, '2022-11-22 18:06:12', '{\"tradeDate\":\"today\"}');
INSERT INTO `invoke_type` VALUES ('MoneyFlowTaskExecutor', '个股资金流向', 'MoneyFlowTaskExecutor', '', NULL, '{}');
INSERT INTO `invoke_type` VALUES ('MonthlyTaskExecutor', '月线行情', 'MonthlyTaskExecutor', NULL, NULL, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('StockBasicTaskExecutor', '股票列表', 'StockBasicTaskExecutor', NULL, NULL, '{\"is_hs\":\"是否沪深港通标的，N否 H沪股通 S深股通\",\"list_status\":\"上市状态： L上市 D退市 P暂停上市\",\"exchange\":\"交易所 SSE上交所 SZSE深交所 HKEX港交所(未上线)\"}');
INSERT INTO `invoke_type` VALUES ('StockDayKdjTaskExecutor', '个股日线KDJ', 'StockDayKdjTaskExecutor', '', '2021-05-03 12:20:27', '{“kdj_type”:\"9_3_3\",\"start_date\":\"20200101\",\"end_date\":\"20210430\"}');
INSERT INTO `invoke_type` VALUES ('StockfluctuationTaskExecutor', '运算个股异动情况', 'StockfluctuationTaskExecutor', NULL, NULL, '{}');
INSERT INTO `invoke_type` VALUES ('TradeCalTaskExecutor', '交易日历', 'TradeCalTaskExecutor', NULL, '2022-10-18 11:27:31', '{}');
INSERT INTO `invoke_type` VALUES ('WeeklyTaskExecutor', '周线行情', 'WeeklyTaskExecutor', NULL, NULL, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');

SET FOREIGN_KEY_CHECKS = 1;
