/*
SQLyog Ultimate v8.32 
MySQL - 5.5.19 : Database - badmintondeity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`badmintondeity` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `badmintondeity`;

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `REGION_ID` double NOT NULL,
  `REGION_CODE` varchar(100) NOT NULL,
  `REGION_NAME` varchar(100) NOT NULL,
  `PARENT_ID` double NOT NULL,
  `REGION_LEVEL` double NOT NULL,
  `REGION_ORDER` double NOT NULL,
  `REGION_NAME_EN` varchar(100) NOT NULL,
  `REGION_SHORTNAME_EN` varchar(10) NOT NULL,
  PRIMARY KEY (`REGION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_acc_credit` */

DROP TABLE IF EXISTS `t_acc_credit`;

CREATE TABLE `t_acc_credit` (
  `ACCOUNT_ID` decimal(8,0) NOT NULL COMMENT '账户ID',
  `USER_ID` decimal(8,0) NOT NULL COMMENT '用户ID',
  `ACC_TYPE` varchar(1) COLLATE utf8_bin DEFAULT 'C' COMMENT '账户类型',
  `ACC_STATUS` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '账户状态',
  `ACC_OPEN_DATE` datetime DEFAULT NULL COMMENT '开户时间',
  `ACC_BALANCE` decimal(8,0) DEFAULT NULL COMMENT '账户余额',
  `ACC_FREEZE` decimal(8,0) DEFAULT NULL COMMENT '账户冻结金额',
  `ACC_ENCHASH` decimal(8,0) DEFAULT NULL COMMENT '可提现金额',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_acc_credit_detail` */

DROP TABLE IF EXISTS `t_acc_credit_detail`;

CREATE TABLE `t_acc_credit_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ACCOUNT_ID` decimal(8,0) NOT NULL COMMENT '账户ID',
  `USER_ID` decimal(8,0) NOT NULL COMMENT '用户ID',
  `BS_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '账户操作类型',
  `BS_NO` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '账户业务编号',
  `ACC_TRAN_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '账户变化类型',
  `AMNT` decimal(8,0) DEFAULT NULL COMMENT '金额',
  `ENROLL_ID` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '报名ID',
  `EXT2` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展字段2',
  `EXT3` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展字段3',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户积分明细表';

/*Table structure for table `t_account` */

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `ACCOUT_NO` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ACC_TYPE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACC_STATUS` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACC_BALANCE` decimal(20,4) DEFAULT NULL,
  `ACC_FREEZE` decimal(20,4) DEFAULT NULL,
  `ACC_ENCHASH` decimal(20,4) DEFAULT NULL,
  `CREATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ACCOUT_NO`),
  KEY `FK_t_account` (`USER_ID`),
  CONSTRAINT `FK_t_account` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_account_detail` */

DROP TABLE IF EXISTS `t_account_detail`;

CREATE TABLE `t_account_detail` (
  `ACC_DEAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUT_NO` int(11) NOT NULL,
  `TRANSION_SEQ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PAYMENT_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` decimal(20,4) DEFAULT NULL,
  `CREATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ACC_DEAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_activity_enroll` */

DROP TABLE IF EXISTS `t_activity_enroll`;

CREATE TABLE `t_activity_enroll` (
  `ENROLL_ID` int(11) NOT NULL COMMENT '主键-流水号',
  `ACTIVITY_ID` int(11) DEFAULT NULL COMMENT '比赛id',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户id',
  `IDENTITY` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛人员身份',
  `STATUS` varchar(2) COLLATE utf8_bin DEFAULT '0' COMMENT '报名状态',
  `NUMBER` int(4) DEFAULT NULL COMMENT '人数',
  `PAY_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型',
  `ACC_NO` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '支付帐号',
  `PAY_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '支付流水号',
  `PAY_DATE` datetime DEFAULT NULL COMMENT '支付日期',
  `ORIGINAL_COST` decimal(4,2) DEFAULT NULL COMMENT '报名原价',
  `COST` decimal(4,2) DEFAULT NULL COMMENT '支付钱数',
  `SETTLE_ID` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '结算编号',
  `SIGN_IN` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '签到标识',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`ENROLL_ID`),
  KEY `FK_t_activity_enroll` (`ACTIVITY_ID`),
  CONSTRAINT `FK_t_activity_enroll` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `t_activity_manage` (`ACTIVITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_activity_enroll_rule` */

DROP TABLE IF EXISTS `t_activity_enroll_rule`;

CREATE TABLE `t_activity_enroll_rule` (
  `RULE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则id',
  `BASEQ_NO` int(11) DEFAULT NULL COMMENT '序号',
  `RULE_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '规则类型',
  `RULE_CODE` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '规则编码',
  `RULE_FIELDS` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '规则检查字段',
  `RULE_COUNT_METHOD` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '规则计算方法',
  `RULE_TABLE_METHOD` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '规则表方法',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `ACTIVITY_ID` int(20) DEFAULT NULL COMMENT '比赛id',
  PRIMARY KEY (`RULE_ID`),
  KEY `FK_t_activity_enroll_ rule` (`ACTIVITY_ID`),
  CONSTRAINT `FK_t_activity_enroll_ rule` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `t_activity_manage` (`ACTIVITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='比赛规则表';

/*Table structure for table `t_activity_enroll_rule_templa` */

DROP TABLE IF EXISTS `t_activity_enroll_rule_templa`;

CREATE TABLE `t_activity_enroll_rule_templa` (
  `TEMPLATE_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '比赛id',
  `RULE_SEQ` decimal(10,0) DEFAULT NULL COMMENT '规则序号',
  `RULE_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '规则编码',
  `RULE_FIELD` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '规则字段',
  `RULE_FIELD_VALUE` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '字段值',
  `RULE_COUNT_METHOD` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '计算方法',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_activity_manage` */

DROP TABLE IF EXISTS `t_activity_manage`;

CREATE TABLE `t_activity_manage` (
  `ACTIVITY_ID` int(11) NOT NULL COMMENT '比赛id',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '群id',
  `ACTIVITY_NAME` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛名称',
  `ACTIVITY_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛类型',
  `ACTIVITY_STATUS` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛状态',
  `USER_ID` int(20) DEFAULT NULL COMMENT '比赛发起人用户id',
  `ENROLL_ROLL_ID` int(11) DEFAULT NULL COMMENT '比赛报名条件',
  `ACTIVITY_BEGIN` datetime DEFAULT NULL COMMENT '比赛开始时间',
  `ACTIVITY_END` datetime DEFAULT NULL COMMENT '比赛结束时间',
  `ACTIVITY_ADDR` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛地点',
  `REMARK` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `MANAGE_ID` int(20) DEFAULT NULL COMMENT '管理员用户id',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ACTIVITY_ID`),
  KEY `FK_t_activity_manage` (`GROUP_ID`),
  CONSTRAINT `FK_t_activity_manage` FOREIGN KEY (`GROUP_ID`) REFERENCES `t_group_manage` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_activity_settle` */

DROP TABLE IF EXISTS `t_activity_settle`;

CREATE TABLE `t_activity_settle` (
  `SETTLE_ID` int(11) NOT NULL COMMENT '结算id',
  `SETTLE_STATUS` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '结算状态',
  `ACTIVITY_ID` int(20) DEFAULT NULL COMMENT '比赛ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '收款用户',
  `COST` decimal(4,0) DEFAULT NULL COMMENT '结算费用',
  `PAY_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '收款账户类型',
  `PAY_NO` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '收款账户号',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`SETTLE_ID`),
  KEY `FK_t_activity_settle` (`ACTIVITY_ID`),
  CONSTRAINT `FK_t_activity_settle` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `t_activity_manage` (`ACTIVITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_bank_acc` */

DROP TABLE IF EXISTS `t_bank_acc`;

CREATE TABLE `t_bank_acc` (
  `USER_BANK_ACC_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户银行id',
  `USER_ID` int(20) NOT NULL COMMENT '用户id',
  `BANK_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '银行代码',
  `BANK_NAME` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '银行名称',
  `BANK_BRANCH_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '开户行名称',
  `BANK_ACC_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `DEFAULT_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标记',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`USER_BANK_ACC_ID`),
  KEY `FK_t_bank_acc` (`USER_ID`),
  CONSTRAINT `FK_t_bank_acc` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_cd_area` */

DROP TABLE IF EXISTS `t_cd_area`;

CREATE TABLE `t_cd_area` (
  `AREA_ID` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `AREA_CODE` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '区县号',
  `AREA_NAME` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '区县名称',
  `CITY_CODE` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '城市ID',
  PRIMARY KEY (`AREA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2867 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_cd_city` */

DROP TABLE IF EXISTS `t_cd_city`;

CREATE TABLE `t_cd_city` (
  `CITY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CITY_CODE` decimal(10,0) NOT NULL COMMENT '城市号',
  `CITY_NAME` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '城市名称',
  `PROVINCE_CODE` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '省号',
  PRIMARY KEY (`CITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_cd_province` */

DROP TABLE IF EXISTS `t_cd_province`;

CREATE TABLE `t_cd_province` (
  `PROVINCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PROVINCE_CODE` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '省号',
  `PROVINCE_NAME` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '省名称',
  PRIMARY KEY (`PROVINCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_cpt_msg` */

DROP TABLE IF EXISTS `t_cpt_msg`;

CREATE TABLE `t_cpt_msg` (
  `CPT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MSG_ID` int(11) NOT NULL,
  `PHONE_NO` varchar(20) COLLATE utf8_bin NOT NULL,
  `CPT_NO` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `SEND_TIME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVE_TIME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`CPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_group_manage` */

DROP TABLE IF EXISTS `t_group_manage`;

CREATE TABLE `t_group_manage` (
  `GROUP_ID` int(11) NOT NULL COMMENT '群id',
  `GROUP_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '群名称',
  `GROUP_LOGO` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '群LOGO地址',
  `GROUP_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '群组类型',
  `BIND_NO` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '绑定的群号',
  `GROUP_MANAGE_USER_ID` int(11) DEFAULT NULL COMMENT '群管理员的id',
  `REMARK` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '群备注信息',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_group_members` */

DROP TABLE IF EXISTS `t_group_members`;

CREATE TABLE `t_group_members` (
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户id',
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '群id',
  `BAMEMBERS_IDENTITY` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '成员身份类型',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  KEY `FK_t_group_members` (`GROUP_ID`),
  CONSTRAINT `FK_t_group_members` FOREIGN KEY (`GROUP_ID`) REFERENCES `t_group_manage` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_hot_action` */

DROP TABLE IF EXISTS `t_hot_action`;

CREATE TABLE `t_hot_action` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SEQ` decimal(10,0) DEFAULT NULL COMMENT '顺序编号',
  `NAME` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `PIC_PATH` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `URL` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '连接地址',
  `PIC_FILE` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名称 ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AVG_ROW_LENGTH=1;

/*Table structure for table `t_login_log` */

DROP TABLE IF EXISTS `t_login_log`;

CREATE TABLE `t_login_log` (
  `USER_ID` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `LOGIN_STATUS` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `LOGIN_DATE` datetime DEFAULT NULL,
  `LOGIN_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `LOGIN_IP` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `PDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_t_login_log` (`USER_ID`),
  CONSTRAINT `FK_t_login_log` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_CODE` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `MENU_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MENU_ORDER` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `MENU_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `MENU_PRVGFLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `MENU_PARENTCODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `MENU_ACTION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MENU_ICONCLASS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`,`MENU_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `MESSAGE_ID` int(11) NOT NULL COMMENT '消息Id',
  `PUBLISHER_ID` int(11) NOT NULL DEFAULT '0' COMMENT '发布者id',
  `MESSAGE_TYPE` enum('P','G','S') CHARACTER SET utf8 NOT NULL DEFAULT 'S' COMMENT '消息类型',
  `THEME` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '主题',
  `CONTENT` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_message_re` */

DROP TABLE IF EXISTS `t_message_re`;

CREATE TABLE `t_message_re` (
  `RE_ID` int(11) NOT NULL COMMENT '消息接收id',
  `MESSAGE_ID` int(11) NOT NULL COMMENT '消息id',
  `STATUS` enum('0','1') NOT NULL DEFAULT '0' COMMENT '阅读状态0未读1已读',
  `USER_ID` int(11) DEFAULT NULL COMMENT '接收用户id',
  PRIMARY KEY (`RE_ID`),
  KEY `FK_t_message_re` (`MESSAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_msg_info` */

DROP TABLE IF EXISTS `t_msg_info`;

CREATE TABLE `t_msg_info` (
  `MSG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MSG_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `MSG_TYP` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `MSG_COMMENT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`MSG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_msg_log` */

DROP TABLE IF EXISTS `t_msg_log`;

CREATE TABLE `t_msg_log` (
  `MSG_ID` int(11) NOT NULL,
  `MSG_BIZTYP` varchar(20) COLLATE utf8_bin NOT NULL,
  `MSG_TYP` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `MSG_PHONENO` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `MSG_VALUE` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `SEND_TIME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`MSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_opt_log` */

DROP TABLE IF EXISTS `t_opt_log`;

CREATE TABLE `t_opt_log` (
  `CITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `OPT_STATUS` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPT_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPT_TYPE` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `OPT_IP` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`CITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ROLE_REMARK` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `MENU_CODEARRAY` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `MENU_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_secty_ctl` */

DROP TABLE IF EXISTS `t_secty_ctl`;

CREATE TABLE `t_secty_ctl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `LOGIN_PWD` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登陆密码',
  `TRAN_PWD` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '交易密码',
  `USER_STATUS` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  `ERR_COUNT` int(11) DEFAULT NULL COMMENT '错误次数',
  `SECTY_LEVEL` int(11) DEFAULT NULL COMMENT '安全级别',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_site` */

DROP TABLE IF EXISTS `t_site`;

CREATE TABLE `t_site` (
  `SITE_ADDR_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `SITE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地点名称',
  `SITE_ADDR_PROVINCE` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `SITE_ADDR_CITY` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `SITE_ADDR_DISTRICT` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '区',
  `SITE_ADDR_DETAIL` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '详细信息',
  `SITE_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '塑胶木板',
  `SITE_OUT_IN` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '室内室外',
  `SITE_OFFICE_TELEPHONE` int(11) DEFAULT NULL COMMENT '联系电话',
  `SITE_OFFICE_MANAGER` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人管理员',
  `SITE_OFFICE_PHONE` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '座机号码',
  `SITE_DES` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '场地描述',
  `SITE_LNG` double DEFAULT NULL COMMENT '场地坐标经度',
  `SITE_LAT` double DEFAULT NULL COMMENT '场地坐标纬度',
  PRIMARY KEY (`SITE_ADDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='场地表';

/*Table structure for table `t_system_para` */

DROP TABLE IF EXISTS `t_system_para`;

CREATE TABLE `t_system_para` (
  `PARA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARA_CODE` varchar(20) COLLATE utf8_bin NOT NULL,
  `PARA_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PARA_VALUES` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `PARA_TYPE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `PARA_LENGTH` int(11) DEFAULT NULL,
  `REMARK` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PARA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_transion` */

DROP TABLE IF EXISTS `t_transion`;

CREATE TABLE `t_transion` (
  `TRAN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRANSION_SEQ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `TRANSION_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `PAYMENT_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `BANK_SEQ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` decimal(20,4) DEFAULT NULL,
  `CREATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`TRAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL,
  `USER_CDE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `USER_STATUS` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL,
  `USER_TYPE` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL,
  `USER_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USER_NICKNAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USER_HEADIMGURL` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  `PHONE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `MAIL` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `CERTIF_TYPE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `CERTIF_NO` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `USER_CREDIT` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL,
  `RECOD_PHONE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `CREATE_DATE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `UPDATE_DATE` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `WEIXIN_ID` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_user_ext` */

DROP TABLE IF EXISTS `t_user_ext`;

CREATE TABLE `t_user_ext` (
  `REG_DATETIME` date NOT NULL COMMENT '注册时间',
  `REG_TYPE` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '注册类型',
  `USER_ID` int(12) NOT NULL COMMENT '用户表的id',
  `QQ_NO` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `QQ_NICKNAME` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq昵称',
  `WEIXIN_NO` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '微信号码',
  `WEIXIN_NICKNAME` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '微信昵称',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新时间',
  KEY `FK_t_user_ext` (`USER_ID`),
  CONSTRAINT `FK_t_user_ext` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_user_history_data` */

DROP TABLE IF EXISTS `t_user_history_data`;

CREATE TABLE `t_user_history_data` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(20) NOT NULL,
  `PARA_CODE` varchar(10) COLLATE utf8_bin NOT NULL,
  `PARA_NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `PARA_VALUES` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PARA_TYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `PARA_LENGTH` decimal(10,0) DEFAULT NULL,
  `REMARK` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_t_user_history_data` (`USER_ID`),
  CONSTRAINT `FK_t_user_history_data` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户历史记录表,主要记录用户的历史地址,历史电话等';

/*Table structure for table `t_user_prvg` */

DROP TABLE IF EXISTS `t_user_prvg`;

CREATE TABLE `t_user_prvg` (
  `PRVG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PRVG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
