/*
Navicat MySQL Data Transfer

Source Server         : testweb
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : testweb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-09 11:26:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodname` varchar(255) NOT NULL,
  `price` varchar(50) NOT NULL,
  `searchkey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `foodname` (`foodname`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food
-- ----------------------------
INSERT INTO `tb_food` VALUES ('1', '手撕包菜', '10', 'shousibaocai手撕包菜');
INSERT INTO `tb_food` VALUES ('2', '酸辣大白菜', '10', 'suanladabaicai酸辣大白菜');
INSERT INTO `tb_food` VALUES ('3', '酸辣土豆丝', '10', 'suanlatudousi酸辣土豆丝');
INSERT INTO `tb_food` VALUES ('4', '红烧土豆片', '10', 'hongshaotudoupian红烧土豆片');
INSERT INTO `tb_food` VALUES ('5', '西红柿炒蛋', '10', 'xihongshichaodan西红柿炒蛋');
INSERT INTO `tb_food` VALUES ('6', '青椒炒蛋', '10', 'qingjiaochaodan青椒炒蛋');
INSERT INTO `tb_food` VALUES ('7', '麻婆豆腐', '10', 'mapodoufu麻婆豆腐');
INSERT INTO `tb_food` VALUES ('8', '葱花煎蛋', '10', 'conghuajiandan葱花煎蛋');
INSERT INTO `tb_food` VALUES ('9', '红烧茄子', '11', 'hongshaoqiezi红烧茄子');
INSERT INTO `tb_food` VALUES ('10', '紫苏黄瓜', '11', 'zisuhuanggua紫苏黄瓜');
INSERT INTO `tb_food` VALUES ('11', '家常豆腐', '11', 'jiachangdoufu家常豆腐');
INSERT INTO `tb_food` VALUES ('12', '虎皮青椒', '11', 'hupiqingjiao虎皮青椒');
INSERT INTO `tb_food` VALUES ('13', '小炒茄子', '11', 'xiaochaoqiezi小炒茄子');
INSERT INTO `tb_food` VALUES ('14', '茄子豆角', '11', 'qiezidoujiao茄子豆角');
INSERT INTO `tb_food` VALUES ('15', '青瓜炒肉', '12', 'qingguachaorou青瓜炒肉');
INSERT INTO `tb_food` VALUES ('16', '红烧芋头', '12', 'hongshaoyutou红烧芋头');
INSERT INTO `tb_food` VALUES ('17', '土豆片炒肉', '12', 'tudoupianchaorou土豆片炒肉');
INSERT INTO `tb_food` VALUES ('18', '红(白)萝卜炒肉', '12', 'hong(bai)luobochaorou红(白)萝卜炒肉');
INSERT INTO `tb_food` VALUES ('19', '脆黄瓜皮炒肉', '12', 'cuihuangguapichaorou脆黄瓜皮炒肉');
INSERT INTO `tb_food` VALUES ('20', '苦瓜炒肉(蛋)', '12', 'kuguachaorou(dan)苦瓜炒肉(蛋)');
INSERT INTO `tb_food` VALUES ('21', '榨菜炒肉', '12', 'zhacaichaorou榨菜炒肉');
INSERT INTO `tb_food` VALUES ('22', '地瓜炒肉', '12', 'diguachaorou地瓜炒肉');
INSERT INTO `tb_food` VALUES ('23', '洋葱炒肉(蛋)', '12', 'yangcongchaorou(dan)洋葱炒肉(蛋)');
INSERT INTO `tb_food` VALUES ('24', '酸豆角肉沫', '12', 'suandoujiaoroumo酸豆角肉沫');
INSERT INTO `tb_food` VALUES ('25', '香干炒肉', '12', 'xiangganchaorou香干炒肉');
INSERT INTO `tb_food` VALUES ('26', '千张炒肉', '12', 'qianzhangchaorou千张炒肉');
INSERT INTO `tb_food` VALUES ('27', '火腿炒蛋', '12', 'huotuichaodan火腿炒蛋');
INSERT INTO `tb_food` VALUES ('28', '黄豆炒肉', '12', 'huangdouchaorou黄豆炒肉');
INSERT INTO `tb_food` VALUES ('29', '玉米炒肉(火腿)', '12', 'yumichaorou(huotui)玉米炒肉(火腿)');
INSERT INTO `tb_food` VALUES ('30', '黑木耳炒肉', '13', 'heimuerchaorou黑木耳炒肉');
INSERT INTO `tb_food` VALUES ('31', '豌豆炒肉', '13', 'wandouchaorou豌豆炒肉');
INSERT INTO `tb_food` VALUES ('32', '花菜炒肉', '13', 'huacaichaorou花菜炒肉');
INSERT INTO `tb_food` VALUES ('33', '千页豆腐', '13', 'qianyedoufu千页豆腐');
INSERT INTO `tb_food` VALUES ('34', '湖南椒炒五花肉', '13', 'hunanjiaochaowuhuarou湖南椒炒五花肉');
INSERT INTO `tb_food` VALUES ('35', '丝瓜炒肉(蛋)', '13', 'siguachaorou(dan)丝瓜炒肉(蛋)');
INSERT INTO `tb_food` VALUES ('36', '蒜台炒肉', '13', 'suantaichaorou蒜台炒肉');
INSERT INTO `tb_food` VALUES ('37', '高笋炒肉', '13', 'gaosunchaorou高笋炒肉');
INSERT INTO `tb_food` VALUES ('38', '山蕨炒肉', '13', 'shanzuochaorou山蕨炒肉');
INSERT INTO `tb_food` VALUES ('39', '芹菜鸡蛋干', '13', 'qincaijidangan芹菜鸡蛋干');
INSERT INTO `tb_food` VALUES ('40', '香菇炒肉', '13', 'xiangguchaorou香菇炒肉');
INSERT INTO `tb_food` VALUES ('41', '杏鲍菇炒肉', '13', 'xingbaoguchaorou杏鲍菇炒肉');
INSERT INTO `tb_food` VALUES ('42', '藕片炒肉', '13', 'oupianchaorou藕片炒肉');
INSERT INTO `tb_food` VALUES ('43', '芹菜炒柴火香干', '13', 'qincaichaochaihuoxianggan芹菜炒柴火香干');
INSERT INTO `tb_food` VALUES ('44', '莴笋炒肉(腊肉)', '13', 'zuosunchaorou(larou)莴笋炒肉(腊肉)');
INSERT INTO `tb_food` VALUES ('45', '干豆角炒肉(腊肉)', '13', 'gandoujiaochaorou(larou)干豆角炒肉(腊肉)');
INSERT INTO `tb_food` VALUES ('46', '萝卜干炒肉(腊肉)', '13', 'luoboganchaorou(larou)萝卜干炒肉(腊肉)');
INSERT INTO `tb_food` VALUES ('47', '小笋(肉)腊肉', '13', 'xiaosun(rou)larou小笋(肉)腊肉');
INSERT INTO `tb_food` VALUES ('48', '酸菜小笋肉沫', '13', 'suancaixiaosunroumo酸菜小笋肉沫');
INSERT INTO `tb_food` VALUES ('49', '竹笋(肉)腊肉', '13', 'zhusun(rou)larou竹笋(肉)腊肉');
INSERT INTO `tb_food` VALUES ('50', '青椒腊肉', '13', 'qingjiaolarou青椒腊肉');
INSERT INTO `tb_food` VALUES ('51', '四季豆炒肉', '13', 'sijidouchaorou四季豆炒肉');
INSERT INTO `tb_food` VALUES ('52', '香爆卤猪头肉', '13', 'xiangbaoluzhutourou香爆卤猪头肉');
INSERT INTO `tb_food` VALUES ('53', '豆角炒肉', '13', 'doujiaochaorou豆角炒肉');
INSERT INTO `tb_food` VALUES ('54', '蒜台腊肉', '13', 'suantailarou蒜台腊肉');
INSERT INTO `tb_food` VALUES ('55', '腊八豆回锅肉(腊肉)', '13', 'labadouhuiguorou(larou)腊八豆回锅肉(腊肉)');
INSERT INTO `tb_food` VALUES ('56', '青椒回锅肉', '13', 'qingjiaohuiguorou青椒回锅肉');
INSERT INTO `tb_food` VALUES ('57', '四季豆回锅肉', '13', 'sijidouhuiguorou四季豆回锅肉');
INSERT INTO `tb_food` VALUES ('58', '包菜回锅肉', '13', 'baocaihuiguorou包菜回锅肉');
INSERT INTO `tb_food` VALUES ('59', '香干回锅肉', '13', 'xiangganhuiguorou香干回锅肉');
INSERT INTO `tb_food` VALUES ('60', '土豆片回锅肉', '13', 'tudoupianhuiguorou土豆片回锅肉');
INSERT INTO `tb_food` VALUES ('61', '日本豆腐肉沫', '13', 'ribendoufuroumo日本豆腐肉沫');
INSERT INTO `tb_food` VALUES ('62', '腐竹炒肉', '13', 'fuzhuchaorou腐竹炒肉');
INSERT INTO `tb_food` VALUES ('63', '一碗香', '13', 'yiwanxiang一碗香');
INSERT INTO `tb_food` VALUES ('64', '小炒拆骨肉', '13', 'xiaochaochaigurou小炒拆骨肉');
INSERT INTO `tb_food` VALUES ('65', '油豆腐拆骨肉', '13', 'youdoufuchaigurou油豆腐拆骨肉');
INSERT INTO `tb_food` VALUES ('66', '红烧鱼块', '13', 'hongshaoyukuai红烧鱼块');
INSERT INTO `tb_food` VALUES ('67', '香辣火焙鱼', '13', 'xianglahuobeiyu香辣火焙鱼');
INSERT INTO `tb_food` VALUES ('68', '香辣河虾', '13', 'xianglahexia香辣河虾');
INSERT INTO `tb_food` VALUES ('69', '小炒腊肠', '13', 'xiaochaolachang小炒腊肠');
INSERT INTO `tb_food` VALUES ('70', '外婆菜炒肉(蛋)', '13', 'waipocaichaorou(dan)外婆菜炒肉(蛋)');
INSERT INTO `tb_food` VALUES ('71', '腊八豆荷包蛋', '13', 'labadouhebaodan腊八豆荷包蛋');
INSERT INTO `tb_food` VALUES ('72', '烟笋腊肉', '14', 'yansunlarou烟笋腊肉');
INSERT INTO `tb_food` VALUES ('73', '柴火香干炒肉', '14', 'chaihuoxiangganchaorou柴火香干炒肉');
INSERT INTO `tb_food` VALUES ('74', '鸡蛋干炒肉', '14', 'jidanganchaorou鸡蛋干炒肉');
INSERT INTO `tb_food` VALUES ('75', '酸辣鸡杂', '14', 'suanlajiza酸辣鸡杂');
INSERT INTO `tb_food` VALUES ('76', '蒜台鸡杂', '14', 'suantaijiza蒜台鸡杂');
INSERT INTO `tb_food` VALUES ('77', '爆炒土鸡', '14', 'baochaotuji爆炒土鸡');
INSERT INTO `tb_food` VALUES ('78', '香菇炒鸡', '14', 'xiangguchaoji香菇炒鸡');
INSERT INTO `tb_food` VALUES ('79', '黑木耳炒鸡', '14', 'heimuerchaoji黑木耳炒鸡');
INSERT INTO `tb_food` VALUES ('80', '湘味手撕鸡', '14', 'xiangweishousiji湘味手撕鸡');
INSERT INTO `tb_food` VALUES ('81', '红烧排骨', '15', 'hongshaopaigu红烧排骨');
INSERT INTO `tb_food` VALUES ('82', '土豆排骨', '15', 'tudoupaigu土豆排骨');
INSERT INTO `tb_food` VALUES ('83', '湘味啤酒鸭', '15', 'xiangweipijiuya湘味啤酒鸭');
INSERT INTO `tb_food` VALUES ('84', '土豆红烧肉', '15', 'tudouhongshaorou土豆红烧肉');
INSERT INTO `tb_food` VALUES ('85', '干豆角红烧肉', '15', 'gandoujiaohongshaorou干豆角红烧肉');
INSERT INTO `tb_food` VALUES ('86', '青椒肥肠', '15', 'qingjiaofeichang青椒肥肠');
INSERT INTO `tb_food` VALUES ('87', '酸菜肥肠', '15', 'suancaifeichang酸菜肥肠');
INSERT INTO `tb_food` VALUES ('88', '青椒(酸菜)肚丝', '15', 'qingjiao(suancai)dusi青椒(酸菜)肚丝');
INSERT INTO `tb_food` VALUES ('89', '板栗炒肉', '15', 'banlichaorou板栗炒肉');
INSERT INTO `tb_food` VALUES ('90', '荷兰豆炒肉', '15', 'helandouchaorou荷兰豆炒肉');
INSERT INTO `tb_food` VALUES ('91', '荷兰豆炒回锅肉', '16', 'helandouchaohuiguorou荷兰豆炒回锅肉');
INSERT INTO `tb_food` VALUES ('92', '板栗烧鸡', '16', 'banlishaoji板栗烧鸡');
INSERT INTO `tb_food` VALUES ('93', '野山椒牛肉', '16', 'yeshanjiaoniurou野山椒牛肉');
INSERT INTO `tb_food` VALUES ('94', '高笋炒牛肉', '16', 'gaosunchaoniurou高笋炒牛肉');
INSERT INTO `tb_food` VALUES ('95', '萝卜(土豆)牛腩', '16', 'luobo(tudou)niuzuo萝卜(土豆)牛腩');
INSERT INTO `tb_food` VALUES ('96', '爆炒卤猪耳', '18', 'baochaoluzhuer爆炒卤猪耳');
INSERT INTO `tb_food` VALUES ('102', '青瓜火腿(蛋)', '12', 'qingguahuotui(dan)青瓜火腿(蛋)');
INSERT INTO `tb_food` VALUES ('103', '小炒肉', '12', 'xiaochaorou小炒肉');
INSERT INTO `tb_food` VALUES ('104', '冬瓜炒肉', '12', 'dongguachaorou冬瓜炒肉');
INSERT INTO `tb_food` VALUES ('105', '爆炒猪肝', '12', 'baochaozhugan爆炒猪肝');

-- ----------------------------
-- Table structure for tb_food_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_food_user`;
CREATE TABLE `tb_food_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `is_pay` int(10) unsigned zerofill DEFAULT '0000000000',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food_user
-- ----------------------------
INSERT INTO `tb_food_user` VALUES ('44', '105', '1', '2017-09-12 17:49:35', '微辣', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('45', '15', '9', '2017-09-12 17:49:35', '清炒青瓜（免辣）', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('46', '44', '5', '2017-09-12 17:49:35', '莴笋炒腊肉中辣', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('47', '10', '2', '2017-09-12 17:49:35', '', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('48', '2', '4', '2017-09-12 17:49:35', '', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('49', '103', '3', '2017-09-12 17:49:35', '', '0000000001', '2017-09-12 17:49:35');
INSERT INTO `tb_food_user` VALUES ('50', '104', '2', '2017-09-13 18:12:09', '', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('51', '51', '5', '2017-09-13 17:49:35', '四季豆炒腊肉（中辣）', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('52', '8', '1', '2017-09-13 18:13:39', '免辣', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('53', '20', '9', '2017-09-13 17:49:35', '清炒苦瓜（免辣）', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('55', '8', '3', '2017-09-13 17:49:35', '', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('56', '1', '4', '2017-09-13 17:49:35', '微辣', '0000000001', '2017-09-13 17:49:35');
INSERT INTO `tb_food_user` VALUES ('57', '62', '1', '2017-09-14 11:59:58', '免辣', '0000000001', '2017-09-14 09:41:22');
INSERT INTO `tb_food_user` VALUES ('59', '102', '9', '2017-09-14 11:58:47', '青瓜炒蛋（免辣）', '0000000001', '2017-09-14 10:25:03');
INSERT INTO `tb_food_user` VALUES ('60', '32', '2', '2017-09-14 10:45:17', '多放肉，少放花菜', '0000000000', '2017-09-14 10:35:23');
INSERT INTO `tb_food_user` VALUES ('61', '44', '5', '2017-09-14 13:54:42', '莴笋炒腊肉 中辣', '0000000001', '2017-09-14 10:37:44');
INSERT INTO `tb_food_user` VALUES ('62', '36', '3', '2017-09-14 13:54:02', '', '0000000001', '2017-09-14 10:39:35');
INSERT INTO `tb_food_user` VALUES ('63', '1', '4', '2017-09-14 13:53:38', '微辣', '0000000001', '2017-09-14 10:43:56');
INSERT INTO `tb_food_user` VALUES ('64', '5', '1', '2017-09-15 14:11:01', '免辣', '0000000001', '2017-09-15 10:00:44');
INSERT INTO `tb_food_user` VALUES ('65', '1', '3', '2017-09-15 10:04:02', '', '0000000001', '2017-09-15 10:04:02');
INSERT INTO `tb_food_user` VALUES ('66', '1', '5', '2017-09-15 14:43:28', '中辣【凉茶】', '0000000001', '2017-09-15 10:22:12');
INSERT INTO `tb_food_user` VALUES ('67', '102', '9', '2017-09-15 14:43:23', '清炒青瓜（免辣 凉菜）', '0000000001', '2017-09-15 10:26:11');
INSERT INTO `tb_food_user` VALUES ('68', '1', '4', '2017-09-15 14:43:16', '微辣', '0000000001', '2017-09-15 10:29:06');
INSERT INTO `tb_food_user` VALUES ('69', '58', '6', '2017-09-15 14:43:09', '', '0000000001', '2017-09-15 10:58:53');
INSERT INTO `tb_food_user` VALUES ('70', '11', '1', '2017-09-18 13:55:35', '', '0000000001', '2017-09-18 10:08:39');
INSERT INTO `tb_food_user` VALUES ('71', '15', '5', '2017-09-18 14:09:25', '微辣（凉茶）', '0000000001', '2017-09-18 10:30:28');
INSERT INTO `tb_food_user` VALUES ('72', '103', '6', '2017-09-18 14:09:19', '', '0000000001', '2017-09-18 10:32:11');
INSERT INTO `tb_food_user` VALUES ('73', '15', '9', '2017-09-18 14:09:55', '清炒青瓜（免辣）', '0000000001', '2017-09-18 10:37:55');
INSERT INTO `tb_food_user` VALUES ('74', '29', '3', '2017-09-18 13:49:44', '玉米炒肉', '0000000001', '2017-09-18 10:39:16');
INSERT INTO `tb_food_user` VALUES ('75', '57', '2', '2017-09-18 14:09:48', '免辣', '0000000001', '2017-09-18 10:39:42');
INSERT INTO `tb_food_user` VALUES ('76', '1', '4', '2017-09-18 14:09:07', '微辣', '0000000001', '2017-09-18 10:43:54');
INSERT INTO `tb_food_user` VALUES ('78', '15', '9', '2017-09-19 14:21:32', '清炒青瓜', '0000000001', '2017-09-19 09:57:17');
INSERT INTO `tb_food_user` VALUES ('79', '11', '1', '2017-09-19 13:57:42', '', '0000000001', '2017-09-19 10:03:04');
INSERT INTO `tb_food_user` VALUES ('80', '58', '3', '2017-09-19 13:59:08', '', '0000000001', '2017-09-19 10:13:26');
INSERT INTO `tb_food_user` VALUES ('81', '45', '6', '2017-09-19 14:21:07', '干豆角腊肉', '0000000001', '2017-09-19 10:21:54');
INSERT INTO `tb_food_user` VALUES ('82', '1', '5', '2017-09-19 14:22:06', '微辣(冰红茶)', '0000000001', '2017-09-19 10:24:19');
INSERT INTO `tb_food_user` VALUES ('83', '81', '2', '2017-09-21 11:02:37', '', '0000000001', '2017-09-19 10:45:20');
INSERT INTO `tb_food_user` VALUES ('84', '3', '4', '2017-09-19 16:29:31', '微辣', '0000000001', '2017-09-19 10:45:21');
INSERT INTO `tb_food_user` VALUES ('85', '56', '3', '2017-09-20 16:41:52', '', '0000000001', '2017-09-20 09:14:55');
INSERT INTO `tb_food_user` VALUES ('86', '32', '2', '2017-09-21 11:02:45', '', '0000000001', '2017-09-20 09:17:49');
INSERT INTO `tb_food_user` VALUES ('87', '102', '9', '2017-09-22 17:55:11', '清炒青瓜（免辣）', '0000000001', '2017-09-20 10:01:13');
INSERT INTO `tb_food_user` VALUES ('88', '44', '5', '2017-09-20 13:50:00', '莴笋炒腊肉（微辣）', '0000000001', '2017-09-20 10:09:54');
INSERT INTO `tb_food_user` VALUES ('89', '11', '1', '2017-09-21 10:59:11', 'ok', '0000000001', '2017-09-20 10:21:42');
INSERT INTO `tb_food_user` VALUES ('90', '9', '4', '2017-09-21 11:17:56', '微辣', '0000000001', '2017-09-20 11:07:09');
INSERT INTO `tb_food_user` VALUES ('91', '10', '1', '2017-09-21 13:40:54', '', '0000000001', '2017-09-21 10:07:48');
INSERT INTO `tb_food_user` VALUES ('92', '14', '6', '2017-09-21 13:36:56', '', '0000000001', '2017-09-21 10:08:43');
INSERT INTO `tb_food_user` VALUES ('93', '56', '3', '2017-09-21 13:45:52', '青椒肉丝 微辣', '0000000001', '2017-09-21 10:09:02');
INSERT INTO `tb_food_user` VALUES ('94', '3', '4', '2017-09-21 13:44:12', '微辣', '0000000001', '2017-09-21 10:44:32');
INSERT INTO `tb_food_user` VALUES ('95', '38', '5', '2017-09-21 13:45:06', '微辣（凉茶）', '0000000001', '2017-09-21 10:44:58');
INSERT INTO `tb_food_user` VALUES ('96', '88', '2', '2017-09-22 10:54:15', '酸菜肚丝儿', '0000000001', '2017-09-21 10:47:03');
INSERT INTO `tb_food_user` VALUES ('97', '102', '9', '2017-09-21 13:43:48', '清炒青瓜（免辣）', '0000000001', '2017-09-21 10:48:31');
INSERT INTO `tb_food_user` VALUES ('98', '56', '3', '2017-09-22 14:37:44', '青椒肉丝 微辣', '0000000001', '2017-09-22 09:49:56');
INSERT INTO `tb_food_user` VALUES ('99', '45', '5', '2017-09-22 17:47:44', '干豆角炒腊肉（微辣）凉茶', '0000000001', '2017-09-22 10:15:31');
INSERT INTO `tb_food_user` VALUES ('100', '104', '9', '2017-09-22 12:15:53', '清炒冬瓜（免辣）', '0000000001', '2017-09-22 10:35:01');
INSERT INTO `tb_food_user` VALUES ('101', '44', '2', '2017-09-22 17:47:52', '莴笋炒肉', '0000000001', '2017-09-22 10:35:30');
INSERT INTO `tb_food_user` VALUES ('102', '85', '6', '2017-09-22 17:54:39', '', '0000000001', '2017-09-22 10:35:52');
INSERT INTO `tb_food_user` VALUES ('103', '11', '1', '2017-09-22 14:14:32', '', '0000000001', '2017-09-22 10:41:54');
INSERT INTO `tb_food_user` VALUES ('104', '3', '4', '2017-09-22 17:47:40', '', '0000000001', '2017-09-22 10:50:07');
INSERT INTO `tb_food_user` VALUES ('105', '102', '9', '2017-09-25 13:56:19', '清炒青瓜（免辣）', '0000000001', '2017-09-25 09:49:11');
INSERT INTO `tb_food_user` VALUES ('106', '7', '3', '2017-09-25 15:55:46', '', '0000000001', '2017-09-25 10:01:23');
INSERT INTO `tb_food_user` VALUES ('107', '11', '1', '2017-09-25 12:12:46', '', '0000000001', '2017-09-25 10:29:24');
INSERT INTO `tb_food_user` VALUES ('108', '42', '5', '2017-09-25 13:56:14', '中辣', '0000000001', '2017-09-25 10:29:36');
INSERT INTO `tb_food_user` VALUES ('109', '20', '6', '2017-09-25 13:56:08', '炒肉', '0000000001', '2017-09-25 10:38:00');
INSERT INTO `tb_food_user` VALUES ('110', '1', '4', '2017-09-25 13:56:02', '', '0000000001', '2017-09-25 10:40:42');
INSERT INTO `tb_food_user` VALUES ('111', '45', '2', '2017-09-25 13:55:56', '干豆角腊肉', '0000000001', '2017-09-25 10:59:23');
INSERT INTO `tb_food_user` VALUES ('112', '11', '1', '2017-09-26 13:58:12', '', '0000000001', '2017-09-26 09:13:57');
INSERT INTO `tb_food_user` VALUES ('113', '11', '3', '2017-09-26 16:59:26', '微辣', '0000000001', '2017-09-26 09:22:32');
INSERT INTO `tb_food_user` VALUES ('114', '22', '6', '2017-09-27 08:44:47', '', '0000000001', '2017-09-26 10:21:15');
INSERT INTO `tb_food_user` VALUES ('115', '11', '5', '2017-09-26 14:58:33', '微辣', '0000000001', '2017-09-26 10:22:44');
INSERT INTO `tb_food_user` VALUES ('116', '9', '4', '2017-09-26 14:58:38', '', '0000000001', '2017-09-26 10:23:17');
INSERT INTO `tb_food_user` VALUES ('118', '20', '9', '2017-09-26 14:58:54', '清炒苦瓜（免辣）', '0000000001', '2017-09-26 10:27:04');
INSERT INTO `tb_food_user` VALUES ('119', '89', '2', '2017-09-27 08:58:06', '', '0000000001', '2017-09-26 10:28:54');
INSERT INTO `tb_food_user` VALUES ('120', '20', '2', '2017-09-27 08:58:01', '苦瓜炒肉', '0000000000', '2017-09-27 08:57:37');
INSERT INTO `tb_food_user` VALUES ('121', '11', '9', '2017-09-27 16:16:27', '免辣少豆腐', '0000000001', '2017-09-27 09:02:04');
INSERT INTO `tb_food_user` VALUES ('123', '56', '3', '2017-09-27 14:03:18', '青椒肉丝 微辣', '0000000001', '2017-09-27 09:03:55');
INSERT INTO `tb_food_user` VALUES ('124', '29', '6', '2017-09-27 16:16:36', '炒肉', '0000000001', '2017-09-27 09:05:18');
INSERT INTO `tb_food_user` VALUES ('125', '102', '4', '2017-09-27 17:56:07', '青瓜炒蛋', '0000000001', '2017-09-27 09:06:54');
INSERT INTO `tb_food_user` VALUES ('126', '20', '1', '2017-09-27 16:16:15', '苦瓜炒蛋', '0000000001', '2017-09-27 09:07:30');
INSERT INTO `tb_food_user` VALUES ('127', '50', '5', '2017-09-27 16:16:23', '中辣', '0000000001', '2017-09-27 09:08:04');
INSERT INTO `tb_food_user` VALUES ('128', '8', '9', '2017-09-29 10:37:23', '免辣', '0000000001', '2017-09-28 08:49:23');
INSERT INTO `tb_food_user` VALUES ('129', '56', '3', '2017-09-28 15:24:14', '青椒肉丝', '0000000001', '2017-09-28 10:21:12');
INSERT INTO `tb_food_user` VALUES ('130', '3', '4', '2017-09-28 13:56:26', '红茶', '0000000001', '2017-09-28 10:28:48');
INSERT INTO `tb_food_user` VALUES ('131', '1', '5', '2017-09-28 13:56:20', '中辣', '0000000001', '2017-09-28 10:29:24');
INSERT INTO `tb_food_user` VALUES ('132', '20', '1', '2017-09-28 13:58:56', '苦瓜炒蛋', '0000000001', '2017-09-28 10:30:05');
INSERT INTO `tb_food_user` VALUES ('133', '11', '6', '2017-09-28 13:56:30', '', '0000000001', '2017-09-28 10:31:03');
INSERT INTO `tb_food_user` VALUES ('134', '35', '9', '2017-09-29 15:07:18', '清炒丝瓜（免辣）', '0000000001', '2017-09-29 09:45:51');
INSERT INTO `tb_food_user` VALUES ('135', '20', '1', '2017-09-29 13:44:28', '苦瓜炒蛋', '0000000001', '2017-09-29 09:49:43');
INSERT INTO `tb_food_user` VALUES ('136', '50', '5', '2017-09-29 14:16:47', '中辣', '0000000001', '2017-09-29 10:24:28');
INSERT INTO `tb_food_user` VALUES ('137', '32', '6', '2017-09-29 15:07:31', '', '0000000001', '2017-09-29 10:27:35');
INSERT INTO `tb_food_user` VALUES ('138', '3', '4', '2017-09-29 15:07:25', '', '0000000001', '2017-09-29 10:31:26');
INSERT INTO `tb_food_user` VALUES ('139', '62', '7', '2017-09-29 15:06:59', '超级辣', '0000000001', '2017-09-29 10:49:18');
INSERT INTO `tb_food_user` VALUES ('140', '26', '2', '2017-10-10 09:32:17', '', '0000000000', '2017-10-10 09:32:17');
INSERT INTO `tb_food_user` VALUES ('141', '1', '4', '2017-10-10 09:33:16', '', '0000000000', '2017-10-10 09:33:16');
INSERT INTO `tb_food_user` VALUES ('142', '102', '9', '2017-10-17 11:50:12', '清炒青瓜（免辣）', '0000000001', '2017-10-10 09:34:35');
INSERT INTO `tb_food_user` VALUES ('143', '5', '1', '2017-10-10 09:34:43', '', '0000000000', '2017-10-10 09:34:43');
INSERT INTO `tb_food_user` VALUES ('145', '44', '5', '2017-10-10 09:46:25', '莴笋炒腊肉（中辣）凉茶', '0000000000', '2017-10-10 09:44:33');
INSERT INTO `tb_food_user` VALUES ('146', '13', '6', '2017-10-10 10:27:04', '', '0000000000', '2017-10-10 10:27:04');
INSERT INTO `tb_food_user` VALUES ('147', '26', '2', '2017-10-12 10:49:51', '', '0000000001', '2017-10-11 10:20:14');
INSERT INTO `tb_food_user` VALUES ('148', '5', '1', '2017-10-11 16:50:07', '', '0000000001', '2017-10-11 10:20:48');
INSERT INTO `tb_food_user` VALUES ('149', '5', '9', '2017-10-17 11:50:05', '免辣', '0000000001', '2017-10-11 10:21:09');
INSERT INTO `tb_food_user` VALUES ('150', '12', '6', '2017-10-11 10:21:50', '', '0000000000', '2017-10-11 10:21:50');
INSERT INTO `tb_food_user` VALUES ('151', '56', '3', '2017-10-11 13:54:19', '青椒肉丝', '0000000001', '2017-10-11 10:23:04');
INSERT INTO `tb_food_user` VALUES ('152', '44', '5', '2017-10-11 10:24:19', '莴笋炒腊肉（中辣）凉茶', '0000000000', '2017-10-11 10:23:57');
INSERT INTO `tb_food_user` VALUES ('153', '9', '4', '2017-10-11 10:25:26', '', '0000000000', '2017-10-11 10:25:26');
INSERT INTO `tb_food_user` VALUES ('154', '56', '3', '2017-10-12 13:55:14', '青椒肉丝', '0000000001', '2017-10-12 09:48:48');
INSERT INTO `tb_food_user` VALUES ('155', '41', '5', '2017-10-12 10:17:14', '中辣（红茶/凉茶）', '0000000000', '2017-10-12 10:16:44');
INSERT INTO `tb_food_user` VALUES ('156', '5', '1', '2017-10-12 13:52:50', '', '0000000001', '2017-10-12 10:22:44');
INSERT INTO `tb_food_user` VALUES ('157', '5', '9', '2017-10-17 11:49:58', '免辣少盐', '0000000001', '2017-10-12 10:27:56');
INSERT INTO `tb_food_user` VALUES ('158', '5', '4', '2017-10-12 10:44:48', '微辣', '0000000000', '2017-10-12 10:43:45');
INSERT INTO `tb_food_user` VALUES ('159', '24', '6', '2017-10-12 10:43:54', '', '0000000000', '2017-10-12 10:43:54');
INSERT INTO `tb_food_user` VALUES ('160', '83', '2', '2017-10-12 10:47:58', '', '0000000000', '2017-10-12 10:47:58');
INSERT INTO `tb_food_user` VALUES ('161', '5', '1', '2017-10-16 09:11:36', '', '0000000001', '2017-10-13 09:53:25');
INSERT INTO `tb_food_user` VALUES ('162', '102', '9', '2017-10-13 18:05:14', '清炒青瓜（免辣）', '0000000001', '2017-10-13 10:11:49');
INSERT INTO `tb_food_user` VALUES ('163', '15', '5', '2017-10-13 17:11:36', '中辣（凉茶）', '0000000001', '2017-10-13 10:14:31');
INSERT INTO `tb_food_user` VALUES ('164', '91', '6', '2017-10-13 18:05:18', '', '0000000001', '2017-10-13 10:25:20');
INSERT INTO `tb_food_user` VALUES ('165', '5', '3', '2017-10-13 16:52:31', '微辣', '0000000001', '2017-10-13 10:26:21');
INSERT INTO `tb_food_user` VALUES ('166', '5', '4', '2017-10-13 17:11:19', '', '0000000001', '2017-10-13 10:28:58');
INSERT INTO `tb_food_user` VALUES ('167', '88', '2', '2017-10-13 17:11:27', '青椒肚丝', '0000000001', '2017-10-13 10:32:11');
INSERT INTO `tb_food_user` VALUES ('168', '5', '9', '2017-10-17 11:49:45', '免辣少盐', '0000000001', '2017-10-16 10:12:47');
INSERT INTO `tb_food_user` VALUES ('169', '56', '3', '2017-10-17 10:15:57', '', '0000000001', '2017-10-16 10:22:55');
INSERT INTO `tb_food_user` VALUES ('170', '5', '1', '2017-10-17 10:15:02', '', '0000000001', '2017-10-16 10:22:59');
INSERT INTO `tb_food_user` VALUES ('171', '5', '4', '2017-10-16 10:27:02', '微辣', '0000000000', '2017-10-16 10:26:56');
INSERT INTO `tb_food_user` VALUES ('172', '5', '5', '2017-10-16 10:28:23', '', '0000000000', '2017-10-16 10:28:23');
INSERT INTO `tb_food_user` VALUES ('173', '21', '6', '2017-10-16 14:51:40', '', '0000000001', '2017-10-16 10:32:56');
INSERT INTO `tb_food_user` VALUES ('174', '41', '2', '2017-10-16 10:34:31', '', '0000000000', '2017-10-16 10:34:31');
INSERT INTO `tb_food_user` VALUES ('175', '56', '3', '2017-10-18 10:24:00', '青椒肉丝', '0000000001', '2017-10-17 10:17:06');
INSERT INTO `tb_food_user` VALUES ('176', '27', '6', '2017-10-18 10:24:05', '', '0000000001', '2017-10-17 10:17:12');
INSERT INTO `tb_food_user` VALUES ('177', '56', '5', '2017-10-18 10:23:56', '青椒肉丝', '0000000001', '2017-10-17 10:21:15');
INSERT INTO `tb_food_user` VALUES ('178', '5', '1', '2017-10-17 13:57:16', '', '0000000001', '2017-10-17 10:22:41');
INSERT INTO `tb_food_user` VALUES ('179', '5', '9', '2017-10-18 10:01:05', '免辣少盐', '0000000001', '2017-10-17 10:23:05');
INSERT INTO `tb_food_user` VALUES ('181', '103', '2', '2017-10-18 10:23:51', '', '0000000001', '2017-10-17 10:24:00');
INSERT INTO `tb_food_user` VALUES ('182', '5', '4', '2017-10-18 10:23:43', '微辣', '0000000001', '2017-10-17 10:24:18');
INSERT INTO `tb_food_user` VALUES ('183', '103', '8', '2017-10-18 10:23:39', '青椒炒肉', '0000000001', '2017-10-17 10:30:25');
INSERT INTO `tb_food_user` VALUES ('185', '23', '5', '2017-10-18 15:22:52', '洋葱炒肉（中辣）凉茶', '0000000001', '2017-10-18 10:25:33');
INSERT INTO `tb_food_user` VALUES ('186', '11', '1', '2017-10-18 15:29:47', '', '0000000001', '2017-10-18 10:28:34');
INSERT INTO `tb_food_user` VALUES ('187', '30', '6', '2017-10-18 15:23:15', '', '0000000001', '2017-10-18 10:36:18');
INSERT INTO `tb_food_user` VALUES ('188', '7', '3', '2017-10-19 08:53:00', '', '0000000001', '2017-10-18 10:37:33');
INSERT INTO `tb_food_user` VALUES ('189', '5', '4', '2017-10-18 15:22:46', '微辣', '0000000001', '2017-10-18 10:39:29');
INSERT INTO `tb_food_user` VALUES ('190', '3', '8', '2017-10-18 15:36:07', '', '0000000001', '2017-10-18 10:41:06');
INSERT INTO `tb_food_user` VALUES ('192', '66', '2', '2017-10-18 15:23:04', '', '0000000001', '2017-10-18 10:47:38');
INSERT INTO `tb_food_user` VALUES ('194', '8', '9', '2017-10-18 15:23:08', '', '0000000001', '2017-10-18 10:49:51');
INSERT INTO `tb_food_user` VALUES ('195', '64', '3', '2017-10-19 13:51:33', '', '0000000001', '2017-10-19 08:54:28');
INSERT INTO `tb_food_user` VALUES ('196', '11', '1', '2017-10-19 13:52:13', '', '0000000001', '2017-10-19 09:47:38');
INSERT INTO `tb_food_user` VALUES ('198', '69', '2', '2017-10-19 10:40:03', '', '0000000000', '2017-10-19 10:40:03');
INSERT INTO `tb_food_user` VALUES ('199', '58', '6', '2017-10-19 14:41:39', '', '0000000001', '2017-10-19 10:41:27');
INSERT INTO `tb_food_user` VALUES ('200', '5', '4', '2017-10-19 10:42:57', '微辣', '0000000000', '2017-10-19 10:42:29');
INSERT INTO `tb_food_user` VALUES ('201', '1', '5', '2017-10-19 14:41:35', '中辣【红茶】', '0000000001', '2017-10-19 10:49:29');
INSERT INTO `tb_food_user` VALUES ('203', '70', '6', '2017-10-20 14:10:46', '炒肉', '0000000001', '2017-10-20 10:25:10');
INSERT INTO `tb_food_user` VALUES ('204', '57', '2', '2017-10-20 10:25:11', '', '0000000000', '2017-10-20 10:25:11');
INSERT INTO `tb_food_user` VALUES ('205', '47', '5', '2017-10-20 10:25:58', '小笋肉沫（微辣）', '0000000000', '2017-10-20 10:25:17');
INSERT INTO `tb_food_user` VALUES ('206', '63', '4', '2017-10-20 10:29:32', '', '0000000000', '2017-10-20 10:29:32');
INSERT INTO `tb_food_user` VALUES ('207', '80', '3', '2017-10-20 13:55:38', '', '0000000001', '2017-10-20 10:37:15');
INSERT INTO `tb_food_user` VALUES ('209', '47', '5', '2017-10-23 10:44:19', '', '0000000000', '2017-10-23 10:44:19');
INSERT INTO `tb_food_user` VALUES ('210', '51', '5', '2017-10-24 10:43:14', '杨瑞', '0000000000', '2017-10-24 10:41:37');
INSERT INTO `tb_food_user` VALUES ('211', '20', '5', '2017-10-24 10:43:06', '苦瓜炒肉（剑南）', '0000000000', '2017-10-24 10:42:28');
INSERT INTO `tb_food_user` VALUES ('212', '82', '5', '2017-10-24 10:43:42', '闫昊', '0000000000', '2017-10-24 10:43:36');
INSERT INTO `tb_food_user` VALUES ('213', '63', '4', '2017-10-24 10:43:53', '', '0000000000', '2017-10-24 10:43:53');
INSERT INTO `tb_food_user` VALUES ('214', '19', '5', '2017-10-24 10:44:23', '微辣', '0000000000', '2017-10-24 10:44:11');
INSERT INTO `tb_food_user` VALUES ('215', '5', '5', '2017-10-25 10:30:37', '闫昊', '0000000000', '2017-10-25 10:27:49');
INSERT INTO `tb_food_user` VALUES ('216', '63', '5', '2017-10-25 10:29:41', '剑南', '0000000000', '2017-10-25 10:28:05');
INSERT INTO `tb_food_user` VALUES ('217', '8', '5', '2017-10-25 10:30:25', '荣华（免辣少盐）', '0000000000', '2017-10-25 10:28:31');
INSERT INTO `tb_food_user` VALUES ('218', '57', '5', '2017-10-25 10:29:50', '杨瑞', '0000000000', '2017-10-25 10:28:46');
INSERT INTO `tb_food_user` VALUES ('219', '51', '5', '2017-10-25 10:33:45', '微辣', '0000000000', '2017-10-25 10:33:38');
INSERT INTO `tb_food_user` VALUES ('220', '5', '4', '2017-10-25 10:55:21', '', '0000000000', '2017-10-25 10:55:21');
INSERT INTO `tb_food_user` VALUES ('221', '30', '5', '2017-10-27 10:18:09', '中辣', '0000000000', '2017-10-27 10:17:54');
INSERT INTO `tb_food_user` VALUES ('222', '103', '5', '2017-10-27 10:26:29', '微辣', '0000000000', '2017-10-27 10:26:19');
INSERT INTO `tb_food_user` VALUES ('223', '66', '5', '2017-10-27 10:38:05', '', '0000000000', '2017-10-27 10:38:05');
INSERT INTO `tb_food_user` VALUES ('224', '103', '5', '2017-10-27 10:38:45', '', '0000000000', '2017-10-27 10:38:45');
INSERT INTO `tb_food_user` VALUES ('225', '75', '5', '2017-10-27 11:17:55', '', '0000000000', '2017-10-27 11:17:55');
INSERT INTO `tb_food_user` VALUES ('226', '57', '5', '2017-10-30 10:20:54', '', '0000000000', '2017-10-30 10:20:54');
INSERT INTO `tb_food_user` VALUES ('227', '1', '4', '2017-10-30 10:24:56', '', '0000000000', '2017-10-30 10:24:56');
INSERT INTO `tb_food_user` VALUES ('228', '30', '5', '2017-10-30 10:25:50', '中辣', '0000000000', '2017-10-30 10:25:38');
INSERT INTO `tb_food_user` VALUES ('229', '103', '5', '2017-10-30 10:29:27', '微辣', '0000000000', '2017-10-30 10:29:19');
INSERT INTO `tb_food_user` VALUES ('230', '40', '5', '2017-10-30 10:31:46', '', '0000000000', '2017-10-30 10:31:46');
INSERT INTO `tb_food_user` VALUES ('231', '64', '5', '2017-10-30 10:36:24', '中辣', '0000000000', '2017-10-30 10:36:15');
INSERT INTO `tb_food_user` VALUES ('232', '42', '3', '2017-11-10 17:21:54', '', '0000000000', '2017-11-10 17:21:54');
INSERT INTO `tb_food_user` VALUES ('237', '8', '3', '2017-11-13 16:52:08', '', '0000000000', '2017-11-13 16:52:08');
INSERT INTO `tb_food_user` VALUES ('238', '8', '3', '2017-11-13 16:54:21', '', '0000000000', '2017-11-13 16:54:21');
INSERT INTO `tb_food_user` VALUES ('239', '12', '3', '2017-11-13 16:54:27', '', '0000000000', '2017-11-13 16:54:27');
INSERT INTO `tb_food_user` VALUES ('240', '44', '5', '2017-11-14 10:05:01', '莴笋炒腊肉', '0000000000', '2017-11-14 10:04:42');
INSERT INTO `tb_food_user` VALUES ('241', '36', '5', '2017-11-14 10:08:40', '', '0000000000', '2017-11-14 10:08:40');
INSERT INTO `tb_food_user` VALUES ('242', '103', '5', '2017-11-14 10:26:41', '', '0000000000', '2017-11-14 10:26:34');
INSERT INTO `tb_food_user` VALUES ('246', '70', '3', '2017-11-21 15:36:31', '', '0000000000', '2017-11-21 15:36:31');

-- ----------------------------
-- Table structure for tb_recommend
-- ----------------------------
DROP TABLE IF EXISTS `tb_recommend`;
CREATE TABLE `tb_recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=999 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recommend
-- ----------------------------
INSERT INTO `tb_recommend` VALUES ('902', '1', '5', '70397');
INSERT INTO `tb_recommend` VALUES ('903', '1', '20', '30241');
INSERT INTO `tb_recommend` VALUES ('904', '1', '8', '10103');
INSERT INTO `tb_recommend` VALUES ('905', '1', '10', '10172');
INSERT INTO `tb_recommend` VALUES ('906', '1', '11', '80414');
INSERT INTO `tb_recommend` VALUES ('907', '1', '62', '10112');
INSERT INTO `tb_recommend` VALUES ('908', '1', '105', '10095');
INSERT INTO `tb_recommend` VALUES ('909', '2', '69', '10414');
INSERT INTO `tb_recommend` VALUES ('910', '2', '103', '10397');
INSERT INTO `tb_recommend` VALUES ('911', '2', '32', '20164');
INSERT INTO `tb_recommend` VALUES ('912', '2', '66', '10406');
INSERT INTO `tb_recommend` VALUES ('913', '2', '10', '10095');
INSERT INTO `tb_recommend` VALUES ('914', '2', '41', '10388');
INSERT INTO `tb_recommend` VALUES ('915', '2', '104', '10103');
INSERT INTO `tb_recommend` VALUES ('916', '2', '44', '10181');
INSERT INTO `tb_recommend` VALUES ('917', '2', '45', '10207');
INSERT INTO `tb_recommend` VALUES ('918', '2', '81', '10155');
INSERT INTO `tb_recommend` VALUES ('919', '2', '20', '10224');
INSERT INTO `tb_recommend` VALUES ('920', '2', '83', '10354');
INSERT INTO `tb_recommend` VALUES ('921', '2', '57', '20423');
INSERT INTO `tb_recommend` VALUES ('922', '2', '26', '20345');
INSERT INTO `tb_recommend` VALUES ('923', '2', '89', '10216');
INSERT INTO `tb_recommend` VALUES ('924', '2', '88', '20362');
INSERT INTO `tb_recommend` VALUES ('925', '3', '1', '10120');
INSERT INTO `tb_recommend` VALUES ('926', '3', '103', '10095');
INSERT INTO `tb_recommend` VALUES ('927', '3', '64', '10414');
INSERT INTO `tb_recommend` VALUES ('928', '3', '80', '10423');
INSERT INTO `tb_recommend` VALUES ('929', '3', '5', '10362');
INSERT INTO `tb_recommend` VALUES ('930', '3', '36', '10112');
INSERT INTO `tb_recommend` VALUES ('931', '3', '7', '20406');
INSERT INTO `tb_recommend` VALUES ('932', '3', '8', '10103');
INSERT INTO `tb_recommend` VALUES ('933', '3', '58', '10155');
INSERT INTO `tb_recommend` VALUES ('934', '3', '56', '90397');
INSERT INTO `tb_recommend` VALUES ('935', '3', '11', '10216');
INSERT INTO `tb_recommend` VALUES ('936', '3', '29', '10146');
INSERT INTO `tb_recommend` VALUES ('937', '4', '102', '10224');
INSERT INTO `tb_recommend` VALUES ('938', '4', '1', '70509');
INSERT INTO `tb_recommend` VALUES ('939', '4', '2', '10095');
INSERT INTO `tb_recommend` VALUES ('940', '4', '3', '50241');
INSERT INTO `tb_recommend` VALUES ('941', '4', '5', '70466');
INSERT INTO `tb_recommend` VALUES ('942', '4', '9', '30345');
INSERT INTO `tb_recommend` VALUES ('943', '4', '63', '20457');
INSERT INTO `tb_recommend` VALUES ('944', '5', '103', '30509');
INSERT INTO `tb_recommend` VALUES ('945', '5', '1', '40414');
INSERT INTO `tb_recommend` VALUES ('946', '5', '38', '10172');
INSERT INTO `tb_recommend` VALUES ('947', '5', '64', '10509');
INSERT INTO `tb_recommend` VALUES ('948', '5', '5', '20466');
INSERT INTO `tb_recommend` VALUES ('949', '5', '66', '10483');
INSERT INTO `tb_recommend` VALUES ('950', '5', '42', '10207');
INSERT INTO `tb_recommend` VALUES ('951', '5', '8', '10466');
INSERT INTO `tb_recommend` VALUES ('952', '5', '40', '10509');
INSERT INTO `tb_recommend` VALUES ('953', '5', '41', '10354');
INSERT INTO `tb_recommend` VALUES ('954', '5', '11', '10216');
INSERT INTO `tb_recommend` VALUES ('955', '5', '47', '20449');
INSERT INTO `tb_recommend` VALUES ('956', '5', '44', '50345');
INSERT INTO `tb_recommend` VALUES ('957', '5', '45', '10181');
INSERT INTO `tb_recommend` VALUES ('958', '5', '75', '10483');
INSERT INTO `tb_recommend` VALUES ('959', '5', '15', '20362');
INSERT INTO `tb_recommend` VALUES ('960', '5', '51', '30466');
INSERT INTO `tb_recommend` VALUES ('961', '5', '50', '20241');
INSERT INTO `tb_recommend` VALUES ('962', '5', '19', '10457');
INSERT INTO `tb_recommend` VALUES ('963', '5', '20', '10457');
INSERT INTO `tb_recommend` VALUES ('964', '5', '23', '10406');
INSERT INTO `tb_recommend` VALUES ('965', '5', '82', '10457');
INSERT INTO `tb_recommend` VALUES ('966', '5', '57', '20509');
INSERT INTO `tb_recommend` VALUES ('967', '5', '56', '10397');
INSERT INTO `tb_recommend` VALUES ('968', '5', '63', '10466');
INSERT INTO `tb_recommend` VALUES ('969', '5', '30', '20509');
INSERT INTO `tb_recommend` VALUES ('970', '6', '103', '10146');
INSERT INTO `tb_recommend` VALUES ('971', '6', '70', '10423');
INSERT INTO `tb_recommend` VALUES ('972', '6', '32', '10241');
INSERT INTO `tb_recommend` VALUES ('973', '6', '11', '10233');
INSERT INTO `tb_recommend` VALUES ('974', '6', '12', '10345');
INSERT INTO `tb_recommend` VALUES ('975', '6', '13', '10336');
INSERT INTO `tb_recommend` VALUES ('976', '6', '14', '10172');
INSERT INTO `tb_recommend` VALUES ('977', '6', '45', '10155');
INSERT INTO `tb_recommend` VALUES ('978', '6', '85', '10181');
INSERT INTO `tb_recommend` VALUES ('979', '6', '21', '10388');
INSERT INTO `tb_recommend` VALUES ('980', '6', '20', '10207');
INSERT INTO `tb_recommend` VALUES ('981', '6', '22', '10216');
INSERT INTO `tb_recommend` VALUES ('982', '6', '24', '10354');
INSERT INTO `tb_recommend` VALUES ('983', '6', '58', '20414');
INSERT INTO `tb_recommend` VALUES ('984', '6', '27', '10397');
INSERT INTO `tb_recommend` VALUES ('985', '6', '29', '10224');
INSERT INTO `tb_recommend` VALUES ('986', '6', '91', '10362');
INSERT INTO `tb_recommend` VALUES ('987', '6', '30', '10406');
INSERT INTO `tb_recommend` VALUES ('988', '7', '62', '10241');
INSERT INTO `tb_recommend` VALUES ('989', '8', '103', '10397');
INSERT INTO `tb_recommend` VALUES ('990', '8', '3', '10406');
INSERT INTO `tb_recommend` VALUES ('991', '9', '102', '70362');
INSERT INTO `tb_recommend` VALUES ('992', '9', '35', '10241');
INSERT INTO `tb_recommend` VALUES ('993', '9', '5', '40397');
INSERT INTO `tb_recommend` VALUES ('994', '9', '20', '20216');
INSERT INTO `tb_recommend` VALUES ('995', '9', '8', '20406');
INSERT INTO `tb_recommend` VALUES ('996', '9', '11', '10224');
INSERT INTO `tb_recommend` VALUES ('997', '9', '104', '10181');
INSERT INTO `tb_recommend` VALUES ('998', '9', '15', '30155');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `chinesename` varchar(255) DEFAULT NULL,
  `admin` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'lwx481042', null, '路飞', '0000000001');
INSERT INTO `tb_user` VALUES ('2', 'ywx370642', null, '小瑞瑞', '0000000001');
INSERT INTO `tb_user` VALUES ('3', 'ywx349077', null, '闫昊', '0000000001');
INSERT INTO `tb_user` VALUES ('4', 'lwx240886', null, '前江', '0000000001');
INSERT INTO `tb_user` VALUES ('5', 'jwx460266', null, '庆林', '0000000001');
INSERT INTO `tb_user` VALUES ('6', 'wwx416542', null, '剑南', '0000000001');
INSERT INTO `tb_user` VALUES ('7', 'lwx489448', null, '文贤', '0000000001');
INSERT INTO `tb_user` VALUES ('8', 'zwx488633', null, '俊龙', '0000000001');
INSERT INTO `tb_user` VALUES ('9', 'hwx378156', null, '荣华', '0000000001');

-- ----------------------------
-- Table structure for t_base_pinyin
-- ----------------------------
DROP TABLE IF EXISTS `t_base_pinyin`;
CREATE TABLE `t_base_pinyin` (
  `pin_yin_` varchar(255) CHARACTER SET gbk NOT NULL,
  `code_` int(11) NOT NULL,
  PRIMARY KEY (`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_base_pinyin
-- ----------------------------
INSERT INTO `t_base_pinyin` VALUES ('zuo', '10254');
INSERT INTO `t_base_pinyin` VALUES ('zun', '10256');
INSERT INTO `t_base_pinyin` VALUES ('zui', '10260');
INSERT INTO `t_base_pinyin` VALUES ('zuan', '10262');
INSERT INTO `t_base_pinyin` VALUES ('zu', '10270');
INSERT INTO `t_base_pinyin` VALUES ('zou', '10274');
INSERT INTO `t_base_pinyin` VALUES ('zong', '10281');
INSERT INTO `t_base_pinyin` VALUES ('zi', '10296');
INSERT INTO `t_base_pinyin` VALUES ('zhuo', '10307');
INSERT INTO `t_base_pinyin` VALUES ('zhun', '10309');
INSERT INTO `t_base_pinyin` VALUES ('zhui', '10315');
INSERT INTO `t_base_pinyin` VALUES ('zhuang', '10322');
INSERT INTO `t_base_pinyin` VALUES ('zhuan', '10328');
INSERT INTO `t_base_pinyin` VALUES ('zhuai', '10329');
INSERT INTO `t_base_pinyin` VALUES ('zhua', '10331');
INSERT INTO `t_base_pinyin` VALUES ('zhu', '10519');
INSERT INTO `t_base_pinyin` VALUES ('zhou', '10533');
INSERT INTO `t_base_pinyin` VALUES ('zhong', '10544');
INSERT INTO `t_base_pinyin` VALUES ('zhi', '10587');
INSERT INTO `t_base_pinyin` VALUES ('zheng', '10764');
INSERT INTO `t_base_pinyin` VALUES ('zhen', '10780');
INSERT INTO `t_base_pinyin` VALUES ('zhe', '10790');
INSERT INTO `t_base_pinyin` VALUES ('zhao', '10800');
INSERT INTO `t_base_pinyin` VALUES ('zhang', '10815');
INSERT INTO `t_base_pinyin` VALUES ('zhan', '10832');
INSERT INTO `t_base_pinyin` VALUES ('zhai', '10838');
INSERT INTO `t_base_pinyin` VALUES ('zha', '11014');
INSERT INTO `t_base_pinyin` VALUES ('zeng', '11018');
INSERT INTO `t_base_pinyin` VALUES ('zen', '11019');
INSERT INTO `t_base_pinyin` VALUES ('zei', '11020');
INSERT INTO `t_base_pinyin` VALUES ('ze', '11024');
INSERT INTO `t_base_pinyin` VALUES ('zao', '11038');
INSERT INTO `t_base_pinyin` VALUES ('zang', '11041');
INSERT INTO `t_base_pinyin` VALUES ('zan', '11045');
INSERT INTO `t_base_pinyin` VALUES ('zai', '11052');
INSERT INTO `t_base_pinyin` VALUES ('za', '11055');
INSERT INTO `t_base_pinyin` VALUES ('yun', '11067');
INSERT INTO `t_base_pinyin` VALUES ('yue', '11077');
INSERT INTO `t_base_pinyin` VALUES ('yuan', '11097');
INSERT INTO `t_base_pinyin` VALUES ('yu', '11303');
INSERT INTO `t_base_pinyin` VALUES ('you', '11324');
INSERT INTO `t_base_pinyin` VALUES ('yong', '11339');
INSERT INTO `t_base_pinyin` VALUES ('yo', '11340');
INSERT INTO `t_base_pinyin` VALUES ('ying', '11358');
INSERT INTO `t_base_pinyin` VALUES ('yin', '11536');
INSERT INTO `t_base_pinyin` VALUES ('yi', '11589');
INSERT INTO `t_base_pinyin` VALUES ('ye', '11604');
INSERT INTO `t_base_pinyin` VALUES ('yao', '11781');
INSERT INTO `t_base_pinyin` VALUES ('yang', '11798');
INSERT INTO `t_base_pinyin` VALUES ('yan', '11831');
INSERT INTO `t_base_pinyin` VALUES ('ya', '11847');
INSERT INTO `t_base_pinyin` VALUES ('xun', '11861');
INSERT INTO `t_base_pinyin` VALUES ('xue', '11867');
INSERT INTO `t_base_pinyin` VALUES ('xuan', '12039');
INSERT INTO `t_base_pinyin` VALUES ('xu', '12058');
INSERT INTO `t_base_pinyin` VALUES ('xiu', '12067');
INSERT INTO `t_base_pinyin` VALUES ('xiong', '12074');
INSERT INTO `t_base_pinyin` VALUES ('xing', '12089');
INSERT INTO `t_base_pinyin` VALUES ('xin', '12099');
INSERT INTO `t_base_pinyin` VALUES ('xie', '12120');
INSERT INTO `t_base_pinyin` VALUES ('xiao', '12300');
INSERT INTO `t_base_pinyin` VALUES ('xiang', '12320');
INSERT INTO `t_base_pinyin` VALUES ('xian', '12346');
INSERT INTO `t_base_pinyin` VALUES ('xia', '12359');
INSERT INTO `t_base_pinyin` VALUES ('xi', '12556');
INSERT INTO `t_base_pinyin` VALUES ('wu', '12585');
INSERT INTO `t_base_pinyin` VALUES ('wo', '12594');
INSERT INTO `t_base_pinyin` VALUES ('weng', '12597');
INSERT INTO `t_base_pinyin` VALUES ('wen', '12607');
INSERT INTO `t_base_pinyin` VALUES ('wei', '12802');
INSERT INTO `t_base_pinyin` VALUES ('wang', '12812');
INSERT INTO `t_base_pinyin` VALUES ('wan', '12829');
INSERT INTO `t_base_pinyin` VALUES ('wai', '12831');
INSERT INTO `t_base_pinyin` VALUES ('wa', '12838');
INSERT INTO `t_base_pinyin` VALUES ('tuo', '12849');
INSERT INTO `t_base_pinyin` VALUES ('tun', '12852');
INSERT INTO `t_base_pinyin` VALUES ('tui', '12858');
INSERT INTO `t_base_pinyin` VALUES ('tuan', '12860');
INSERT INTO `t_base_pinyin` VALUES ('tu', '12871');
INSERT INTO `t_base_pinyin` VALUES ('tou', '12875');
INSERT INTO `t_base_pinyin` VALUES ('tong', '12888');
INSERT INTO `t_base_pinyin` VALUES ('ting', '13060');
INSERT INTO `t_base_pinyin` VALUES ('tie', '13063');
INSERT INTO `t_base_pinyin` VALUES ('tiao', '13068');
INSERT INTO `t_base_pinyin` VALUES ('tian', '13076');
INSERT INTO `t_base_pinyin` VALUES ('ti', '13091');
INSERT INTO `t_base_pinyin` VALUES ('teng', '13095');
INSERT INTO `t_base_pinyin` VALUES ('te', '13096');
INSERT INTO `t_base_pinyin` VALUES ('tao', '13107');
INSERT INTO `t_base_pinyin` VALUES ('tang', '13120');
INSERT INTO `t_base_pinyin` VALUES ('tan', '13138');
INSERT INTO `t_base_pinyin` VALUES ('tai', '13147');
INSERT INTO `t_base_pinyin` VALUES ('ta', '13318');
INSERT INTO `t_base_pinyin` VALUES ('suo', '13326');
INSERT INTO `t_base_pinyin` VALUES ('sun', '13329');
INSERT INTO `t_base_pinyin` VALUES ('sui', '13340');
INSERT INTO `t_base_pinyin` VALUES ('suan', '13343');
INSERT INTO `t_base_pinyin` VALUES ('su', '13356');
INSERT INTO `t_base_pinyin` VALUES ('sou', '13359');
INSERT INTO `t_base_pinyin` VALUES ('song', '13367');
INSERT INTO `t_base_pinyin` VALUES ('si', '13383');
INSERT INTO `t_base_pinyin` VALUES ('shuo', '13387');
INSERT INTO `t_base_pinyin` VALUES ('shun', '13391');
INSERT INTO `t_base_pinyin` VALUES ('shui', '13395');
INSERT INTO `t_base_pinyin` VALUES ('shuang', '13398');
INSERT INTO `t_base_pinyin` VALUES ('shuan', '13400');
INSERT INTO `t_base_pinyin` VALUES ('shuai', '13404');
INSERT INTO `t_base_pinyin` VALUES ('shua', '13406');
INSERT INTO `t_base_pinyin` VALUES ('shu', '13601');
INSERT INTO `t_base_pinyin` VALUES ('shou', '13611');
INSERT INTO `t_base_pinyin` VALUES ('shi', '13658');
INSERT INTO `t_base_pinyin` VALUES ('sheng', '13831');
INSERT INTO `t_base_pinyin` VALUES ('shen', '13847');
INSERT INTO `t_base_pinyin` VALUES ('she', '13859');
INSERT INTO `t_base_pinyin` VALUES ('shao', '13870');
INSERT INTO `t_base_pinyin` VALUES ('shang', '13878');
INSERT INTO `t_base_pinyin` VALUES ('shan', '13894');
INSERT INTO `t_base_pinyin` VALUES ('shai', '13896');
INSERT INTO `t_base_pinyin` VALUES ('sha', '13905');
INSERT INTO `t_base_pinyin` VALUES ('seng', '13906');
INSERT INTO `t_base_pinyin` VALUES ('sen', '13907');
INSERT INTO `t_base_pinyin` VALUES ('se', '13910');
INSERT INTO `t_base_pinyin` VALUES ('sao', '13914');
INSERT INTO `t_base_pinyin` VALUES ('sang', '13917');
INSERT INTO `t_base_pinyin` VALUES ('san', '14083');
INSERT INTO `t_base_pinyin` VALUES ('sai', '14087');
INSERT INTO `t_base_pinyin` VALUES ('sa', '14090');
INSERT INTO `t_base_pinyin` VALUES ('ruo', '14092');
INSERT INTO `t_base_pinyin` VALUES ('run', '14094');
INSERT INTO `t_base_pinyin` VALUES ('rui', '14097');
INSERT INTO `t_base_pinyin` VALUES ('ruan', '14099');
INSERT INTO `t_base_pinyin` VALUES ('ru', '14109');
INSERT INTO `t_base_pinyin` VALUES ('rou', '14112');
INSERT INTO `t_base_pinyin` VALUES ('rong', '14122');
INSERT INTO `t_base_pinyin` VALUES ('ri', '14123');
INSERT INTO `t_base_pinyin` VALUES ('reng', '14125');
INSERT INTO `t_base_pinyin` VALUES ('ren', '14135');
INSERT INTO `t_base_pinyin` VALUES ('re', '14137');
INSERT INTO `t_base_pinyin` VALUES ('rao', '14140');
INSERT INTO `t_base_pinyin` VALUES ('rang', '14145');
INSERT INTO `t_base_pinyin` VALUES ('ran', '14149');
INSERT INTO `t_base_pinyin` VALUES ('qun', '14151');
INSERT INTO `t_base_pinyin` VALUES ('que', '14159');
INSERT INTO `t_base_pinyin` VALUES ('quan', '14170');
INSERT INTO `t_base_pinyin` VALUES ('qu', '14345');
INSERT INTO `t_base_pinyin` VALUES ('qiu', '14353');
INSERT INTO `t_base_pinyin` VALUES ('qiong', '14355');
INSERT INTO `t_base_pinyin` VALUES ('qing', '14368');
INSERT INTO `t_base_pinyin` VALUES ('qin', '14379');
INSERT INTO `t_base_pinyin` VALUES ('qie', '14384');
INSERT INTO `t_base_pinyin` VALUES ('qiao', '14399');
INSERT INTO `t_base_pinyin` VALUES ('qiang', '14407');
INSERT INTO `t_base_pinyin` VALUES ('qian', '14429');
INSERT INTO `t_base_pinyin` VALUES ('qia', '14594');
INSERT INTO `t_base_pinyin` VALUES ('qi', '14630');
INSERT INTO `t_base_pinyin` VALUES ('pu', '14645');
INSERT INTO `t_base_pinyin` VALUES ('po', '14654');
INSERT INTO `t_base_pinyin` VALUES ('ping', '14663');
INSERT INTO `t_base_pinyin` VALUES ('pin', '14668');
INSERT INTO `t_base_pinyin` VALUES ('pie', '14670');
INSERT INTO `t_base_pinyin` VALUES ('piao', '14674');
INSERT INTO `t_base_pinyin` VALUES ('pian', '14678');
INSERT INTO `t_base_pinyin` VALUES ('pi', '14857');
INSERT INTO `t_base_pinyin` VALUES ('peng', '14871');
INSERT INTO `t_base_pinyin` VALUES ('pen', '14873');
INSERT INTO `t_base_pinyin` VALUES ('pei', '14882');
INSERT INTO `t_base_pinyin` VALUES ('pao', '14889');
INSERT INTO `t_base_pinyin` VALUES ('pang', '14894');
INSERT INTO `t_base_pinyin` VALUES ('pan', '14902');
INSERT INTO `t_base_pinyin` VALUES ('pai', '14908');
INSERT INTO `t_base_pinyin` VALUES ('pa', '14914');
INSERT INTO `t_base_pinyin` VALUES ('ou', '14921');
INSERT INTO `t_base_pinyin` VALUES ('o', '14922');
INSERT INTO `t_base_pinyin` VALUES ('nuo', '14926');
INSERT INTO `t_base_pinyin` VALUES ('nue', '14928');
INSERT INTO `t_base_pinyin` VALUES ('nuan', '14929');
INSERT INTO `t_base_pinyin` VALUES ('nv', '14930');
INSERT INTO `t_base_pinyin` VALUES ('nu', '14933');
INSERT INTO `t_base_pinyin` VALUES ('nong', '14937');
INSERT INTO `t_base_pinyin` VALUES ('niu', '14941');
INSERT INTO `t_base_pinyin` VALUES ('ning', '15109');
INSERT INTO `t_base_pinyin` VALUES ('nin', '15110');
INSERT INTO `t_base_pinyin` VALUES ('nie', '15117');
INSERT INTO `t_base_pinyin` VALUES ('niao', '15119');
INSERT INTO `t_base_pinyin` VALUES ('niang', '15121');
INSERT INTO `t_base_pinyin` VALUES ('nian', '15128');
INSERT INTO `t_base_pinyin` VALUES ('ni', '15139');
INSERT INTO `t_base_pinyin` VALUES ('neng', '15140');
INSERT INTO `t_base_pinyin` VALUES ('nen', '15141');
INSERT INTO `t_base_pinyin` VALUES ('nei', '15143');
INSERT INTO `t_base_pinyin` VALUES ('ne', '15144');
INSERT INTO `t_base_pinyin` VALUES ('nao', '15149');
INSERT INTO `t_base_pinyin` VALUES ('nang', '15150');
INSERT INTO `t_base_pinyin` VALUES ('nan', '15153');
INSERT INTO `t_base_pinyin` VALUES ('nai', '15158');
INSERT INTO `t_base_pinyin` VALUES ('na', '15165');
INSERT INTO `t_base_pinyin` VALUES ('mu', '15180');
INSERT INTO `t_base_pinyin` VALUES ('mou', '15183');
INSERT INTO `t_base_pinyin` VALUES ('mo', '15362');
INSERT INTO `t_base_pinyin` VALUES ('miu', '15363');
INSERT INTO `t_base_pinyin` VALUES ('ming', '15369');
INSERT INTO `t_base_pinyin` VALUES ('min', '15375');
INSERT INTO `t_base_pinyin` VALUES ('mie', '15377');
INSERT INTO `t_base_pinyin` VALUES ('miao', '15385');
INSERT INTO `t_base_pinyin` VALUES ('mian', '15394');
INSERT INTO `t_base_pinyin` VALUES ('mi', '15408');
INSERT INTO `t_base_pinyin` VALUES ('meng', '15416');
INSERT INTO `t_base_pinyin` VALUES ('men', '15419');
INSERT INTO `t_base_pinyin` VALUES ('mei', '15435');
INSERT INTO `t_base_pinyin` VALUES ('me', '15436');
INSERT INTO `t_base_pinyin` VALUES ('mao', '15448');
INSERT INTO `t_base_pinyin` VALUES ('mang', '15454');
INSERT INTO `t_base_pinyin` VALUES ('man', '15625');
INSERT INTO `t_base_pinyin` VALUES ('mai', '15631');
INSERT INTO `t_base_pinyin` VALUES ('ma', '15640');
INSERT INTO `t_base_pinyin` VALUES ('luo', '15652');
INSERT INTO `t_base_pinyin` VALUES ('lun', '15659');
INSERT INTO `t_base_pinyin` VALUES ('lue', '15661');
INSERT INTO `t_base_pinyin` VALUES ('luan', '15667');
INSERT INTO `t_base_pinyin` VALUES ('lv', '15681');
INSERT INTO `t_base_pinyin` VALUES ('lu', '15701');
INSERT INTO `t_base_pinyin` VALUES ('lou', '15707');
INSERT INTO `t_base_pinyin` VALUES ('long', '15878');
INSERT INTO `t_base_pinyin` VALUES ('liu', '15889');
INSERT INTO `t_base_pinyin` VALUES ('ling', '15903');
INSERT INTO `t_base_pinyin` VALUES ('lin', '15915');
INSERT INTO `t_base_pinyin` VALUES ('lie', '15920');
INSERT INTO `t_base_pinyin` VALUES ('liao', '15933');
INSERT INTO `t_base_pinyin` VALUES ('liang', '15944');
INSERT INTO `t_base_pinyin` VALUES ('lian', '15958');
INSERT INTO `t_base_pinyin` VALUES ('lia', '15959');
INSERT INTO `t_base_pinyin` VALUES ('li', '16155');
INSERT INTO `t_base_pinyin` VALUES ('leng', '16158');
INSERT INTO `t_base_pinyin` VALUES ('lei', '16169');
INSERT INTO `t_base_pinyin` VALUES ('le', '16171');
INSERT INTO `t_base_pinyin` VALUES ('lao', '16180');
INSERT INTO `t_base_pinyin` VALUES ('lang', '16187');
INSERT INTO `t_base_pinyin` VALUES ('lan', '16202');
INSERT INTO `t_base_pinyin` VALUES ('lai', '16205');
INSERT INTO `t_base_pinyin` VALUES ('la', '16212');
INSERT INTO `t_base_pinyin` VALUES ('kuo', '16216');
INSERT INTO `t_base_pinyin` VALUES ('kun', '16220');
INSERT INTO `t_base_pinyin` VALUES ('kui', '16393');
INSERT INTO `t_base_pinyin` VALUES ('kuang', '16401');
INSERT INTO `t_base_pinyin` VALUES ('kuan', '16403');
INSERT INTO `t_base_pinyin` VALUES ('kuai', '16407');
INSERT INTO `t_base_pinyin` VALUES ('kua', '16412');
INSERT INTO `t_base_pinyin` VALUES ('ku', '16419');
INSERT INTO `t_base_pinyin` VALUES ('kou', '16423');
INSERT INTO `t_base_pinyin` VALUES ('kong', '16427');
INSERT INTO `t_base_pinyin` VALUES ('keng', '16429');
INSERT INTO `t_base_pinyin` VALUES ('ken', '16433');
INSERT INTO `t_base_pinyin` VALUES ('ke', '16448');
INSERT INTO `t_base_pinyin` VALUES ('kao', '16452');
INSERT INTO `t_base_pinyin` VALUES ('kang', '16459');
INSERT INTO `t_base_pinyin` VALUES ('kan', '16465');
INSERT INTO `t_base_pinyin` VALUES ('kai', '16470');
INSERT INTO `t_base_pinyin` VALUES ('ka', '16474');
INSERT INTO `t_base_pinyin` VALUES ('jun', '16647');
INSERT INTO `t_base_pinyin` VALUES ('jue', '16657');
INSERT INTO `t_base_pinyin` VALUES ('juan', '16664');
INSERT INTO `t_base_pinyin` VALUES ('ju', '16689');
INSERT INTO `t_base_pinyin` VALUES ('jiu', '16706');
INSERT INTO `t_base_pinyin` VALUES ('jiong', '16708');
INSERT INTO `t_base_pinyin` VALUES ('jing', '16733');
INSERT INTO `t_base_pinyin` VALUES ('jin', '16915');
INSERT INTO `t_base_pinyin` VALUES ('jie', '16942');
INSERT INTO `t_base_pinyin` VALUES ('jiao', '16970');
INSERT INTO `t_base_pinyin` VALUES ('jiang', '16983');
INSERT INTO `t_base_pinyin` VALUES ('jian', '17185');
INSERT INTO `t_base_pinyin` VALUES ('jia', '17202');
INSERT INTO `t_base_pinyin` VALUES ('ji', '17417');
INSERT INTO `t_base_pinyin` VALUES ('huo', '17427');
INSERT INTO `t_base_pinyin` VALUES ('hun', '17433');
INSERT INTO `t_base_pinyin` VALUES ('hui', '17454');
INSERT INTO `t_base_pinyin` VALUES ('huang', '17468');
INSERT INTO `t_base_pinyin` VALUES ('huan', '17482');
INSERT INTO `t_base_pinyin` VALUES ('huai', '17487');
INSERT INTO `t_base_pinyin` VALUES ('hua', '17496');
INSERT INTO `t_base_pinyin` VALUES ('hu', '17676');
INSERT INTO `t_base_pinyin` VALUES ('hou', '17683');
INSERT INTO `t_base_pinyin` VALUES ('hong', '17692');
INSERT INTO `t_base_pinyin` VALUES ('heng', '17697');
INSERT INTO `t_base_pinyin` VALUES ('hen', '17701');
INSERT INTO `t_base_pinyin` VALUES ('hei', '17703');
INSERT INTO `t_base_pinyin` VALUES ('he', '17721');
INSERT INTO `t_base_pinyin` VALUES ('hao', '17730');
INSERT INTO `t_base_pinyin` VALUES ('hang', '17733');
INSERT INTO `t_base_pinyin` VALUES ('han', '17752');
INSERT INTO `t_base_pinyin` VALUES ('hai', '17759');
INSERT INTO `t_base_pinyin` VALUES ('ha', '17922');
INSERT INTO `t_base_pinyin` VALUES ('guo', '17928');
INSERT INTO `t_base_pinyin` VALUES ('gun', '17931');
INSERT INTO `t_base_pinyin` VALUES ('gui', '17947');
INSERT INTO `t_base_pinyin` VALUES ('guang', '17950');
INSERT INTO `t_base_pinyin` VALUES ('guan', '17961');
INSERT INTO `t_base_pinyin` VALUES ('guai', '17964');
INSERT INTO `t_base_pinyin` VALUES ('gua', '17970');
INSERT INTO `t_base_pinyin` VALUES ('gu', '17988');
INSERT INTO `t_base_pinyin` VALUES ('gou', '17997');
INSERT INTO `t_base_pinyin` VALUES ('gong', '18012');
INSERT INTO `t_base_pinyin` VALUES ('geng', '18181');
INSERT INTO `t_base_pinyin` VALUES ('gen', '18183');
INSERT INTO `t_base_pinyin` VALUES ('gei', '18184');
INSERT INTO `t_base_pinyin` VALUES ('ge', '18201');
INSERT INTO `t_base_pinyin` VALUES ('gao', '18211');
INSERT INTO `t_base_pinyin` VALUES ('gang', '18220');
INSERT INTO `t_base_pinyin` VALUES ('gan', '18231');
INSERT INTO `t_base_pinyin` VALUES ('gai', '18237');
INSERT INTO `t_base_pinyin` VALUES ('ga', '18239');
INSERT INTO `t_base_pinyin` VALUES ('fu', '18446');
INSERT INTO `t_base_pinyin` VALUES ('fou', '18447');
INSERT INTO `t_base_pinyin` VALUES ('fo', '18448');
INSERT INTO `t_base_pinyin` VALUES ('feng', '18463');
INSERT INTO `t_base_pinyin` VALUES ('fen', '18478');
INSERT INTO `t_base_pinyin` VALUES ('fei', '18490');
INSERT INTO `t_base_pinyin` VALUES ('fang', '18501');
INSERT INTO `t_base_pinyin` VALUES ('fan', '18518');
INSERT INTO `t_base_pinyin` VALUES ('fa', '18526');
INSERT INTO `t_base_pinyin` VALUES ('er', '18696');
INSERT INTO `t_base_pinyin` VALUES ('en', '18697');
INSERT INTO `t_base_pinyin` VALUES ('e', '18710');
INSERT INTO `t_base_pinyin` VALUES ('duo', '18722');
INSERT INTO `t_base_pinyin` VALUES ('dun', '18731');
INSERT INTO `t_base_pinyin` VALUES ('dui', '18735');
INSERT INTO `t_base_pinyin` VALUES ('duan', '18741');
INSERT INTO `t_base_pinyin` VALUES ('du', '18756');
INSERT INTO `t_base_pinyin` VALUES ('dou', '18763');
INSERT INTO `t_base_pinyin` VALUES ('dong', '18773');
INSERT INTO `t_base_pinyin` VALUES ('diu', '18774');
INSERT INTO `t_base_pinyin` VALUES ('ding', '18783');
INSERT INTO `t_base_pinyin` VALUES ('die', '18952');
INSERT INTO `t_base_pinyin` VALUES ('diao', '18961');
INSERT INTO `t_base_pinyin` VALUES ('dian', '18977');
INSERT INTO `t_base_pinyin` VALUES ('di', '18996');
INSERT INTO `t_base_pinyin` VALUES ('deng', '19003');
INSERT INTO `t_base_pinyin` VALUES ('de', '19006');
INSERT INTO `t_base_pinyin` VALUES ('dao', '19018');
INSERT INTO `t_base_pinyin` VALUES ('dang', '19023');
INSERT INTO `t_base_pinyin` VALUES ('dan', '19038');
INSERT INTO `t_base_pinyin` VALUES ('dai', '19212');
INSERT INTO `t_base_pinyin` VALUES ('da', '19218');
INSERT INTO `t_base_pinyin` VALUES ('cuo', '19224');
INSERT INTO `t_base_pinyin` VALUES ('cun', '19227');
INSERT INTO `t_base_pinyin` VALUES ('cui', '19235');
INSERT INTO `t_base_pinyin` VALUES ('cuan', '19238');
INSERT INTO `t_base_pinyin` VALUES ('cu', '19242');
INSERT INTO `t_base_pinyin` VALUES ('cou', '19243');
INSERT INTO `t_base_pinyin` VALUES ('cong', '19249');
INSERT INTO `t_base_pinyin` VALUES ('ci', '19261');
INSERT INTO `t_base_pinyin` VALUES ('chuo', '19263');
INSERT INTO `t_base_pinyin` VALUES ('chun', '19270');
INSERT INTO `t_base_pinyin` VALUES ('chui', '19275');
INSERT INTO `t_base_pinyin` VALUES ('chuang', '19281');
INSERT INTO `t_base_pinyin` VALUES ('chuan', '19288');
INSERT INTO `t_base_pinyin` VALUES ('chuai', '19289');
INSERT INTO `t_base_pinyin` VALUES ('chu', '19467');
INSERT INTO `t_base_pinyin` VALUES ('chou', '19479');
INSERT INTO `t_base_pinyin` VALUES ('chong', '19484');
INSERT INTO `t_base_pinyin` VALUES ('chi', '19500');
INSERT INTO `t_base_pinyin` VALUES ('cheng', '19515');
INSERT INTO `t_base_pinyin` VALUES ('chen', '19525');
INSERT INTO `t_base_pinyin` VALUES ('che', '19531');
INSERT INTO `t_base_pinyin` VALUES ('chao', '19540');
INSERT INTO `t_base_pinyin` VALUES ('chang', '19715');
INSERT INTO `t_base_pinyin` VALUES ('chan', '19725');
INSERT INTO `t_base_pinyin` VALUES ('chai', '19728');
INSERT INTO `t_base_pinyin` VALUES ('cha', '19739');
INSERT INTO `t_base_pinyin` VALUES ('ceng', '19741');
INSERT INTO `t_base_pinyin` VALUES ('ce', '19746');
INSERT INTO `t_base_pinyin` VALUES ('cao', '19751');
INSERT INTO `t_base_pinyin` VALUES ('cang', '19756');
INSERT INTO `t_base_pinyin` VALUES ('can', '19763');
INSERT INTO `t_base_pinyin` VALUES ('cai', '19774');
INSERT INTO `t_base_pinyin` VALUES ('ca', '19775');
INSERT INTO `t_base_pinyin` VALUES ('bu', '19784');
INSERT INTO `t_base_pinyin` VALUES ('bo', '19805');
INSERT INTO `t_base_pinyin` VALUES ('bing', '19976');
INSERT INTO `t_base_pinyin` VALUES ('bin', '19982');
INSERT INTO `t_base_pinyin` VALUES ('bie', '19986');
INSERT INTO `t_base_pinyin` VALUES ('biao', '19990');
INSERT INTO `t_base_pinyin` VALUES ('bian', '20002');
INSERT INTO `t_base_pinyin` VALUES ('bi', '20026');
INSERT INTO `t_base_pinyin` VALUES ('beng', '20032');
INSERT INTO `t_base_pinyin` VALUES ('ben', '20036');
INSERT INTO `t_base_pinyin` VALUES ('bei', '20051');
INSERT INTO `t_base_pinyin` VALUES ('bao', '20230');
INSERT INTO `t_base_pinyin` VALUES ('bang', '20242');
INSERT INTO `t_base_pinyin` VALUES ('ban', '20257');
INSERT INTO `t_base_pinyin` VALUES ('bai', '20265');
INSERT INTO `t_base_pinyin` VALUES ('ba', '20283');
INSERT INTO `t_base_pinyin` VALUES ('ao', '20292');
INSERT INTO `t_base_pinyin` VALUES ('ang', '20295');
INSERT INTO `t_base_pinyin` VALUES ('an', '20304');
INSERT INTO `t_base_pinyin` VALUES ('ai', '20317');
INSERT INTO `t_base_pinyin` VALUES ('a', '20319');

-- ----------------------------
-- Function structure for to_pinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `to_pinyin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `to_pinyin`(NAME VARCHAR(255) CHARSET gbk) RETURNS varchar(255) CHARSET gbk
BEGIN
    DECLARE mycode INT;
    DECLARE tmp_lcode VARCHAR(2) CHARSET gbk;
    DECLARE lcode INT;
    DECLARE tmp_rcode VARCHAR(2) CHARSET gbk;
    DECLARE rcode INT;
    DECLARE mypy VARCHAR(255) CHARSET gbk DEFAULT '';
    DECLARE lp INT;
    SET mycode = 0;
    SET lp = 1;
    SET NAME = HEX(NAME);
    WHILE lp < LENGTH(NAME) DO
        SET tmp_lcode = SUBSTRING(NAME, lp, 2);
        SET lcode = CAST(ASCII(UNHEX(tmp_lcode)) AS UNSIGNED); 
        SET tmp_rcode = SUBSTRING(NAME, lp + 2, 2);
        SET rcode = CAST(ASCII(UNHEX(tmp_rcode)) AS UNSIGNED); 
        IF lcode > 128 THEN
            SET mycode =65536 - lcode * 256 - rcode ;
            SELECT CONCAT(mypy,pin_yin_) INTO mypy FROM t_base_pinyin WHERE CODE_ >= ABS(mycode) ORDER BY CODE_ ASC LIMIT 1;
            SET lp = lp + 4;
        ELSE
            SET mypy = CONCAT(mypy,CHAR(CAST(ASCII(UNHEX(SUBSTRING(NAME, lp, 2))) AS UNSIGNED)));
            SET lp = lp + 2;
        END IF;
    END WHILE;
    RETURN LOWER(mypy);
END
;;
DELIMITER ;
