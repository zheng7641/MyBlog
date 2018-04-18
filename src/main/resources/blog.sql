/*
Navicat MySQL Data Transfer

Source Server         : a
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-04-18 08:47:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '博客主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `content` text COMMENT '博客内容',
  `tag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '博客名字', '2018-04-09 23:06:32', '2018-04-09 23:06:34', '客内容博客内容博客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客111222内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容客内容博客内容博客内容容122', '博客标签');

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `name` varchar(255) DEFAULT NULL COMMENT '回复人昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '箱邮',
  `title` varchar(255) DEFAULT NULL COMMENT '题标',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客标签id',
  `name` varchar(255) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
