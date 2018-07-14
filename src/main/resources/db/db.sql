/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.22-log : Database - cp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cp`;

/*Table structure for table `cp_data` */

DROP TABLE IF EXISTS `cp_data`;

CREATE TABLE `cp_data` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `cp_date` varchar(10) NOT NULL COMMENT '日期 如:(2018-01-01)',
  `cp_qi_hao` int(3) NOT NULL COMMENT '短期号 如:(120)',
  `wan` int(1) NOT NULL COMMENT '万',
  `qian` int(1) NOT NULL COMMENT '千',
  `bai` int(1) NOT NULL COMMENT '百',
  `shi` int(1) NOT NULL COMMENT '十',
  `ge` int(1) NOT NULL COMMENT '个',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cp_data_result` */

DROP TABLE IF EXISTS `cp_data_result`;

CREATE TABLE `cp_data_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_date` varchar(10) DEFAULT NULL COMMENT '日期',
  `cp_qi_hao` varchar(3) DEFAULT NULL COMMENT '期号',
  `cp_index` enum('1','2','3','4','5') DEFAULT NULL COMMENT '位置',
  `cp_num` enum('0','1','2','3','4','5','6','7','8','9') DEFAULT NULL COMMENT '号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10526 DEFAULT CHARSET=utf8;

/*Table structure for table `cp_data_result_views` */

DROP TABLE IF EXISTS `cp_data_result_views`;

CREATE TABLE `cp_data_result_views` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_index` enum('1','2','3','4','5') DEFAULT NULL COMMENT '角标',
  `end_qi_hao` varchar(3) DEFAULT NULL COMMENT '结束期号',
  `start_qi_hao` varchar(3) DEFAULT NULL COMMENT '开始期号',
  `cru_hao_ma` varchar(1) DEFAULT NULL COMMENT '当前号码',
  `cishu` int(3) DEFAULT NULL COMMENT '次数',
  `yichu` varchar(32) DEFAULT NULL COMMENT '已出号码',
  `weichu` varchar(32) DEFAULT NULL COMMENT '未出号码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '采集时间',
  `create_date` date DEFAULT NULL COMMENT '采集日期',
  `start_qi_date` date DEFAULT NULL COMMENT '开始期号开奖日期',
  `end_qi_date` date DEFAULT NULL COMMENT '结束期号开奖日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6569 DEFAULT CHARSET=utf8;

/*Table structure for table `cp_parity_analysis` */

DROP TABLE IF EXISTS `cp_parity_analysis`;

CREATE TABLE `cp_parity_analysis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_data_id` varchar(64) NOT NULL COMMENT '主表主键(cp_data)',
  `parity` int(1) NOT NULL COMMENT '奇偶(1:奇数 2:偶数)',
  `index_num` enum('1','2','3','4','5') NOT NULL COMMENT '什么位置(1:万 2:千 3:百 4:十 5:个)',
  `batch_num` bigint(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7261 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


DROP TABLE IF EXISTS `cp_data_result_ssc_tj`;

CREATE TABLE `cp_data_result_ssc_tj` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_date` varchar(10) DEFAULT NULL COMMENT '日期',
  `cp_qi_hao` varchar(3) DEFAULT NULL COMMENT '期号',
  `cp_index` enum('1','2','3','4','5') DEFAULT NULL COMMENT '位置',
  `cp_num` enum('0','1','2','3','4','5','6','7','8','9') DEFAULT NULL COMMENT '号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11761 DEFAULT CHARSET=utf8;

/*Table structure for table `cp_data_result_views_ssc_tj` */

DROP TABLE IF EXISTS `cp_data_result_views_ssc_tj`;

CREATE TABLE `cp_data_result_views_ssc_tj` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_index` enum('1','2','3','4','5') DEFAULT NULL COMMENT '角标',
  `end_qi_hao` varchar(3) DEFAULT NULL COMMENT '结束期号',
  `start_qi_hao` varchar(3) DEFAULT NULL COMMENT '开始期号',
  `cru_hao_ma` varchar(1) DEFAULT NULL COMMENT '当前号码',
  `cishu` int(3) DEFAULT NULL COMMENT '次数',
  `yichu` varchar(32) DEFAULT NULL COMMENT '已出号码',
  `weichu` varchar(32) DEFAULT NULL COMMENT '未出号码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '采集时间',
  `create_date` date DEFAULT NULL COMMENT '采集日期',
  `start_qi_date` date DEFAULT NULL COMMENT '开始期号开奖日期',
  `end_qi_date` date DEFAULT NULL COMMENT '结束期号开奖日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7209 DEFAULT CHARSET=utf8;







CREATE DATABASE /*!32312 IF NOT EXISTS*/`cp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cp`;

/*Table structure for table `cp_data_result_ssc_bj` */

DROP TABLE IF EXISTS `cp_data_result_ssc_bj`;

CREATE TABLE `cp_data_result_ssc_bj` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_date` varchar(19) DEFAULT NULL COMMENT '日期',
  `cp_qi_hao` int(11) DEFAULT NULL COMMENT '期号',
  `cp_index` enum('1','2','3','4','5','6','7','8','9','10') DEFAULT NULL COMMENT '位置',
  `cp_num` enum('01','02','03','04','05','06','07','08','09','10') DEFAULT NULL COMMENT '号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1723 DEFAULT CHARSET=utf8;

/*Table structure for table `cp_data_result_views_ssc_bj` */

DROP TABLE IF EXISTS `cp_data_result_views_ssc_bj`;

CREATE TABLE `cp_data_result_views_ssc_bj` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_index` enum('1','2','3','4','5','6','7','8','9','10') DEFAULT NULL COMMENT '角标',
  `start_qi_hao` int(11) DEFAULT NULL COMMENT '开始期号',
  `end_qi_hao` int(11) DEFAULT NULL COMMENT '结束期号',
  `cru_hao_ma` varchar(2) DEFAULT NULL COMMENT '当前号码',
  `cishu` int(3) DEFAULT NULL COMMENT '次数',
  `yichu` varchar(32) DEFAULT NULL COMMENT '已出号码',
  `weichu` varchar(32) DEFAULT NULL COMMENT '未出号码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '采集时间',
  `create_date` date DEFAULT NULL COMMENT '采集日期',
  `start_qi_date` date DEFAULT NULL COMMENT '开始期号开奖日期',
  `end_qi_date` date DEFAULT NULL COMMENT '结束期号开奖日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;