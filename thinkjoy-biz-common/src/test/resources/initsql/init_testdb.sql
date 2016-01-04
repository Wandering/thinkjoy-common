/*
 Navicat MySQL Data Transfer

 Source Server         : local-mysql
 Source Server Version : 50626
 Source Host           : 127.0.0.1
 Source Database       : test

 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 01/04/2016 17:55:41 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `adc_product`
-- ----------------------------
DROP TABLE IF EXISTS `adc_product`;
CREATE TABLE `adc_product` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `createDate` date NOT NULL,
  `linkUrl` varchar(100) NOT NULL,
  `section` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `adc_product`
-- ----------------------------
BEGIN;
INSERT INTO `adc_product` VALUES ('1', '徐乐', '2016-01-04', 'www.baidu.com', '140'), ('2', '徐测试', '2016-01-07', 'www.sina.com', '200');
COMMIT;

-- ----------------------------
--  Table structure for `dap_role`
-- ----------------------------
DROP TABLE IF EXISTS `dap_role`;
CREATE TABLE `dap_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
  `creator` int(11) NOT NULL COMMENT '创建人',
  `createDate` bigint(11) NOT NULL COMMENT '创建时间',
  `lastModifier` int(11) NOT NULL COMMENT '修改人',
  `lastModDate` bigint(11) NOT NULL COMMENT '修改时间',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色|角色管理|基础管理|CreateBaseDomain\n角色';

-- ----------------------------
--  Records of `dap_role`
-- ----------------------------
BEGIN;
INSERT INTO `dap_role` VALUES ('1', '测试管理员', '0', '0', '0', '0', ''), ('2', '普通运营人员', '0', '0', '0', '0', ''), ('3', '测试', '1', '1433390464464', '1', '1433390464464', ''), ('4', 'api管理员', '1', '1435144252652', '1', '1435144252652', ''), ('5', 'hellozj', '1', '1435646346655', '1', '1435646346655', ''), ('6', 'zj', '1', '1438744760139', '1', '1438744760139', '蜂巢'), ('7', '123', '1', '1438942329119', '1', '1438942329119', '132'), ('8', '语智通', '1', '1439437897511', '1', '1439437897511', '语智通'), ('9', '知了', '1', '1445937133105', '1', '1445937133105', '知了');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
