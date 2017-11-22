-- ���ݿ�administrators
create database if not exists administrators;

use administrators;

-- ��users
create table if not exists users(
  user_id char(6) not null unique,
  openid varchar(20) unique,
  nick_name varchar(20) unique,
  name varchar(20),
  password varchar(32) not null
);

-- ��groups
create table if not exists groups(
  group_id char(6) not null unique,
  group_name varchar(50) not null unique,
  database_name varchar(15) not null unique,
  database_ip varchar(20) not null,
  status boolean not null
);

-- �û�ת�ӱ�user_switch
create table if not exists user_switch(
  user_id char(6) not null unique,
  openid varchar(20) unique,
  nick_name varchar(20) unique,
  group_id char(6)
);






-- ������λA���ݿ�
create database if not exists database_hdu;

use database_hdu;

-- �û���user
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

-- �����block
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

-- δ��ʱ�����¼��without_delay
create table if not exists without_delay(
  wd_id char(6) not null unique,
  block_id char(6) not null,
  wd_detailes varchar(200) not null,
  wd_date varchar(20) not null,
  wd_flag boolean not null default false
);

-- ���鵥λ��̬������static_parameter
create table if not exists static_parameter(
  sp_id char(6) not null unique,
  sp_name varchar(50) not null,
  sp_value varchar(150) not null,
  sp_type varchar(50) not null,
  block_id char(6) not null
);

-- ���鶯̬������dynamic_parameter
create table if not exists dynamic_parameter(
  dp_id char(6) not null unique,
  dp_name varchar(50) not null,
  block_id char(6) not null
);

-- ���鶯̬�������޼�¼record
create table if not exists record(
  dp_id char(6) not null,
  dp_name varchar(50) not null,
  block_id char(6) not null unique,
  dp_value varchar(150) not null,
  recond_date varchar(20) not null
);

-- ���ñ�conf
create table if not exists conf(
  co_id char(6) not null unique,
  co_name varchar(50),
  co_value varchar(50) not null
);























