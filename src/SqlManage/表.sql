CREATE TABLE `t_carlist` (
`cid`  int(11) NOT NULL AUTO_INCREMENT ,
`cname`  varchar(50) NOT NULL ,
`ctype`  int(11) NULL DEFAULT 1 ,
`price`  float(6,1) NOT NULL ,
`cimage`  varchar(100) NULL ,
`insure`  float(6,1) NOT NULL ,
`desp`  varchar(200) NULL DEFAULT '（这个用户很懒没有评价）' ,
PRIMARY KEY (`cid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT;

CREATE TABLE `t_order` (
`oid`  int(11) NOT NULL AUTO_INCREMENT ,
`uid`  int(11) NOT NULL ,
`rsd`  date NOT NULL ,
`red`  date NOT NULL ,
`otime`  int(2) NOT NULL ,
`cid`  int(11) NOT NULL ,
`total`  float(7,1) NOT NULL ,
PRIMARY KEY (`oid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT;

CREATE TABLE `t_userinfo` (
`uid`  int(15) NOT NULL AUTO_INCREMENT ,
`phone`  varchar(11) NOT NULL ,
`password`  varchar(20) NOT NULL ,
`tname`  varchar(20) NOT NULL ,
`id`  varchar(18) NOT NULL ,
`genda`  int(1) NOT NULL DEFAULT 1 ,
`regtime`  date NOT NULL ,
`amount`  float(10,1) NOT NULL DEFAULT 10.0 ,
PRIMARY KEY (`uid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT
;