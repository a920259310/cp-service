/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - cp
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_date` date NOT NULL COMMENT '长期号 如:(20180101-120)',
  `cp_qi_hao` int(3) NOT NULL COMMENT '短期号 如:(120)',
  `wan` int(1) NOT NULL COMMENT '万',
  `qian` int(1) NOT NULL COMMENT '千',
  `bai` int(1) NOT NULL COMMENT '百',
  `shi` int(1) NOT NULL COMMENT '十',
  `ge` int(1) NOT NULL COMMENT '个',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `cp_parity_analysis` */

DROP TABLE IF EXISTS `cp_parity_analysis`;

CREATE TABLE `cp_parity_analysis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cp_data_id` bigint(20) NOT NULL COMMENT '主表主键(cp_data)',
  `parity` int(1) NOT NULL COMMENT '奇偶(1:奇数 2:偶数)',
  `index_num` enum('1','2','3','4','5') NOT NULL COMMENT '什么位置(1:万 2:千 3:百 4:十 5:个)',
  `batch_num` bigint(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/* Trigger structure for table `cp_data` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `parity_analysis` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `parity_analysis` AFTER INSERT ON `cp_data` FOR EACH ROW BEGIN
	-- DECLARE s1 VARCHAR(40)character set utf8;
	-- DECLARE s2 VARCHAR(20) character set utf8;
	
	-- SET s2 = " is created";
	-- SET s1 = CONCAT(NEW.name,s2);
	DECLARE parity int(1);
	set parity = new.ge % 2;
	INSERT INTO cp.cp_parity_analysis(cp_data_id,parity,index_num,batch_num) VALUES(new.id,parity,5,1);
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
