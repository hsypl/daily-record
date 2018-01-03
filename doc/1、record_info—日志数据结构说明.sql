-- -----------------------------------------------------------------------------
-- 6、记录信息数据结构说明-数据表结构定义
-- 2016年12月28日
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- Create database record_info
-- -----------------------------------------------------------------------------
set character_set_client=utf8,character_set_connection=utf8,character_set_results=utf8;
CREATE DATABASE IF NOT EXISTS record_info DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
USE ilearning_log;


CREATE TABLE IF NOT EXISTS user_info (
  uid VARCHAR (64) NOT NULL  COMMENT '用户id',
  username VARCHAR(128) NOT NULL COMMENT '系统用户名（邮箱）',
  password VARCHAR(128) NOT NULL COMMENT '登录密码',
  name VARCHAR(128) NOT NULL COMMENT '姓名',
  PRIMARY KEY (uid),
  UNIQUE(username),
  INDEX(name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '用户信息表';


-- -----------------------------------------------------------------------------
-- 1.0 plan_info
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS plan_info(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  description VARCHAR (512) NOT NULL COMMENT '内容描述',
  start_time BIGINT NOT NULL COMMENT '开始时间',
  complete_time BIGINT NOT NULL COMMENT '完成时间',
  remark  VARCHAR (512) NOT NULL COMMENT '内容描述',
  status INT NOT NULL COMMENT '是否完成',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '任务计划表';


-- 1.0 plan_info
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ico_project_info(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  uid  VARCHAR (24) NOT NULL COMMENT '用户id',
  name VARCHAR (48) NOT NULL COMMENT '项目名称',
  website VARCHAR (128) NOT NULL COMMENT '网址',
  price VARCHAR (128) NOT NULL COMMENT '金额',
  symbol VARCHAR (24) NOT NULL COMMENT '代币符号',
  number DOUBLE NOT NULL COMMENT '数量',
  remark VARCHAR (128)NOT NULL COMMENT '备注',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT 'ICO项目记录';


-- 1.0 currency_info
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS currency_info(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  name VARCHAR (24) NOT NULL COMMENT '币种名称',
  usd_price  VARCHAR (10) NOT NULL COMMENT '美元价格',
  cny_price  VARCHAR (10) NOT NULL COMMENT '人民币价格',
  status INT NOT NULL COMMENT '状态',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '币种记录表';

-- 1.0 assets_history
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS assets_history(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  uid  VARCHAR (24) NOT NULL COMMENT '用户id',
  amount  BIGINT NOT NULL COMMENT '总资产',
  create_time BIGINT NOT NULL COMMENT '统计日期',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '资产历史记录';


CREATE TABLE IF NOT EXISTS coin_market_cap(
  id  VARCHAR (64) NOT NULL COMMENT 'id',
  name  VARCHAR (64) NOT NULL COMMENT '名称',
  symbol VARCHAR (24) NOT NULL COMMENT '代币符号',
  rank  INT NOT NULL COMMENT '排名',
  price_usd  DOUBLE NOT NULL COMMENT '美元',
  price_btc  DOUBLE NOT NULL COMMENT 'BTC价格',
  volume_usd_24h  DOUBLE NOT NULL COMMENT '24小时交易量',
  market_cap_usd DOUBLE NOT NULL COMMENT '市值',
  percent_change_24h DOUBLE NOT NULL COMMENT '24小时涨幅',
  percent_change_7d DOUBLE NOT NULL COMMENT '一星期涨幅',
  price_cny DOUBLE NOT NULL COMMENT '人民币',
  volume_cny_24h DOUBLE NOT NULL COMMENT '24小时交易量',
  market_cap_cny DOUBLE NOT NULL COMMENT '市值',
  last_updated BIGINT NOT NULL COMMENT '更新时间',
  status INT NOT NULL COMMENT '状态',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '市值信息记录表';

-- 1.0 coin_history
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS coin_history(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  symbol VARCHAR (64) NOT NULL COMMENT '代币符号',
  price  DOUBLE NOT NULL COMMENT '价格',
  volume DOUBLE NOT NULL COMMENT '交易量',
  create_time BIGINT NOT NULL COMMENT '日期',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '资产历史记录';

-- 1.0 user_coin_relation
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS user_coin_relation(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  uid  VARCHAR (24) NOT NULL COMMENT '用户id',
  symbol VARCHAR (64) NOT NULL COMMENT '代币符号',
  priority INT NOT NULL COMMENT '优先级',
  PRIMARY KEY(id),
  UNIQUE (uid,symbol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '资产历史记录';

-- 1.0 change_log
-- 创建时间：2017年7月11日
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS change_log(
  id   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  uid  VARCHAR (24) NOT NULL COMMENT '用户id',
  symbol VARCHAR (64) NOT NULL COMMENT '代币符号',
  price DOUBLE  NOT NULL COMMENT '金额',
  number DOUBLE NOT NULL COMMENT '数量',
  amount INT NOT NULL COMMENT '总量',
  type INT NOT NULL COMMENT '类型',
  remark VARCHAR (128)NOT NULL COMMENT '备注',
  create_time BIGINT NOT NULL COMMENT '交易日期',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT '交易记录';




