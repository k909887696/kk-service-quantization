/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : quantization

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-10-16 13:27:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cn_m
-- ----------------------------
DROP TABLE IF EXISTS `cn_m`;
CREATE TABLE `cn_m` (
  `month` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `m0` double DEFAULT NULL,
  `m0_yoy` double DEFAULT NULL,
  `m0_mom` double DEFAULT NULL,
  `m1` double DEFAULT NULL,
  `m1_yoy` double DEFAULT NULL,
  `m1_mom` double DEFAULT NULL,
  `m2` double DEFAULT NULL,
  `m2_yoy` double DEFAULT NULL,
  `m2_mom` double DEFAULT NULL,
  PRIMARY KEY (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for collection_policy
-- ----------------------------
DROP TABLE IF EXISTS `collection_policy`;
CREATE TABLE `collection_policy` (
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_method` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_cycle` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `pre_run_time` timestamp NULL DEFAULT NULL,
  `invoke_params` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `run_count` int DEFAULT NULL,
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `invoke_cycle_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`policy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for collection_task
-- ----------------------------
DROP TABLE IF EXISTS `collection_task`;
CREATE TABLE `collection_task` (
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `pre_run_time` timestamp NULL DEFAULT NULL,
  `run_time` timestamp NULL DEFAULT NULL,
  `finish_time` timestamp NULL DEFAULT NULL,
  `create_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `run_count` int DEFAULT NULL,
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  PRIMARY KEY (`task_id`),
  KEY `t2` (`task_id`(2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for collection_task_history
-- ----------------------------
DROP TABLE IF EXISTS `collection_task_history`;
CREATE TABLE `collection_task_history` (
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `invoke_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `pre_run_time` timestamp NULL DEFAULT NULL,
  `run_time` timestamp NULL DEFAULT NULL,
  `finish_time` timestamp NULL DEFAULT NULL,
  `create_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `run_count` int DEFAULT NULL,
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  KEY `t2` (`task_id`(2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for collection_task_o
-- ----------------------------
DROP TABLE IF EXISTS `collection_task_o`;
CREATE TABLE `collection_task_o` (
  `TaskId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PolicyId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `InvokeObject` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `InvokeMethod` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `InvokeParams` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PreRunTime` timestamp NULL DEFAULT NULL,
  `RunTime` timestamp NULL DEFAULT NULL,
  `FinishTime` timestamp NULL DEFAULT NULL,
  `CreateId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CreateName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CreateTime` timestamp NULL DEFAULT NULL,
  `RunCount` int DEFAULT NULL,
  `ExMsg` longtext COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for concept
-- ----------------------------
DROP TABLE IF EXISTS `concept`;
CREATE TABLE `concept` (
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `src` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for concept_daily
-- ----------------------------
DROP TABLE IF EXISTS `concept_daily`;
CREATE TABLE `concept_daily` (
  `concept_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `pre_close` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  `pct_chg` double DEFAULT NULL,
  `vol` double DEFAULT NULL,
  `amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for concept_detail
-- ----------------------------
DROP TABLE IF EXISTS `concept_detail`;
CREATE TABLE `concept_detail` (
  `id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `concept_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `in_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `out_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `symbol` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for concept_moneyflow
-- ----------------------------
DROP TABLE IF EXISTS `concept_moneyflow`;
CREATE TABLE `concept_moneyflow` (
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `buy_sm_vol` int DEFAULT NULL,
  `buy_sm_amount` double DEFAULT NULL,
  `sell_sm_vol` int DEFAULT NULL,
  `sell_sm_amount` double DEFAULT NULL,
  `buy_md_vol` int DEFAULT NULL,
  `buy_md_amount` double DEFAULT NULL,
  `sell_md_vol` int DEFAULT NULL,
  `sell_md_amount` double DEFAULT NULL,
  `buy_lg_vol` int DEFAULT NULL,
  `buy_lg_amount` double DEFAULT NULL,
  `sell_lg_vol` int DEFAULT NULL,
  `sell_lg_amount` double DEFAULT NULL,
  `buy_elg_vol` int DEFAULT NULL,
  `buy_elg_amount` double DEFAULT NULL,
  `sell_elg_vol` int DEFAULT NULL,
  `sell_elg_amount` double DEFAULT NULL,
  `net_mf_vol` int DEFAULT NULL,
  `net_mf_amount` double DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '股票代码',
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `open` double DEFAULT NULL COMMENT '开盘价',
  `high` double DEFAULT NULL COMMENT '最高价',
  `low` double DEFAULT NULL COMMENT '最低价',
  `close` double DEFAULT NULL COMMENT '收盘价',
  `pre_close` double DEFAULT NULL COMMENT '上一日收盘价',
  `change` double DEFAULT NULL COMMENT '涨跌额',
  `pct_chg` double DEFAULT NULL COMMENT '涨跌幅%',
  `vol` double DEFAULT NULL COMMENT '成交额',
  `amount` double DEFAULT NULL COMMENT '成交量',
  PRIMARY KEY (`ts_code`,`trade_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for daily_basic
-- ----------------------------
DROP TABLE IF EXISTS `daily_basic`;
CREATE TABLE `daily_basic` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `close` double DEFAULT NULL,
  `turnover_rate` double DEFAULT NULL,
  `turnover_rate_f` double DEFAULT NULL,
  `volume_ratio` double DEFAULT NULL,
  `pe` double DEFAULT NULL,
  `pe_ttm` double DEFAULT NULL,
  `pb` double DEFAULT NULL,
  `ps` double DEFAULT NULL,
  `ps_ttm` double DEFAULT NULL,
  `dv_ratio` double DEFAULT NULL,
  `dv_ttm` double DEFAULT NULL,
  `total_share` double DEFAULT NULL,
  `float_share` double DEFAULT NULL,
  `free_share` double DEFAULT NULL,
  `total_mv` double DEFAULT NULL,
  `circ_mv` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for daily_time
-- ----------------------------
DROP TABLE IF EXISTS `daily_time`;
CREATE TABLE `daily_time` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `pre_close` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  `pct_chg` double DEFAULT NULL,
  `vol` double DEFAULT NULL,
  `amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for index_basic
-- ----------------------------
DROP TABLE IF EXISTS `index_basic`;
CREATE TABLE `index_basic` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publisher` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `index_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `base_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `base_point` double DEFAULT NULL,
  `list_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `weight_rule` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `exp_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for index_classify
-- ----------------------------
DROP TABLE IF EXISTS `index_classify`;
CREATE TABLE `index_classify` (
  `index_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `industry_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `industry_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `src` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for index_member
-- ----------------------------
DROP TABLE IF EXISTS `index_member`;
CREATE TABLE `index_member` (
  `index_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `index_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `con_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `con_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `in_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `out_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_new` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

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
-- Table structure for kdj_cross
-- ----------------------------
DROP TABLE IF EXISTS `kdj_cross`;
CREATE TABLE `kdj_cross` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cross_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `analysis_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for max_pct_chg
-- ----------------------------
DROP TABLE IF EXISTS `max_pct_chg`;
CREATE TABLE `max_pct_chg` (
  `market` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_pct_chg` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for moneyflow
-- ----------------------------
DROP TABLE IF EXISTS `moneyflow`;
CREATE TABLE `moneyflow` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `buy_sm_vol` int DEFAULT NULL,
  `buy_sm_amount` double DEFAULT NULL,
  `sell_sm_vol` int DEFAULT NULL,
  `sell_sm_amount` double DEFAULT NULL,
  `buy_md_vol` int DEFAULT NULL,
  `buy_md_amount` double DEFAULT NULL,
  `sell_md_vol` int DEFAULT NULL,
  `sell_md_amount` double DEFAULT NULL,
  `buy_lg_vol` int DEFAULT NULL,
  `buy_lg_amount` double DEFAULT NULL,
  `sell_lg_vol` int DEFAULT NULL,
  `sell_lg_amount` double DEFAULT NULL,
  `buy_elg_vol` int DEFAULT NULL,
  `buy_elg_amount` double DEFAULT NULL,
  `sell_elg_vol` int DEFAULT NULL,
  `sell_elg_amount` double DEFAULT NULL,
  `net_mf_vol` int DEFAULT NULL,
  `net_mf_amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for monthly
-- ----------------------------
DROP TABLE IF EXISTS `monthly`;
CREATE TABLE `monthly` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `pre_close` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  `pct_chg` double DEFAULT NULL,
  `vol` double DEFAULT NULL,
  `amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for security_selection
-- ----------------------------
DROP TABLE IF EXISTS `security_selection`;
CREATE TABLE `security_selection` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `select_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for serialno
-- ----------------------------
DROP TABLE IF EXISTS `serialno`;
CREATE TABLE `serialno` (
  `SerialName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NextValue` int DEFAULT NULL,
  `Min` int DEFAULT NULL,
  `Max` char(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for stock_basic
-- ----------------------------
DROP TABLE IF EXISTS `stock_basic`;
CREATE TABLE `stock_basic` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `symbol` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `industry` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `exchange` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `curr_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `list_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `list_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `delist_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_hs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for stock_day_kdj
-- ----------------------------
DROP TABLE IF EXISTS `stock_day_kdj`;
CREATE TABLE `stock_day_kdj` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `k_value` double DEFAULT NULL,
  `d_value` double DEFAULT NULL,
  `j_value` double DEFAULT NULL,
  `rsv` double DEFAULT NULL,
  `kdj_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for stock_fluctuation
-- ----------------------------
DROP TABLE IF EXISTS `stock_fluctuation`;
CREATE TABLE `stock_fluctuation` (
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_up_count` int DEFAULT NULL,
  `max_down_count` int DEFAULT NULL,
  `max_up_15d_count` int DEFAULT NULL,
  `max_down_15d_count` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sy_user
-- ----------------------------
DROP TABLE IF EXISTS `sy_user`;
CREATE TABLE `sy_user` (
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `b_day` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for trade_cal
-- ----------------------------
DROP TABLE IF EXISTS `trade_cal`;
CREATE TABLE `trade_cal` (
  `exchange` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cal_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_open` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pretrade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for weekly
-- ----------------------------
DROP TABLE IF EXISTS `weekly`;
CREATE TABLE `weekly` (
  `ts_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trade_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `pre_close` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  `pct_chg` double DEFAULT NULL,
  `vol` double DEFAULT NULL,
  `amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
