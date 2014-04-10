-- MySQL dump 10.11
--
-- Host: localhost    Database: imaqu
-- ------------------------------------------------------
-- Server version	5.0.51a-24+lenny5

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `T_ANSWER`
--

DROP TABLE IF EXISTS `T_ANSWER`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_ANSWER` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `QUESTION_ID` bigint(20) unsigned NOT NULL,
  `USER_ID` bigint(20) unsigned default NULL,
  `CONTENT` longtext,
  `LEVEL` tinyint(4) default '0' COMMENT '0 stands for NORMAL\n1 stands for BEST by the question author\n2 stands for BEST by intenet voting',
  `CANDIDATE` tinyint(1) default '0',
  `TICKETS` int(11) default '0',
  `BLOCKED` tinyint(1) default '0',
  `BLOCKEDTILL` datetime default NULL,
  `CREATEDAT` datetime default NULL,
  `UPDATEDAT` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `QUESTION_ID` (`QUESTION_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `I_QUESTION_POLL_CREATED` USING BTREE (`CANDIDATE`,`CREATEDAT`)
) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_ANSWER`
--

LOCK TABLES `T_ANSWER` WRITE;
/*!40000 ALTER TABLE `T_ANSWER` DISABLE KEYS */;
INSERT INTO `T_ANSWER` VALUES (1,2,2,'呵呵，你真好玩',0,0,0,0,NULL,'2011-01-30 17:55:13',NULL),(2,3,8,'鸟人就是李刚',1,0,0,0,NULL,'2011-01-31 04:10:55',NULL),(4,1,7,'好玩',0,0,0,0,NULL,'2011-01-31 06:11:32',NULL),(5,15,9,'多用霸王啊',1,0,0,0,NULL,'2011-01-31 08:46:31',NULL),(6,14,9,'二二二人人 ',0,1,1,0,NULL,'2011-01-31 08:46:55',NULL),(7,14,9,'56好556',2,1,1,0,NULL,'2011-01-31 08:47:31',NULL),(8,13,9,' 优异于ii',1,0,0,0,NULL,'2011-01-31 08:47:46',NULL),(9,12,9,' uk7逃课t7k',0,1,0,0,NULL,'2011-01-31 08:48:02',NULL),(10,11,9,'7逃课看7',1,0,0,0,NULL,'2011-01-31 08:48:11',NULL),(11,9,9,'7看7看7 k7k7 7 ',1,0,0,0,NULL,'2011-01-31 08:48:22',NULL),(12,10,9,'当然有我认为我',0,0,0,0,NULL,'2011-01-31 08:48:43',NULL),(13,8,9,'人没人要  ',1,0,0,0,NULL,'2011-01-31 08:48:54',NULL),(14,7,9,' jr6我饿问r6  6',1,0,0,0,NULL,'2011-01-31 08:49:06',NULL),(15,6,9,'离问题结束时间还有13天21小时 提问者：123 ',1,0,0,0,NULL,'2011-01-31 08:49:24',NULL),(16,12,9,'rth t jyu jy uy ',0,1,0,0,NULL,'2011-01-31 09:03:08',NULL),(17,1,9,' l8ii99oo',0,0,0,0,NULL,'2011-01-31 09:03:32',NULL),(18,16,8,'就是不鸟你，就是不鸟你',0,1,0,0,NULL,'2011-01-31 09:57:57',NULL),(19,5,8,'香蕉你个巴拉',0,1,0,0,NULL,'2011-01-31 09:58:38',NULL),(20,10,8,'而更他',0,0,0,0,NULL,'2011-01-31 09:59:01',NULL),(21,10,8,'人员和太阳镜',0,0,0,0,NULL,'2011-01-31 09:59:12',NULL),(22,10,8,'就6人人家6就6 ',0,0,0,0,NULL,'2011-01-31 09:59:17',NULL),(23,10,8,'67就6就6',0,0,0,0,NULL,'2011-01-31 09:59:21',NULL),(24,10,8,'O__O\"…4呀一4',0,0,0,0,NULL,'2011-01-31 09:59:24',NULL),(25,10,8,'前沿4无语4 业务员 ',0,0,0,0,NULL,'2011-01-31 09:59:28',NULL),(26,10,8,'5一 ',0,0,0,0,NULL,'2011-01-31 09:59:35',NULL),(27,10,8,'4一4我也  ',0,0,0,0,NULL,'2011-01-31 09:59:38',NULL),(28,10,8,'完一5问5一 ',0,0,0,0,NULL,'2011-01-31 09:59:41',NULL),(29,10,8,'4为一4一完4 我',0,0,0,0,NULL,'2011-01-31 09:59:44',NULL),(30,10,8,'问 一4我',0,0,0,0,NULL,'2011-01-31 09:59:46',NULL),(31,10,8,'4无语4 ① ',0,0,0,0,NULL,'2011-01-31 09:59:49',NULL),(32,10,8,'5我4一问问① 5 ',0,0,0,0,NULL,'2011-01-31 09:59:55',NULL),(33,10,8,'4我一5 ',0,0,0,0,NULL,'2011-01-31 09:59:58',NULL),(34,10,8,'3认同4我5',0,0,0,0,NULL,'2011-01-31 10:00:12',NULL),(35,10,8,'其他2 ',0,0,0,0,NULL,'2011-01-31 10:00:16',NULL),(36,10,8,'45 6 77就6 ',0,0,0,0,NULL,'2011-01-31 10:00:20',NULL),(37,10,8,'67 67 6 ',0,0,0,0,NULL,'2011-01-31 10:00:27',NULL),(38,10,8,'是56',0,0,0,0,NULL,'2011-01-31 10:00:33',NULL),(39,10,8,'为①一5 ',0,0,0,0,NULL,'2011-01-31 10:00:37',NULL),(40,10,8,'咕~~(╯﹏╰)b一6 ',0,0,0,0,NULL,'2011-01-31 10:00:45',NULL),(41,10,8,'好6 6 ',0,0,0,0,NULL,'2011-01-31 10:00:48',NULL),(42,10,8,'6 他6 ',0,0,0,0,NULL,'2011-01-31 10:00:57',NULL),(43,10,8,'同行业6人 ',0,0,0,0,NULL,'2011-01-31 10:00:59',NULL),(44,10,8,'好饿坦然他  ',0,0,0,0,NULL,'2011-01-31 10:01:02',NULL),(45,10,8,'而后5 \\(^o^)/~人好',0,0,0,0,NULL,'2011-01-31 10:01:13',NULL),(46,10,8,'而后 儿童好',0,0,0,0,NULL,'2011-01-31 10:01:16',NULL),(47,10,8,'儿童歌认同\\(^o^)/~ 6他',0,0,0,0,NULL,'2011-01-31 10:01:19',NULL),(48,10,8,'热人他 饿hyr6 ',0,1,0,0,NULL,'2011-01-31 10:01:24',NULL),(49,10,8,'认同和坦然好',0,1,1,0,NULL,'2011-01-31 10:01:27',NULL),(50,10,8,'认同和一就',0,1,0,0,NULL,'2011-01-31 10:01:30',NULL),(51,10,8,'ui哭',0,1,0,0,NULL,'2011-01-31 10:01:53',NULL),(54,10,8,'ui7kikI',2,1,1,0,NULL,'2011-01-31 10:02:03',NULL),(57,16,9,'刚好那天一',0,0,0,0,NULL,'2011-01-31 10:11:29',NULL),(58,16,9,'于一一',0,1,0,0,NULL,'2011-01-31 10:11:36',NULL),(59,16,9,'于于uu',0,0,0,0,NULL,'2011-01-31 10:11:39',NULL),(60,16,9,'于于u',0,1,0,0,NULL,'2011-01-31 10:11:42',NULL),(61,16,9,'饿 呀一4 4  ',0,0,0,0,NULL,'2011-01-31 10:11:46',NULL),(62,23,8,'太阳太阳 一',0,0,0,0,NULL,'2011-01-31 10:58:44',NULL),(63,22,8,'饿一他 于 uy ',0,0,0,0,NULL,'2011-01-31 10:58:58',NULL),(64,21,8,'他他他拒绝u看看',0,0,0,0,NULL,'2011-01-31 10:59:12',NULL),(65,20,8,'太阳巨款i7k7看7 ',0,0,0,0,NULL,'2011-01-31 10:59:21',NULL),(66,19,8,'utk一看看看看',0,0,0,0,NULL,'2011-01-31 10:59:32',NULL),(67,18,8,'u看7ikiki ',0,0,0,0,NULL,'2011-01-31 10:59:42',NULL),(68,17,8,'他k78k78 8 8咕~~(╯﹏╰)b一',0,0,0,0,NULL,'2011-01-31 10:59:55',NULL),(69,23,9,'感言6就6就投影机tj6j他今天',0,0,0,0,NULL,'2011-01-31 11:00:18',NULL),(70,22,9,'犹太教u拒绝u ',0,1,0,0,NULL,'2011-01-31 11:00:26',NULL),(71,21,9,'uu看看看 就认识一',0,0,0,0,NULL,'2011-01-31 11:00:40',NULL),(72,20,9,'饿我(⊙o⊙)嗯 7据苦苦',0,0,0,0,NULL,'2011-01-31 11:00:51',NULL),(73,19,9,'t66767 67  76 ',0,0,0,0,NULL,'2011-01-31 11:01:01',NULL),(74,18,9,'  我ej66 ',0,0,0,0,NULL,'2011-01-31 11:01:11',NULL),(75,17,9,'人呜呜7u76',0,0,0,0,NULL,'2011-01-31 11:01:20',NULL),(76,5,9,'8i98了986人',0,1,0,0,NULL,'2011-01-31 11:01:30',NULL),(77,28,7,'4他45 5 ',0,0,0,0,NULL,'2011-02-01 05:38:52',NULL),(78,28,7,'erg ',0,0,0,0,NULL,'2011-02-01 09:37:33',NULL),(79,28,7,'g e ',0,0,0,0,NULL,'2011-02-01 09:37:39',NULL),(80,32,7,'e t rt ',0,0,0,0,NULL,'2011-02-01 09:37:57',NULL),(81,28,1,'怎么不能回答',0,0,0,0,NULL,'2011-02-01 18:02:42',NULL),(82,27,1,'你去哪儿',0,0,0,0,NULL,'2011-02-01 18:12:38',NULL),(83,31,7,' 二人认同他',0,0,0,0,NULL,'2011-02-02 04:07:09',NULL),(84,28,9,'w ger rt ',0,0,0,0,NULL,'2011-02-02 12:11:46',NULL),(85,29,7,'yu tui ',0,0,0,0,NULL,'2011-02-02 12:12:28',NULL),(86,30,7,'fgbf ',0,0,0,0,NULL,'2011-02-04 05:40:47',NULL),(87,33,7,'t br ',0,0,0,0,NULL,'2011-02-06 10:18:22',NULL),(88,28,7,' 好5他',0,0,0,0,NULL,'2011-02-07 04:29:55',NULL),(89,35,8,'r ',0,0,0,0,NULL,'2011-02-08 03:20:39',NULL),(90,34,1,'test',0,0,0,0,NULL,'2011-02-09 14:08:05',NULL),(91,32,1,'test again',0,0,0,0,NULL,'2011-02-09 14:08:25',NULL),(92,36,8,'rg rtg ',0,0,0,0,NULL,'2011-02-09 16:02:16',NULL);
/*!40000 ALTER TABLE `T_ANSWER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_CATEGORY`
--

DROP TABLE IF EXISTS `T_CATEGORY`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_CATEGORY` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `PARENT_ID` bigint(20) unsigned NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `DESCRIPTION` tinytext,
  `D` bit(1) default NULL COMMENT 'if this is the default level\n1 stands for yes\n0 stands for no',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_CATEGORY`
--

LOCK TABLES `T_CATEGORY` WRITE;
/*!40000 ALTER TABLE `T_CATEGORY` DISABLE KEYS */;
INSERT INTO `T_CATEGORY` VALUES (1,0,'无敌','',''),(2,0,'有点2','','\0'),(3,0,'白日梦','','\0'),(4,0,'奇思妙想','','\0'),(5,0,'欠揍','','\0'),(6,0,'变态','','\0'),(7,0,'很傻很天真','','\0'),(8,0,'test1','haha','\0'),(9,8,'test2','haha','\0'),(10,9,'test3','haha','\0');
/*!40000 ALTER TABLE `T_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_LEVEL`
--

DROP TABLE IF EXISTS `T_LEVEL`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_LEVEL` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `SERIALNAME` varchar(30) default NULL,
  `D` bit(1) default NULL COMMENT 'if this is the default level\n1 stands for yes\n0 stands for no',
  `DESCRIPTION` tinytext,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_LEVEL`
--

LOCK TABLES `T_LEVEL` WRITE;
/*!40000 ALTER TABLE `T_LEVEL` DISABLE KEYS */;
INSERT INTO `T_LEVEL` VALUES (1,'默认','','');
/*!40000 ALTER TABLE `T_LEVEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_QUESTION`
--

DROP TABLE IF EXISTS `T_QUESTION`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_QUESTION` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `CATEGORY_ID` bigint(20) unsigned default NULL,
  `USER_ID` bigint(20) unsigned default NULL,
  `TITLE` varchar(140) NOT NULL,
  `CONTENT` longtext,
  `SUPPLYMENT` text,
  `REWARD` mediumint(8) unsigned default '0',
  `STATUS` tinyint(4) default '0' COMMENT '0 represents OPEN\n1 stands for CLOSED\n2 stands for IN POOL',
  `DEADLINE` datetime default NULL COMMENT 'This is used to set the deadline for open status',
  `RECOMMENDED` tinyint(1) default '0',
  `VIEWEDCOUNT` int(10) unsigned default NULL,
  `DIGCOUNT` mediumint(8) unsigned default NULL,
  `BLOCKED` tinyint(1) default '0',
  `BLOCKEDTILL` datetime default NULL,
  `CREATEDAT` datetime default NULL,
  `UPDATEDAT` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `I_BLOCKED` USING BTREE (`BLOCKED`),
  KEY `I_REWARD` (`REWARD`),
  KEY `I_STATUS` (`STATUS`),
  KEY `I_RECOMMENDED` (`RECOMMENDED`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_QUESTION`
--

LOCK TABLES `T_QUESTION` WRITE;
/*!40000 ALTER TABLE `T_QUESTION` DISABLE KEYS */;
INSERT INTO `T_QUESTION` VALUES (1,4,1,'asdfasdf啊撒旦发打发','阿斯顿发生大法是否地方',NULL,5,0,'2011-02-13 11:35:31',0,0,0,0,NULL,'2011-01-30 11:35:31',NULL),(2,4,6,'注册过程正确，我可以提问么？','不能提问',NULL,5,0,'2011-02-13 17:54:15',0,0,0,0,NULL,'2011-01-30 17:54:15',NULL),(3,2,7,'鸟人是什么？','',NULL,5,1,'2011-02-14 04:03:52',0,0,0,0,NULL,'2011-01-31 04:03:52',NULL),(4,1,7,'3个沃恩 干扰人','','额外人个人',0,1,'2011-02-14 06:14:01',0,0,0,0,NULL,'2011-01-31 06:14:01',NULL),(5,1,7,'太阳u一','',NULL,0,2,'2011-02-05 11:03:31',0,0,0,0,NULL,'2011-01-31 06:15:14',NULL),(6,1,7,'托付谜语木uiui','',NULL,0,1,'2011-02-14 06:16:25',0,0,0,0,NULL,'2011-01-31 06:16:25',NULL),(7,7,7,'u， ，u，     u，uu','',NULL,0,1,'2011-02-14 06:16:38',0,0,0,0,NULL,'2011-01-31 06:16:38',NULL),(8,6,7,'速度好认同和一太阳与预警已经一样于','',NULL,0,1,'2011-02-14 06:16:54',0,0,0,0,NULL,'2011-01-31 06:16:54',NULL),(9,5,7,'回家，广告画面个木瓜uu据u','',NULL,5,1,'2011-02-14 06:17:51',0,0,0,0,NULL,'2011-01-31 06:17:51',NULL),(10,7,7,'一天飞核反应一','',NULL,0,1,'2011-02-05 10:03:50',0,0,0,0,NULL,'2011-01-31 06:18:19',NULL),(11,3,7,'不那么部门见面会，好，回家，好，好，','',NULL,0,1,'2011-02-14 06:18:31',0,0,0,0,NULL,'2011-01-31 06:18:31',NULL),(12,2,7,'回家美好美好，回家，以后，好，，','',NULL,0,2,'2011-02-05 09:17:48',0,0,0,0,NULL,'2011-01-31 06:19:32',NULL),(13,6,7,'于亏uiioioio哦哦oiliili','',NULL,0,1,'2011-02-14 06:20:18',0,0,0,0,NULL,'2011-01-31 06:20:18',NULL),(14,7,7,'i，iii     uuuuuu','',NULL,0,1,'2011-02-05 09:00:11',0,0,0,0,NULL,'2011-01-31 06:21:34',NULL),(15,10,7,'女人怎样才能长胡子？？？','',NULL,0,1,'2011-02-14 08:45:29',0,0,0,0,NULL,'2011-01-31 08:45:29',NULL),(16,6,7,'新春佳节到，我把福来祝，好运天天交，生活步步高，彩票期期中，打牌次次赢，口味顿顿好，若敢把我忘，小心','、有阳光照耀的地方就有我默默的祝福，当月光洒向地球的时候就有我默默的祈祷，当流星划过的刹那我许了个愿：祝你平安健康，新年快乐！\r\n\r\n  82、新春佳节到，我把福来祝，好运天天交，生活步步高，彩票期期中，打牌次次赢，口味顿顿好，若敢把我忘，小心挨棍棒。\r\n\r\n  83、痛苦最好是别人的，快乐才是自己的；麻烦将是暂时的，朋友总是永恒的；爱情是用心经营的，世界上没有什么大不了的。新年快乐！千里试问平安否？且把思念遥相寄。绵绵爱意与关怀，浓浓情意与祝福。祝你祝你新年新年快快乐乐！\r\n\r\n  84、每年的这个时候，祝福就会象海洋涌向你，希望我的祝福象一叶轻舟，载你乘风破浪，到达成功的彼岸！2011，快乐随你！\r\n',NULL,0,2,'2011-02-05 10:55:42',0,0,0,0,NULL,'2011-01-31 09:23:00',NULL),(17,6,7,'g r r t  ','',NULL,0,0,'2011-02-14 10:56:14',0,0,0,0,NULL,'2011-01-31 10:56:14',NULL),(18,5,7,'6y 6j 6 6 6 j6 j6  ju j6 j j j j ju  7 ','',NULL,0,0,'2011-02-14 10:56:31',0,0,0,0,NULL,'2011-01-31 10:56:31',NULL),(19,3,7,'67j 6 6 6  ','',NULL,0,0,'2011-02-14 10:56:46',0,0,0,0,NULL,'2011-01-31 10:56:46',NULL),(20,3,7,'35 w y6 6 l 9p;0\'0-\'','',NULL,0,0,'2011-02-14 10:57:00',0,0,0,0,NULL,'2011-01-31 10:57:00',NULL),(21,7,7,'e56 5 6j6j6 j6 ','',NULL,0,0,'2011-02-14 10:57:16',0,0,0,0,NULL,'2011-01-31 10:57:16',NULL),(22,6,7,'w4y 56 76 67 k8kki8k8 任我游465 ','',NULL,0,2,'2011-02-05 12:00:24',0,0,0,0,NULL,'2011-01-31 10:57:30',NULL),(23,5,7,'漫画家媒体→教育局犹太教 一','','投影机就',0,1,'2011-02-14 10:57:51',0,0,0,0,NULL,'2011-01-31 10:57:51',NULL),(24,5,7,'3他45 6 ','',NULL,0,0,'2011-02-14 11:04:37',0,0,0,0,NULL,'2011-01-31 11:04:37',NULL),(25,4,7,'咕~~(╯﹏╰)b 65 5 ','6 5 6 就就6拒绝',NULL,0,0,'2011-02-14 11:05:03',0,0,0,0,NULL,'2011-01-31 11:05:03',NULL),(26,6,7,'而更我我我 ','',NULL,0,0,'2011-02-14 11:05:15',0,0,0,0,NULL,'2011-01-31 11:05:15',NULL),(27,2,7,'我去去 去  ','',NULL,0,0,'2011-02-14 11:05:26',0,0,0,0,NULL,'2011-01-31 11:05:26',NULL),(28,6,8,'饿认同人白天太阳','一特他他他 ',NULL,50,0,'2011-02-15 05:37:57',0,0,0,0,NULL,'2011-02-01 05:37:57',NULL),(29,5,9,' gr 6 6  7 ','56 u6  ',NULL,20,0,'2011-02-15 07:22:21',0,0,0,0,NULL,'2011-02-01 07:22:21',NULL),(30,3,9,'挺好太阳一一语','',NULL,10,0,'2011-02-15 07:22:56',0,0,0,0,NULL,'2011-02-01 07:22:56',NULL),(31,3,8,'热工 认同他','人人太阳 ',NULL,20,0,'2011-02-15 07:24:46',0,0,0,0,NULL,'2011-02-01 07:24:46',NULL),(32,4,8,'太阳u语uiu iki','',NULL,20,0,'2011-02-15 07:24:59',0,0,0,0,NULL,'2011-02-01 07:24:59',NULL),(33,2,8,'儿童人人人','',NULL,5,0,'2011-02-15 07:27:14',0,0,0,0,NULL,'2011-02-01 07:27:14',NULL),(34,4,7,'为什么答案不能立即显示出来呢？','',NULL,5,0,'2011-02-21 04:34:30',0,0,0,0,NULL,'2011-02-07 04:34:30',NULL),(35,6,7,'w ge r r ty y ','e gt ',NULL,0,0,'2011-02-22 03:20:23',0,0,0,0,NULL,'2011-02-08 03:20:23',NULL),(36,4,7,'dfth ty yt u','re g',NULL,5,0,'2011-02-23 16:01:33',0,0,0,0,NULL,'2011-02-09 16:01:33',NULL);
/*!40000 ALTER TABLE `T_QUESTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_REWARD`
--

DROP TABLE IF EXISTS `T_REWARD`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_REWARD` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `ACTION` varchar(30) NOT NULL,
  `REWARD` mediumint(8) unsigned default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_REWARD`
--

LOCK TABLES `T_REWARD` WRITE;
/*!40000 ALTER TABLE `T_REWARD` DISABLE KEYS */;
/*!40000 ALTER TABLE `T_REWARD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ROLE`
--

DROP TABLE IF EXISTS `T_ROLE`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_ROLE` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `NAME` varchar(60) NOT NULL,
  `DESCRIPTION` tinytext,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_ROLE`
--

LOCK TABLES `T_ROLE` WRITE;
/*!40000 ALTER TABLE `T_ROLE` DISABLE KEYS */;
INSERT INTO `T_ROLE` VALUES (1,'ROLE_ADMIN',''),(2,'ROLE_USER','');
/*!40000 ALTER TABLE `T_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_SUBLEVEL`
--

DROP TABLE IF EXISTS `T_SUBLEVEL`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_SUBLEVEL` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `LEVEL_ID` bigint(20) unsigned NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `MINSCORE` mediumint(8) unsigned default NULL,
  `MAXSCORE` mediumint(8) unsigned default NULL,
  PRIMARY KEY  (`ID`),
  KEY `LEVEL_ID` (`LEVEL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_SUBLEVEL`
--

LOCK TABLES `T_SUBLEVEL` WRITE;
/*!40000 ALTER TABLE `T_SUBLEVEL` DISABLE KEYS */;
INSERT INTO `T_SUBLEVEL` VALUES (1,1,'小菜鸟',0,100),(2,1,'大菜鸟',101,200),(3,1,'老菜鸟',201,300),(4,1,'小虾',301,500),(5,1,'大虾',501,1000),(6,1,'豪虾',1001,2000),(7,1,'怪才',2001,5000),(8,1,'大神',5001,10000),(9,1,'火星来客',10001,50000),(10,1,'想象帝',50001,16777215);
/*!40000 ALTER TABLE `T_SUBLEVEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_USER`
--

DROP TABLE IF EXISTS `T_USER`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_USER` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `ROLE_ID` bigint(20) unsigned NOT NULL,
  `NICKNAME` varchar(60) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `GENDER` bit(1) NOT NULL COMMENT '1 stands for male\n0 stands for female',
  `SCORE` mediumint(8) unsigned default NULL,
  `LEVEL_ID` bigint(20) unsigned default NULL,
  `BLOCKED` tinyint(1) default '0',
  `BLOCKEDTILL` datetime default NULL,
  `CREATEDAT` datetime default NULL,
  `LASTLOGIN` datetime default NULL,
  `LASTIP` varchar(60) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NICKNAME` (`NICKNAME`),
  KEY `LEVEL_ID` (`LEVEL_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `I_NICKNAME` USING BTREE (`NICKNAME`),
  KEY `I_EMAIL` (`EMAIL`),
  KEY `I_SCORE` (`SCORE`),
  KEY `I_BLOCKED` (`BLOCKED`),
  KEY `I_CREATEDAT` (`CREATEDAT`),
  KEY `I_LASTLOGIN` (`LASTIP`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_USER`
--

LOCK TABLES `T_USER` WRITE;
/*!40000 ALTER TABLE `T_USER` DISABLE KEYS */;
INSERT INTO `T_USER` VALUES (1,1,'phinux','aa@aa.com','ef4695d76afadfd1eaab12ee3fed45c8','',23,1,0,'2011-01-30 11:27:38','2011-01-30 11:27:38','2011-02-09 14:07:55','114.222.67.244'),(2,2,'test','aa@aa.com','778dd89b69ed171c91b57fbd3830d90e','\0',22,NULL,0,'2011-01-30 11:27:38','2011-01-30 11:27:38','2011-01-30 18:35:17','121.237.50.198'),(3,2,'stanley','aa@a.com','60178a251abb063a36c162dca9f0cdc3','',0,NULL,0,NULL,'2011-01-30 11:36:23',NULL,NULL),(4,2,'光着屁股上街','87163297@qq.com','d5430441323a4798855c90e60237b829','',0,NULL,0,NULL,'2011-01-30 13:01:16',NULL,NULL),(5,2,'testbb','aa@aa.com','486dd2eb081ff8617ea01e3b8ed344b0','',0,NULL,0,NULL,'2011-01-30 17:15:08',NULL,NULL),(6,2,'stanleyz','aa@aa.com','090fd48d0375d93c63fefc6ee3ea9c47','',5,NULL,0,NULL,'2011-01-30 17:53:21','2011-01-30 17:53:21','121.237.55.180'),(7,2,'123','shendawei@sohu.com','ec9911a53dc9a443918b39656fa9518a','',27,NULL,0,NULL,'2011-01-31 03:50:48','2011-02-09 16:01:05','218.1.148.132'),(8,2,'456','87163297@qq.com','19175c2da03044897628eaf96c260703','',10,NULL,0,NULL,'2011-01-31 04:10:31','2011-02-09 16:02:06','218.1.148.132'),(9,2,'789','shendawei@sohu.com','f863dcefbd7e1409eddccfb0e749c009','',10,NULL,0,NULL,'2011-01-31 08:32:39','2011-02-02 12:11:36','180.126.93.239'),(10,2,'胡说八道','shendawei@sohu.com','9404c08a08a831ccf133a03c7c110a00','\0',11,NULL,0,NULL,'2011-01-31 10:07:34','2011-01-31 10:07:34','180.126.86.135');
/*!40000 ALTER TABLE `T_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_USER_VOTING`
--

DROP TABLE IF EXISTS `T_USER_VOTING`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `T_USER_VOTING` (
  `USER_ID` bigint(20) unsigned NOT NULL,
  `QUESTION_ID` bigint(20) unsigned NOT NULL,
  KEY `QUESTION_ID` (`QUESTION_ID`),
  KEY `USER_ID` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `T_USER_VOTING`
--

LOCK TABLES `T_USER_VOTING` WRITE;
/*!40000 ALTER TABLE `T_USER_VOTING` DISABLE KEYS */;
INSERT INTO `T_USER_VOTING` VALUES (7,14),(9,14),(7,10),(10,10);
/*!40000 ALTER TABLE `T_USER_VOTING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`series`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-02-09 13:31:18
