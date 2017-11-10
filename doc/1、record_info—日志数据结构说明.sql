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
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  username VARCHAR(128) NOT NULL COMMENT '系统用户名（邮箱）',
  password VARCHAR(128) NOT NULL COMMENT '登录密码',
  name VARCHAR(128) NOT NULL COMMENT '姓名',
  PRIMARY KEY (id),
  UNIQUE(username),
  INDEX(name),
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
  name VARCHAR (24) NOT NULL COMMENT '项目名称',
  website VARCHAR (128) NOT NULL COMMENT '网址',
  price VARCHAR (128) NOT NULL COMMENT '金额',
  symbol VARCHAR (12) NOT NULL COMMENT '代币符号',
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




