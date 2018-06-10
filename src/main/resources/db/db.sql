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

/*Data for the table `cp_data` */

insert  into `cp_data`(`id`,`cp_date`,`cp_qi_hao`,`wan`,`qian`,`bai`,`shi`,`ge`,`create_time`) values ('54d28ea13556493a92aad0a68f1c0da8','2018-06-10',117,2,1,8,9,9,'2018-06-10 23:46:43'),('848803c4facb49309da4050130ea8207','2018-06-10',118,1,0,0,0,2,'2018-06-10 23:52:25'),('87b41772bab5492a8306408153b78367','2018-06-10',116,7,3,3,9,2,'2018-06-10 23:41:20'),('994561a4ad754d4d98995327bb7e667e','2018-06-10',115,0,0,6,0,6,'2018-06-10 23:40:20');

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
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

/*Data for the table `cp_parity_analysis` */

insert  into `cp_parity_analysis`(`id`,`cp_data_id`,`parity`,`index_num`,`batch_num`,`create_time`) values (141,'994561a4ad754d4d98995327bb7e667e',0,'1',1,'2018-06-10 23:40:21'),(142,'994561a4ad754d4d98995327bb7e667e',0,'2',1,'2018-06-10 23:40:21'),(143,'994561a4ad754d4d98995327bb7e667e',0,'3',1,'2018-06-10 23:40:21'),(144,'994561a4ad754d4d98995327bb7e667e',0,'4',1,'2018-06-10 23:40:21'),(145,'994561a4ad754d4d98995327bb7e667e',0,'5',1,'2018-06-10 23:40:21'),(146,'87b41772bab5492a8306408153b78367',1,'1',2,'2018-06-10 23:41:20'),(147,'87b41772bab5492a8306408153b78367',1,'2',2,'2018-06-10 23:41:20'),(148,'87b41772bab5492a8306408153b78367',1,'3',2,'2018-06-10 23:41:20'),(149,'87b41772bab5492a8306408153b78367',1,'4',2,'2018-06-10 23:41:20'),(150,'87b41772bab5492a8306408153b78367',0,'5',1,'2018-06-10 23:41:20'),(151,'54d28ea13556493a92aad0a68f1c0da8',0,'1',3,'2018-06-10 23:47:33'),(152,'54d28ea13556493a92aad0a68f1c0da8',1,'2',2,'2018-06-10 23:47:33'),(153,'54d28ea13556493a92aad0a68f1c0da8',0,'3',3,'2018-06-10 23:47:33'),(154,'54d28ea13556493a92aad0a68f1c0da8',1,'4',2,'2018-06-10 23:47:33'),(155,'54d28ea13556493a92aad0a68f1c0da8',1,'5',2,'2018-06-10 23:47:33'),(156,'848803c4facb49309da4050130ea8207',1,'1',4,'2018-06-10 23:53:02'),(157,'848803c4facb49309da4050130ea8207',0,'2',3,'2018-06-10 23:53:02'),(158,'848803c4facb49309da4050130ea8207',0,'3',3,'2018-06-10 23:53:02'),(159,'848803c4facb49309da4050130ea8207',0,'4',3,'2018-06-10 23:53:02'),(160,'848803c4facb49309da4050130ea8207',0,'5',3,'2018-06-10 23:53:02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
