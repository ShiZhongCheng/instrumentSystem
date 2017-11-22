-- 数据库administrators
create database if not exists administrators;

use administrators;

-- 表users
create table if not exists users(
  user_id char(6) not null unique,
  openid varchar(20) unique,
  nick_name varchar(20) unique,
  name varchar(20),
  password varchar(32) not null
);

-- 表groups
create table if not exists groups(
  group_id char(6) not null unique,
  group_name varchar(50) not null unique,
  database_name varchar(15) not null unique,
  database_ip varchar(20) not null,
  status boolean not null
);

-- 用户转接表user_switch
create table if not exists user_switch(
  user_id char(6) not null unique,
  openid varchar(20) unique,
  nick_name varchar(20) unique,
  group_id char(6)
);






-- 创建单位A数据库
create database if not exists database_hdu;

use database_hdu;

-- 用户表user
create table if not exists users(
  user_id char(6) not null unique,
  openid varchar(20) unique,
  nick_name varchar(20) unique,
  name varchar(20),
  academic_number varchar(15),
  telephone char(11),
  email varchar(30),
  password varchar(50) not null,
  role varchar(10) not null
);

-- 区块表block
create table if not exists block(
  block_id char(6) not null unique,
  block_name varchar(50) not null unique,
  last_node char(6) not null,
  next_node varchar(1000),
  type varchar(10) not null,
  keeper char(6) not null,
  block_sp varchar(1000),
  block_dp varchar(1000),
  block_dp_check_crong varchar(50),
  block_dp_check_flag boolean
);

-- 未及时处理记录表without_delay
create table if not exists without_delay(
  wd_id char(6) not null unique,
  block_id char(6) not null,
  wd_detailes varchar(200) not null,
  wd_date varchar(20) not null,
  wd_flag boolean not null default false
);

-- 区块单位静态参数表static_parameter
create table if not exists static_parameter(
  sp_id char(6) not null unique,
  sp_name varchar(50) not null,
  sp_value varchar(150) not null,
  sp_type varchar(50) not null,
  block_id char(6) not null
);

-- 区块动态参数表dynamic_parameter
create table if not exists dynamic_parameter(
  dp_id char(6) not null unique,
  dp_name varchar(50) not null,
  block_id char(6) not null
);

-- 区块动态参数检修记录record
create table if not exists record(
  dp_id char(6) not null,
  dp_name varchar(50) not null,
  block_id char(6) not null unique,
  dp_value varchar(150) not null,
  recond_date varchar(20) not null
);

-- 配置表conf
create table if not exists conf(
  co_id char(6) not null unique,
  co_name varchar(50),
  co_value varchar(50) not null
);























