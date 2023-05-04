/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : quantization

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-10-17 20:27:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for invoke_type
-- ----------------------------
DROP TABLE IF EXISTS `invoke_type`;
CREATE TABLE `invoke_type` (
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_object` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `invoke_params` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  PRIMARY KEY (`invoke_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of invoke_type
-- ----------------------------
INSERT INTO `invoke_type` VALUES ('CnmTaskService', '国内货币月供应量', 'CnmTaskService', '', '2021-01-10 22:44:26', '{}');
INSERT INTO `invoke_type` VALUES ('ConceptDailydfcfTaskService', '东方财富概念日线', 'ConceptDailydfcfTaskService', null, null, '{\"start_date\":\"20180101\",\"end_date\":\"20190101\",\"PageSize\":200}');
INSERT INTO `invoke_type` VALUES ('ConceptDetaildfcfTaskService', '东方财富概念股明细', 'ConceptDetaildfcfTaskService', null, null, null);
INSERT INTO `invoke_type` VALUES ('ConceptDetailTaskService', '概念股明细', 'ConceptDetailTaskService', null, null, '{\"id\":\" 概念分类ID （id来自概念股分类接口）\",\"ts_code\":\" 股票代码 （以上参数二选一）\"}');
INSERT INTO `invoke_type` VALUES ('ConceptDetailthsTaskService', '同花顺概念明细', 'ConceptDetailthsTaskService', null, null, '{}');
INSERT INTO `invoke_type` VALUES ('ConceptdfcfTaskService', '东方财富概念分类', 'ConceptdfcfTaskService', null, null, '{\"PageIndex\":1,\"PageSize\":500}');
INSERT INTO `invoke_type` VALUES ('ConceptMoneyFlowdfcfTaskService', '东方财富概念资金流', 'ConceptMoneyFlowdfcfTaskService', '', null, '{}');
INSERT INTO `invoke_type` VALUES ('ConceptTaskService', '概念股分类', 'ConceptTaskService', null, null, '{\"src\":\"来源，默认为ts\"}');
INSERT INTO `invoke_type` VALUES ('ConceptthsTaskService', '同花顺概念', 'ConceptthsTaskService', null, null, '{\"PageIndex\":1,\"PageSize\":500}');
INSERT INTO `invoke_type` VALUES ('DailyTaskService', '日线行情', 'DailyTaskService', null, null, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('IndexBasicTaskService', '指数基本信息', 'IndexBasicTaskService', '', '2021-01-03 22:59:21', '{}');
INSERT INTO `invoke_type` VALUES ('IndexClassifyTaskService', '申万行业分类', 'IndexClassifyTaskService', null, null, null);
INSERT INTO `invoke_type` VALUES ('IndexDailyTaskService', '指数日线行情', 'IndexDailyTaskService', '', '2021-01-03 22:59:40', '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('IndexMemberTaskService', '申万行业成分', 'IndexMemberTaskService', null, null, null);
INSERT INTO `invoke_type` VALUES ('KDJCrossEmailTaskService', 'KDJ交叉点邮件', 'KDJCrossEmailTaskService', '', '2021-05-30 15:13:30', '{\"reciver_email\":[\"909887696@qq.com\"]}');
INSERT INTO `invoke_type` VALUES ('MoneyFlowTaskService', '个股资金流向', 'MoneyFlowTaskService', '', null, '{}');
INSERT INTO `invoke_type` VALUES ('MonthlyTaskService', '月线行情', 'MonthlyTaskService', null, null, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
INSERT INTO `invoke_type` VALUES ('StockBasicTaskService', '股票列表', 'StockBasicTaskService', null, null, '{\"is_hs\":\"是否沪深港通标的，N否 H沪股通 S深股通\",\"list_status\":\"上市状态： L上市 D退市 P暂停上市\",\"exchange\":\"交易所 SSE上交所 SZSE深交所 HKEX港交所(未上线)\"}');
INSERT INTO `invoke_type` VALUES ('StockDayKDJTaskService', '个股日线KDJ', 'StockDayKDJTaskService', '', '2021-05-03 12:20:27', '{“kdj_type”:\"9_3_3\",\"start_date\":\"20200101\",\"end_date\":\"20210430\"}');
INSERT INTO `invoke_type` VALUES ('StockfluctuationTaskService', '运算个股异动情况', 'StockfluctuationTaskService', null, null, '{}');
INSERT INTO `invoke_type` VALUES ('TradeCalTaskService', '交易日历', 'TradeCalTaskService', null, null, null);
INSERT INTO `invoke_type` VALUES ('WeeklyTaskService', '周线行情', 'WeeklyTaskService', null, null, '{\"ts_code\":\"股票代码（支持多个股票同时提取，逗号分隔）\",\"trade_date\":\"交易日期（YYYYMMDD）\",\"start_date\":\"开始日期(YYYYMMDD)\",\"end_date\":\"结束日期(YYYYMMDD)\"}');
