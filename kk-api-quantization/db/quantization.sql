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

 Date: 13/05/2023 14:51:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cn_m
-- ----------------------------
DROP TABLE IF EXISTS `cn_m`;
CREATE TABLE `cn_m`  (
  `month` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '月份',
  `m0` double(18, 0) NULL DEFAULT NULL COMMENT '货币M0总量',
  `m0_yoy` double(18, 0) NULL DEFAULT NULL COMMENT '货币M0同比',
  `m0_mom` double(18, 0) NULL DEFAULT NULL COMMENT '货币M0环比',
  `m1` double(18, 0) NULL DEFAULT NULL COMMENT '货币M1总量',
  `m1_yoy` double(18, 0) NULL DEFAULT NULL COMMENT '货币M1同比',
  `m1_mom` double(18, 0) NULL DEFAULT NULL COMMENT '货币M1环比',
  `m2` double(18, 0) NULL DEFAULT NULL COMMENT '货币M2总量',
  `m2_yoy` double(18, 0) NULL DEFAULT NULL COMMENT '货币M2同比',
  `m2_mom` double(18, 0) NULL DEFAULT NULL COMMENT '货币M2环比',
  PRIMARY KEY (`month`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '人民币货币总量对象' ROW_FORMAT = Dynamic;

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
-- Table structure for collection_task
-- ----------------------------
DROP TABLE IF EXISTS `collection_task`;
CREATE TABLE `collection_task`  (
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务名称',
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所属策略编号',
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类编号',
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类方法名称',
  `invoke_params` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '调用类方法参数',
  `pre_run_time` datetime(0) NULL DEFAULT NULL COMMENT '预执行时间',
  `run_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `create_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `create_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `run_count` int(11) NULL DEFAULT NULL COMMENT '执行次数',
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '异常信息',
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `idx_policy_id`(`policy_id`) USING BTREE,
  INDEX `idx_invoke_code`(`invoke_code`) USING BTREE,
  INDEX `idx_pre_run_time`(`pre_run_time`) USING BTREE,
  INDEX `idx_run_count`(`run_count`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection_task_history
-- ----------------------------
DROP TABLE IF EXISTS `collection_task_history`;
CREATE TABLE `collection_task_history`  (
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务名称',
  `policy_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所属策略编号',
  `invoke_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类编号',
  `invoke_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '调用类方法名称',
  `invoke_params` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '调用类方法参数',
  `pre_run_time` datetime(0) NULL DEFAULT NULL COMMENT '预执行时间',
  `run_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `create_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `create_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `run_count` int(11) NULL DEFAULT NULL COMMENT '执行次数',
  `ex_msg` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '异常信息',
  INDEX `idx_policy_id`(`policy_id`) USING BTREE,
  INDEX `idx_invoke_code`(`invoke_code`) USING BTREE,
  INDEX `idx_pre_run_time`(`pre_run_time`) USING BTREE,
  INDEX `idx_run_count`(`run_count`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for concept
-- ----------------------------
DROP TABLE IF EXISTS `concept`;
CREATE TABLE `concept`  (
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '概念代码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '概念名称',
  `src` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '来源 ts:tushare,ths:同花顺 90：东方财富',
  `exchange` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交易所',
  `list_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上市日期',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'N概念指数S特色指数',
  `count` int(11) NULL DEFAULT NULL COMMENT '成分个数',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for concept_daily
-- ----------------------------
DROP TABLE IF EXISTS `concept_daily`;
CREATE TABLE `concept_daily`  (
  `concept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '概念编号',
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `open` double NULL DEFAULT NULL COMMENT '开盘价',
  `high` double NULL DEFAULT NULL COMMENT '最高价',
  `low` double NULL DEFAULT NULL COMMENT '最低价',
  `close` double NULL DEFAULT NULL COMMENT '收盘价',
  `pre_close` double NULL DEFAULT NULL COMMENT '前收盘价',
  `change` double NULL DEFAULT NULL COMMENT '涨跌额',
  `pct_chg` double NULL DEFAULT NULL COMMENT '涨跌幅%',
  `vol` double NULL DEFAULT NULL COMMENT '成交量',
  `amount` double NULL DEFAULT NULL COMMENT '成交额',
  PRIMARY KEY (`concept_id`, `trade_date`) USING BTREE,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for concept_detail
-- ----------------------------
DROP TABLE IF EXISTS `concept_detail`;
CREATE TABLE `concept_detail`  (
  `concept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '概念编号',
  `concept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '概念名称',
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '股票编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '股票名称',
  `in_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '加入日期',
  `out_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '剔除日期',
  `symbol` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '股票代码',
  `weight` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权重',
  PRIMARY KEY (`concept_id`, `ts_code`) USING BTREE,
  INDEX `idx_ts_code`(`ts_code`) USING BTREE,
  INDEX `idx_symbol`(`symbol`) USING BTREE,
  INDEX `idx_in_date`(`in_date`) USING BTREE,
  INDEX `idx_out_date`(`out_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for concept_moneyflow
-- ----------------------------
DROP TABLE IF EXISTS `concept_moneyflow`;
CREATE TABLE `concept_moneyflow`  (
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `buy_sm_vol` int(11) NULL DEFAULT NULL,
  `buy_sm_amount` double NULL DEFAULT NULL,
  `sell_sm_vol` int(11) NULL DEFAULT NULL,
  `sell_sm_amount` double NULL DEFAULT NULL,
  `buy_md_vol` int(11) NULL DEFAULT NULL,
  `buy_md_amount` double NULL DEFAULT NULL,
  `sell_md_vol` int(11) NULL DEFAULT NULL,
  `sell_md_amount` double NULL DEFAULT NULL,
  `buy_lg_vol` int(11) NULL DEFAULT NULL,
  `buy_lg_amount` double NULL DEFAULT NULL,
  `sell_lg_vol` int(11) NULL DEFAULT NULL,
  `sell_lg_amount` double NULL DEFAULT NULL,
  `buy_elg_vol` int(11) NULL DEFAULT NULL,
  `buy_elg_amount` double NULL DEFAULT NULL,
  `sell_elg_vol` int(11) NULL DEFAULT NULL,
  `sell_elg_amount` double NULL DEFAULT NULL,
  `net_mf_vol` int(11) NULL DEFAULT NULL,
  `net_mf_amount` double NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`code`, `trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '股票代码',
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `open` double NULL DEFAULT NULL COMMENT '开盘价',
  `high` double NULL DEFAULT NULL COMMENT '最高价',
  `low` double NULL DEFAULT NULL COMMENT '最低价',
  `close` double NULL DEFAULT NULL COMMENT '收盘价',
  `pre_close` double NULL DEFAULT NULL COMMENT '上一日收盘价',
  `change` double NULL DEFAULT NULL COMMENT '涨跌额',
  `pct_chg` double NULL DEFAULT NULL COMMENT '涨跌幅%',
  `vol` double NULL DEFAULT NULL COMMENT '成交额',
  `amount` double NULL DEFAULT NULL COMMENT '成交量',
  PRIMARY KEY (`ts_code`, `trade_date`) USING BTREE,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for daily_basic
-- ----------------------------
DROP TABLE IF EXISTS `daily_basic`;
CREATE TABLE `daily_basic`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'TS代码',
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `close` double NULL DEFAULT NULL COMMENT '当日收盘价',
  `turnover_rate` double NULL DEFAULT NULL COMMENT '换手率（%）',
  `turnover_rate_f` double NULL DEFAULT NULL COMMENT '换手率（自由流通股）',
  `volume_ratio` double NULL DEFAULT NULL COMMENT '量比',
  `pe` double NULL DEFAULT NULL COMMENT '市盈率（总市值/净利润， 亏损的PE为空）',
  `pe_ttm` double NULL DEFAULT NULL COMMENT '市盈率（TTM，亏损的PE为空）',
  `pb` double NULL DEFAULT NULL COMMENT '市净率（总市值/净资产）',
  `ps` double NULL DEFAULT NULL COMMENT '市销率',
  `ps_ttm` double NULL DEFAULT NULL COMMENT '市销率（TTM）',
  `dv_ratio` double NULL DEFAULT NULL COMMENT '股息率 （%）',
  `dv_ttm` double NULL DEFAULT NULL COMMENT '股息率（TTM）（%）',
  `total_share` double NULL DEFAULT NULL COMMENT '总股本 （万股）',
  `float_share` double NULL DEFAULT NULL COMMENT '流通股本 （万股）',
  `free_share` double NULL DEFAULT NULL COMMENT '自由流通股本 （万）',
  `total_mv` double NULL DEFAULT NULL COMMENT '当日总市值（元）',
  `circ_mv` double NULL DEFAULT NULL COMMENT '流通市值（万元）',
  PRIMARY KEY (`ts_code`, `trade_date`) USING BTREE,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for daily_time
-- ----------------------------
DROP TABLE IF EXISTS `daily_time`;
CREATE TABLE `daily_time`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `open` double NULL DEFAULT NULL,
  `high` double NULL DEFAULT NULL,
  `low` double NULL DEFAULT NULL,
  `close` double NULL DEFAULT NULL,
  `pre_close` double NULL DEFAULT NULL,
  `change` double NULL DEFAULT NULL,
  `pct_chg` double NULL DEFAULT NULL,
  `vol` double NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for index_basic
-- ----------------------------
DROP TABLE IF EXISTS `index_basic`;
CREATE TABLE `index_basic`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `market` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `publisher` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `index_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `category` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `base_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `base_point` double NULL DEFAULT NULL,
  `list_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `weight_rule` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `exp_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for index_classify
-- ----------------------------
DROP TABLE IF EXISTS `index_classify`;
CREATE TABLE `index_classify`  (
  `index_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `industry_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `level` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `industry_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `src` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for index_member
-- ----------------------------
DROP TABLE IF EXISTS `index_member`;
CREATE TABLE `index_member`  (
  `index_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `index_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `con_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `con_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `in_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `out_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_new` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

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
-- Table structure for kdj_cross
-- ----------------------------
DROP TABLE IF EXISTS `kdj_cross`;
CREATE TABLE `kdj_cross`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '股票编码',
  `trade_date` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `cross_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交叉点类型（UP:金叉,DOWN:死叉）',
  `analysis_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '运算周期类型（默认 9_3_3）',
  PRIMARY KEY (`ts_code`, `trade_date`) USING BTREE,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE,
  INDEX `idx_cross_type`(`cross_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for max_pct_chg
-- ----------------------------
DROP TABLE IF EXISTS `max_pct_chg`;
CREATE TABLE `max_pct_chg`  (
  `market` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `max_pct_chg` double NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for money_flow
-- ----------------------------
DROP TABLE IF EXISTS `money_flow`;
CREATE TABLE `money_flow`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `buy_sm_vol` int(11) NULL DEFAULT NULL,
  `buy_sm_amount` double NULL DEFAULT NULL,
  `sell_sm_vol` int(11) NULL DEFAULT NULL,
  `sell_sm_amount` double NULL DEFAULT NULL,
  `buy_md_vol` int(11) NULL DEFAULT NULL,
  `buy_md_amount` double NULL DEFAULT NULL,
  `sell_md_vol` int(11) NULL DEFAULT NULL,
  `sell_md_amount` double NULL DEFAULT NULL,
  `buy_lg_vol` int(11) NULL DEFAULT NULL,
  `buy_lg_amount` double NULL DEFAULT NULL,
  `sell_lg_vol` int(11) NULL DEFAULT NULL,
  `sell_lg_amount` double NULL DEFAULT NULL,
  `buy_elg_vol` int(11) NULL DEFAULT NULL,
  `buy_elg_amount` double NULL DEFAULT NULL,
  `sell_elg_vol` int(11) NULL DEFAULT NULL,
  `sell_elg_amount` double NULL DEFAULT NULL,
  `net_mf_vol` int(11) NULL DEFAULT NULL,
  `net_mf_amount` double NULL DEFAULT NULL,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for monthly
-- ----------------------------
DROP TABLE IF EXISTS `monthly`;
CREATE TABLE `monthly`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `open` double NULL DEFAULT NULL,
  `high` double NULL DEFAULT NULL,
  `low` double NULL DEFAULT NULL,
  `close` double NULL DEFAULT NULL,
  `pre_close` double NULL DEFAULT NULL,
  `change` double NULL DEFAULT NULL,
  `pct_chg` double NULL DEFAULT NULL,
  `vol` double NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for security_selection
-- ----------------------------
DROP TABLE IF EXISTS `security_selection`;
CREATE TABLE `security_selection`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `select_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for serial_no
-- ----------------------------
DROP TABLE IF EXISTS `serial_no`;
CREATE TABLE `serial_no`  (
  `serial_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `next_value` int(11) NULL DEFAULT NULL,
  `min` int(11) NULL DEFAULT NULL,
  `max` char(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`serial_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock_basic
-- ----------------------------
DROP TABLE IF EXISTS `stock_basic`;
CREATE TABLE `stock_basic`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'ts股票代码',
  `symbol` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '股票代码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '股票名称',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地域',
  `industry` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '股票全称',
  `enname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '英文全称',
  `market` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '市场类型（主板/创业板/科创板/CDR）',
  `exchange` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交易所代码',
  `curr_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交易货币',
  `list_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上市状态 L上市 D退市 P暂停上市',
  `list_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上市日期',
  `delist_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '退市日期',
  `is_hs` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否沪深港通标的，N否 H沪股通 S深股通',
  `cnspell` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '拼音缩写',
  PRIMARY KEY (`ts_code`) USING BTREE,
  INDEX `idx_symbol`(`symbol`) USING BTREE,
  INDEX `idx_list_date`(`list_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock_day_kdj
-- ----------------------------
DROP TABLE IF EXISTS `stock_day_kdj`;
CREATE TABLE `stock_day_kdj`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '股票编号',
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易日期',
  `k_value` double NULL DEFAULT NULL COMMENT 'k值',
  `d_value` double NULL DEFAULT NULL COMMENT 'd值',
  `j_value` double NULL DEFAULT NULL COMMENT 'j值',
  `rsv` double NULL DEFAULT NULL COMMENT 'rsv值',
  `kdj_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'kdj类型（默认 9_3_3）',
  PRIMARY KEY (`ts_code`, `trade_date`) USING BTREE,
  INDEX `idx_trade_date`(`trade_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock_fluctuation
-- ----------------------------
DROP TABLE IF EXISTS `stock_fluctuation`;
CREATE TABLE `stock_fluctuation`  (
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `max_up_count` int(11) NULL DEFAULT NULL,
  `max_down_count` int(11) NULL DEFAULT NULL,
  `max_up_15d_count` int(11) NULL DEFAULT NULL,
  `max_down_15d_count` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sy_user
-- ----------------------------
DROP TABLE IF EXISTS `sy_user`;
CREATE TABLE `sy_user`  (
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `b_day` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_cal
-- ----------------------------
DROP TABLE IF EXISTS `trade_cal`;
CREATE TABLE `trade_cal`  (
  `exchange` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '交易所 SSE上交所 SZSE深交所',
  `cal_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '日历日期',
  `is_open` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否交易 0休市 1交易',
  `pretrade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上一个交易日',
  PRIMARY KEY (`exchange`, `cal_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for weekly
-- ----------------------------
DROP TABLE IF EXISTS `weekly`;
CREATE TABLE `weekly`  (
  `ts_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `trade_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `open` double NULL DEFAULT NULL,
  `high` double NULL DEFAULT NULL,
  `low` double NULL DEFAULT NULL,
  `close` double NULL DEFAULT NULL,
  `pre_close` double NULL DEFAULT NULL,
  `change` double NULL DEFAULT NULL,
  `pct_chg` double NULL DEFAULT NULL,
  `vol` double NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
