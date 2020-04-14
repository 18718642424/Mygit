/*
Navicat MySQL Data Transfer

Source Server         : qianfeng
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : qianfeng

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2019-12-28 17:03:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auction`
-- ----------------------------
DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction` (
  `AUCTIONID` int(6) NOT NULL AUTO_INCREMENT COMMENT '拍卖品编号',
  `AUCTIONNAME` varchar(50) DEFAULT NULL COMMENT '拍卖品名称',
  `AUCTIONSTARTPRICE` decimal(9,2) DEFAULT NULL COMMENT '拍卖品起拍价',
  `AUCTIONUPSET` decimal(9,2) DEFAULT NULL COMMENT '拍卖品底价',
  `AUCTIONSTARTTIME` datetime DEFAULT NULL COMMENT '拍卖品拍卖开始时间',
  `AUCTIONENDTIME` datetime DEFAULT NULL COMMENT '拍卖品拍卖结束时间',
  `AUCTIONDESC` text COMMENT '拍卖品描述',
  `AUCTIONPICPATH` text COMMENT '拍卖品的图片路径',
  `CREATETIME` datetime DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '0  為確認買主的拍賣品 1是已經確認買主的拍賣品 2是代表拍賣到期但是沒有買主',
  PRIMARY KEY (`AUCTIONID`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8 COMMENT='拍卖品';

-- ----------------------------
-- Records of auction
-- ----------------------------
INSERT INTO `auction` VALUES ('7', '美国油画', '8888.00', '52200.00', '2010-05-05 00:30:00', '2019-12-29 20:41:10', '朝鲜艺术家最高级别是“金日成奖”，“金日成奖”获得者一般在朝鲜国内是家喻户晓，人人皆知的。朝鲜艺术家授勋等级依次是：“金日成奖”、“劳动英雄奖”、“人民艺术家”、“功勋艺术家”。在朝鲜“金日成奖”获得者寥寥无几。', '20191008111647965.jpg', '2019-06-26 20:20:42', null, '1');
INSERT INTO `auction` VALUES ('254', '火热火热', '1111.00', '0.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', '123123123123123', null, '2019-12-25 16:47:33', '2019-12-25 16:47:33', '0');
INSERT INTO `auction` VALUES ('255', '火热火热oo', '0.00', '11111.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', 'h nbmnbmnbmnbmnbmnn', null, '2019-12-25 16:59:04', '2019-12-25 16:59:04', '0');
INSERT INTO `auction` VALUES ('256', '火热火热', '123123.00', '11111.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', 'asdasdasdasd', null, '2019-12-25 17:07:18', '2019-12-25 17:07:18', '0');
INSERT INTO `auction` VALUES ('257', '火热火热', '123123.00', '11111.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', 'asdasdasdasd', null, '2019-12-25 17:07:54', '2019-12-25 17:07:54', '0');
INSERT INTO `auction` VALUES ('258', '美国油画', '11111.00', '11111.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', 'HIHUHUUHIUHIUHUHUHUUHHUU', null, '2019-12-26 08:42:35', '2019-12-26 08:42:35', '0');
INSERT INTO `auction` VALUES ('261', '美国888888', '1234.00', '11111.00', '2010-05-05 12:30:00', '2010-05-05 12:30:00', 'HIHUHUUHIUHIUHUHUHUUHHUU', '20191226090651310.png', '2019-12-26 08:50:14', '2019-12-26 09:06:51', '0');

-- ----------------------------
-- Table structure for `auctionrecord`
-- ----------------------------
DROP TABLE IF EXISTS `auctionrecord`;
CREATE TABLE `auctionrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL,
  `AUCTIONID` int(11) DEFAULT NULL,
  `AUCTIONTIME` date DEFAULT NULL,
  `AUCTIONPRICE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_auctionis` (`AUCTIONID`),
  KEY `at_rd_us_fk` (`USERID`),
  CONSTRAINT `at_rd_ac_fk` FOREIGN KEY (`AUCTIONID`) REFERENCES `auction` (`AUCTIONID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `at_rd_us_fk` FOREIGN KEY (`USERID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auctionrecord
-- ----------------------------
INSERT INTO `auctionrecord` VALUES ('1', '3', '7', '2019-12-21', '10000');
INSERT INTO `auctionrecord` VALUES ('2', '10', '7', '2019-12-21', '13000');
INSERT INTO `auctionrecord` VALUES ('3', '3', '7', '2019-12-23', '14000');
INSERT INTO `auctionrecord` VALUES ('4', '3', '7', '2019-12-23', '15000');
INSERT INTO `auctionrecord` VALUES ('5', '3', '7', '2019-12-26', '16000');
INSERT INTO `auctionrecord` VALUES ('6', '3', '261', '2019-12-26', '2222');
INSERT INTO `auctionrecord` VALUES ('7', '3', '261', '2019-12-26', '2222');
INSERT INTO `auctionrecord` VALUES ('8', '3', '7', '2019-12-26', '18000');
INSERT INTO `auctionrecord` VALUES ('9', '3', '7', '2019-12-26', '19000');
INSERT INTO `auctionrecord` VALUES ('10', '3', '7', '2019-12-26', '20000');
INSERT INTO `auctionrecord` VALUES ('11', '3', '7', '2019-12-26', '21000');
INSERT INTO `auctionrecord` VALUES ('12', '3', '7', '2019-12-27', '1000');
INSERT INTO `auctionrecord` VALUES ('13', '3', '7', '2019-12-27', '31111');

-- ----------------------------
-- Table structure for `auction_order`
-- ----------------------------
DROP TABLE IF EXISTS `auction_order`;
CREATE TABLE `auction_order` (
  `orderid` varchar(255) NOT NULL DEFAULT '',
  `auctionid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1' COMMENT '0 为失败的订单  1 为成功的订单',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auction_order
-- ----------------------------
INSERT INTO `auction_order` VALUES ('1', '7', '3', '1000', '2019-12-27 08:49:31', '1');
INSERT INTO `auction_order` VALUES ('168d7db9-ef7f-4656-bf6b-a4b62654a60c3771', '7', '3', '20000', '2019-12-26 09:32:33', '1');
INSERT INTO `auction_order` VALUES ('397d47e9-532f-489e-98ac-3ad14b31dfcd145', '7', '3', '19000', '2019-12-26 09:30:12', '1');
INSERT INTO `auction_order` VALUES ('730a1861-7479-4bbf-9977-0241d3c5d92b374', '7', '3', '18000', '2019-12-26 09:29:09', '1');
INSERT INTO `auction_order` VALUES ('8a104f6b-16d3-4857-b480-5cedce2b8a085742', '7', '3', '15000', '2019-12-23 16:23:36', '1');
INSERT INTO `auction_order` VALUES ('8deb2ea9-368c-43a9-a2a7-16046dd71b1c272', '7', '3', '31111', '2019-12-27 09:35:30', '1');
INSERT INTO `auction_order` VALUES ('9f1f773f-20e9-4343-b7d8-1041c36bec314048', '7', '3', '16000', '2019-12-26 09:12:48', '1');
INSERT INTO `auction_order` VALUES ('c91bb284-3816-441a-a17f-223ace126dd65888', '7', '3', '21000', '2019-12-26 20:39:22', '1');
INSERT INTO `auction_order` VALUES ('e26466b8-ff80-4611-b00f-7d687ba75cfe1702', '261', '3', '2222', '2019-12-26 09:14:52', '1');

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `validatecode` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', '15177732332', '2980', '2019-12-28 17:02:32');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(11) DEFAULT '1' COMMENT '0代表数据被删除 1代表数据没有被删除',
  `userIsAdmin` int(11) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'test', '123456', '2019-12-10 15:11:22', '2019-12-10 15:11:22', '1', '0', '2732879744@qq.com', null);
INSERT INTO `user` VALUES ('10', 'zhoubi厂', '123123', '2019-12-10 15:50:14', '2019-12-16 15:36:18', '0', '0', null, null);
INSERT INTO `user` VALUES ('11', '哈哈分', '123456', '2019-12-10 15:50:44', '2019-12-16 15:38:49', '0', '0', null, null);
INSERT INTO `user` VALUES ('12', '哈哈哈', '123456', '2019-12-10 15:52:37', '2019-12-16 15:39:28', '0', '0', null, null);
INSERT INTO `user` VALUES ('13', '李白哥', '123456', '2019-12-10 15:55:01', '2019-12-16 15:40:21', '0', '0', null, null);
INSERT INTO `user` VALUES ('14', '李白', '123456', '2019-12-10 16:14:14', '2019-12-10 16:14:14', '0', '0', null, null);
INSERT INTO `user` VALUES ('15', '李白', '123456', '2019-12-10 16:15:33', '2019-12-10 16:15:33', '1', '0', null, null);
INSERT INTO `user` VALUES ('16', '李白', '123456', '2019-12-10 16:18:26', '2019-12-10 16:18:26', '0', '0', null, null);
INSERT INTO `user` VALUES ('17', '李白', '123456', '2019-12-10 16:21:21', '2019-12-10 16:21:21', '1', '0', null, null);
INSERT INTO `user` VALUES ('18', 'johnevinboy', null, '2019-12-15 20:30:15', '2019-12-15 20:30:15', '1', '0', null, null);
INSERT INTO `user` VALUES ('19', 'å??å??å??', null, '2019-12-15 20:30:47', '2019-12-15 20:30:47', '1', '0', null, null);
INSERT INTO `user` VALUES ('20', 'ååå', null, '2019-12-15 20:31:49', '2019-12-15 20:31:49', '0', '0', null, null);
INSERT INTO `user` VALUES ('21', 'ååå', null, '2019-12-15 20:33:04', '2019-12-15 20:33:04', '1', '0', null, null);
INSERT INTO `user` VALUES ('22', '哈哈哈', null, '2019-12-15 20:34:56', '2019-12-15 20:34:56', '1', '0', null, null);
INSERT INTO `user` VALUES ('23', 'ååå', null, '2019-12-15 20:36:01', '2019-12-15 20:36:01', '1', '0', null, null);
INSERT INTO `user` VALUES ('24', 'test', null, '2019-12-17 15:18:09', '2019-12-17 15:18:09', '1', '0', null, null);
INSERT INTO `user` VALUES ('25', '453453', null, '2019-12-17 15:32:47', '2019-12-17 15:32:47', '1', '0', null, null);
INSERT INTO `user` VALUES ('26', '黄忠', null, '2019-12-17 15:45:23', '2019-12-17 15:45:23', '1', '0', null, null);
INSERT INTO `user` VALUES ('27', '狄仁杰！！', '123123', '2019-12-17 15:45:57', '2019-12-17 16:08:48', '1', '0', null, null);
INSERT INTO `user` VALUES ('28', '李白', null, '2019-12-17 15:49:02', '2019-12-17 15:49:02', '1', '0', null, null);
INSERT INTO `user` VALUES ('29', '陈桂民', null, '2019-12-17 15:51:57', '2019-12-17 15:51:57', '0', '0', null, null);
INSERT INTO `user` VALUES ('30', '??', null, '2019-12-17 15:57:10', '2019-12-17 15:57:10', '0', '0', null, null);
INSERT INTO `user` VALUES ('31', '张学友', null, '2019-12-17 16:01:50', '2019-12-17 16:01:50', '1', '0', null, null);
INSERT INTO `user` VALUES ('32', '姚灿林', null, '2019-12-17 16:02:41', '2019-12-17 16:02:41', '0', '0', null, null);
INSERT INTO `user` VALUES ('33', '李hei', null, '2019-12-17 20:34:56', '2019-12-17 20:34:56', '0', '0', null, null);
INSERT INTO `user` VALUES ('34', '1234567', '123456', '2019-12-27 21:12:10', '2019-12-27 21:12:10', '1', '0', '5656456@as.com', '17016478699');
INSERT INTO `user` VALUES ('35', '123456', '123123', '2019-12-27 21:21:11', '2019-12-27 21:21:11', '1', '0', '5656456@as.com', '17016478699');
INSERT INTO `user` VALUES ('36', 'wzc11111', '111111', '2019-12-28 17:02:37', '2019-12-28 17:02:37', '1', '0', '499816237@qq.com', '15177732332');
