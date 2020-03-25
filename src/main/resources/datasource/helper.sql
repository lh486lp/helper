/*
Navicat MySQL Data Transfer

Source Server         : xjk
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : helper

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-05 09:08:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accountinfo
-- ----------------------------
DROP TABLE IF EXISTS `accountinfo`;
CREATE TABLE `accountinfo` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `type` int(1) DEFAULT NULL COMMENT '交易类型 1-推广任务 2-邀请奖励 3-任务返佣 4-提现 5-购物',
  `type_id` int(11) DEFAULT NULL COMMENT '交易ID',
  `amount` decimal(9,4) DEFAULT '0.0000' COMMENT '数额',
  `is_gain` int(1) DEFAULT '1' COMMENT '收益 -1-减少 1-增加',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型 1-微信 2-支付宝',
  `img_path` varchar(500) DEFAULT NULL COMMENT '支付图片',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='账户信息表';

-- ----------------------------
-- Records of accountinfo
-- ----------------------------
INSERT INTO `accountinfo` VALUES ('12', '1', '1', null, '0.2000', '1', null, null, '1', '1', '2019-02-18 23:32:18', null);
INSERT INTO `accountinfo` VALUES ('13', '2', '4', null, '80.0000', '1', null, null, '1', '6', '2019-02-23 16:44:31', null);

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(11) DEFAULT NULL COMMENT '收货人手机号',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `area` varchar(100) DEFAULT NULL COMMENT '地市',
  `town` varchar(100) DEFAULT NULL COMMENT '城镇',
  `detail` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `default_addr` int(1) DEFAULT '0' COMMENT '是否默认地址 0-否 1-是',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`addr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '1', '超级管理员', '13333333333', '北京市', '北京市', '东城区', '噶啦胡同', '0', '1', '1', '2019-01-09 20:21:13', '2019-01-17 00:19:58');
INSERT INTO `address` VALUES ('2', '1', '超级管理员', '13333333333', '广东省', '广州市', '天河区', '123', '0', '1', '1', '2019-01-09 20:21:13', '2019-01-17 00:29:14');
INSERT INTO `address` VALUES ('3', '1', '超级管理员', '13333333333', '广东省', '广州市', '白云区', '234', '0', '1', '1', '2019-01-16 23:05:05', '2019-01-17 00:29:30');
INSERT INTO `address` VALUES ('4', '1', '超级管理员', '13333333333', '广东省', '广州市', '增城区', '345', '1', '1', '1', '2019-01-16 23:11:41', '2019-01-17 00:29:43');
INSERT INTO `address` VALUES ('5', '1', '超级管理员', '13333333333', '广东省', '广州市', '花都区', '456', '0', '1', '1', '2019-01-17 00:27:18', '2019-01-17 00:29:54');
INSERT INTO `address` VALUES ('6', '1', '张三', '13978787878', '北京市', '北京市', '朝阳区', '四合院', '0', '1', '1', '2019-01-17 00:27:32', null);
INSERT INTO `address` VALUES ('7', '1', '里死', '13777777777', '北京市', '北京市', '朝阳区', '111111111', '0', '0', '1', '2019-01-17 00:28:07', '2019-01-17 00:28:53');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_title` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `article_content` text COMMENT '内容',
  `img_path` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `article_type` int(1) DEFAULT '1' COMMENT '类型 1-文章 2-公告 3-首页banner 4-商品banner 5-消息',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='文章通告表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '测试标题1', '<section data-role=\"outer\" label=\"Powered by 135editor.com\" style=\"font-family: 微软雅黑;\">\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section data-role=\"paragraph\" class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n            <section style=\"padding: 10px; box-sizing: border-box;\">\n                <section style=\"width: 100%;\">\n                    <section style=\"width: 200px; height: 195px; margin: 0px auto; background-image: url(&quot;https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRJyBYsZcVgTlsbKCUiaeSf2CCmonYic54T5Lw1sa0hiaoNFM44oibzBFgOQ/0?wx_fmt=png&quot;); background-size: 100%; background-repeat: no-repeat; background-position: center center; border: 1px solid transparent; box-sizing: border-box;\">\n                        <section style=\"width: 40px;height: 100px;margin-top: 20px;text-align: center;float: right;margin-right: 66px\">\n                            <p style=\"margin-top: 0px; margin-bottom: 0px; white-space: pre-line; word-break: break-all; overflow-wrap: break-word; font-size: 25px;\" class=\"135brush\">\n                                腊八粥\n                            </p>\n                        </section>\n                    </section>\n                </section>\n            </section>\n        </section>\n    </section>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">周日就是腊八节啦！自古以来，每逢腊八这一天，家家户户都会做腊八粥、泡腊八蒜，合家团聚一起食用，同时馈赠亲朋好友。</span>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">除了煮上一锅美味又营养的腊八粥，和家人一起分享，我们也可以让孩子了解更多腊八节的习俗哦~</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section data-role=\"paragraph\" class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n            <section style=\"padding: 10px; box-sizing: border-box;\">\n                <section style=\"width: 100%;text-align: center;\">\n                    <section style=\"display: inline-block;width: auto;\">\n                        <section style=\"display: flex;display: -webkit-flex;align-items: center;align-items: center;justify-content: center;justify-content: center;\">\n                            <section>\n                                <section style=\"width: 90px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRt1YByIxia2qcibbVh9RzgZ2nXSSBxZhdc47Vz2KAVlV03DMiaQOJJk0iaQ/0?wx_fmt=png\"/>\n                                </section>\n                                <section style=\"width: 45px;margin: -5px auto 0;\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRibPemnnXKC4a26wdaibWDzMNZBBNkSjmRp6UEzaf03BVGlWmdZJfKz6A/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                            <section>\n                                <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 18px;\" class=\"135brush\">\n                                    <span style=\"font-size: 16px;\"><strong>腊八粥节的由来</strong></span>\n                                </p>\n                            </section>\n                            <section style=\"width: 50px;margin-top: 18px\">\n                                <section style=\"width:50px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfR3qKNJLUHbOPmaiavaq42DL2qUfpmNRfTjJLLcVzJicuwviatKZDsbmhfQ/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                        </section>\n                    </section>\n                </section>\n            </section>\n        </section>\n    </section>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #3F3F3F;\">腊八节因腊日而来，是农历腊月最重大的节日，日期为腊月初八，古代称为“腊日”，俗称“腊八节”。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #3F3F3F;\">“腊”的含义有三：</span>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #7F7F7F;\">一曰“腊者，接也”，寓有新旧交替的意思（《隋书·礼仪志》记载）；</span>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #7F7F7F;\">二曰“腊者同猎”，指田猎获取禽兽好祭祖祭神，“腊”从“肉”旁，就是用肉“冬祭”；</span>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #7F7F7F;\">三曰“腊者，逐疫迎春”。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #3F3F3F;\">腊月初八这一天，中国老百姓有吃腊八粥的传统习俗。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #3F3F3F;\">“腊八粥”又叫佛粥、福寿粥、五味粥和七宝粥。据载，中国有的寺院在腊月初八以前，由僧人手持金本盂，到处沿街(途)化缘，将收集的米、粟、枣、麻、果仁等材料造成腊八粥，分发给民众，吃了据说会得到佛祖的保佑，所以人们叫它做“佛粥”。南宋陆游有诗云：“今朝佛粥更相馈，反觉江村节物新。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"letter-spacing: 0.5px; margin: 0em 0.5em; text-align: justify; line-height: 1.75em;\">\n        <span style=\"font-size: 15px; color: #3F3F3F;\">而杭州名刹天宁寺，内有一储藏剩饭的“栈饭楼”，寺僧每日把化缘得来的剩饭曝干，积一年到腊月初八煮粥，供信众享用，称为“福寿粥”。至今，中国东北、西北、江南等地区，人们仍保持着腊八节吃腊八粥的习俗。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section data-role=\"paragraph\" class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n            <section style=\"padding: 10px; box-sizing: border-box;\">\n                <section style=\"width: 100%;text-align: center;\">\n                    <section style=\"display: inline-block;width: auto;\">\n                        <section style=\"display: flex;display: -webkit-flex;align-items: center;align-items: center;justify-content: center;justify-content: center;\">\n                            <section>\n                                <section style=\"width: 90px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRt1YByIxia2qcibbVh9RzgZ2nXSSBxZhdc47Vz2KAVlV03DMiaQOJJk0iaQ/0?wx_fmt=png\"/>\n                                </section>\n                                <section style=\"width: 45px;margin: -5px auto 0;\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRibPemnnXKC4a26wdaibWDzMNZBBNkSjmRp6UEzaf03BVGlWmdZJfKz6A/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                            <section>\n                                <p style=\"margin-top: 0px; margin-bottom: 0px;\" class=\"135brush\">\n                                    <span style=\"font-size: 15px;\"><strong>美味腊八粥如何煮</strong></span>\n                                </p>\n                            </section>\n                            <section style=\"width: 50px;margin-top: 18px\">\n                                <section style=\"width:50px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfR3qKNJLUHbOPmaiavaq42DL2qUfpmNRfTjJLLcVzJicuwviatKZDsbmhfQ/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                        </section>\n                    </section>\n                </section>\n            </section>\n        </section>\n    </section>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <strong><span style=\"font-size: 15px; color: #C00000;\">窍门一：选材</span></strong>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">选材是煮营养美味腊八粥的第一步，不同地区的腊八粥的选材还有所不同。腊八粥种的主料还是米，不过南北方选材有所不同，北方的粳米比较糯，非常适合用于煮腊八粥，而南方的籼米的直链淀粉多，糯性差，故南方的腊八粥常常掺入一些糯米，这样可以增加腊八粥的口感。</span>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">而辅料，则可以根据个人的口味不同，可选择做甜味腊八粥和咸味腊八粥，但一般甜味腊八粥常选用红枣、莲子、桂圆干、杏仁、豆类等果类一起煮，而咸味腊八粥的辅料则常选择各种腊肉、蔬菜、豆类等。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <strong><span style=\"font-size: 15px; color: #C00000;\">窍门二：浸泡</span></strong>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">由于腊八粥的食材种类比较多，涉及种属比较远，所以浸泡这道工序相当重要，而且最好分开浸泡。比如颗粒比较大的芸豆、黑豆、花生、莲子等需要浸泡4-5小时，绿豆、红豆、高粱米等则需要浸泡2小时左右，而糯米、大米、小米等则只需要浸泡半小时或者不用浸泡，这样可以使各种食材能够得到充分泡发，能精简传统煮腊八粥的过程。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <strong><span style=\"font-size: 15px; color: #C00000;\">窍门三：精煮</span></strong>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">传统煮腊八粥需要先煮豆类、花生、红枣等，在放大米、小米、腊肉等一起煮，如果是添加蔬菜的，还要等腊八粥差不多煮熟时再放入蔬菜粒一起煮。现在都简化了，高压锅、电压力锅，将浸泡好的食材放入压力锅中，加入适量的水，点火或者插电就可以等待腊八粥的出锅了，不过如果是蔬菜的话，尤其叶子菜，还是建议等粥煮熟后再放入蔬菜，再一次开火煮3-5分钟。</span>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <br/>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"font-size: 15px; color: #262626;\">但不管怎么样，要想煮出香浓腊八粥，水与食材比例很重要，一般以1:10-15的比例比较合适，而腊八粥中豆类、干果比较多，煮的时间相对比较长，如果大米、小米或者糯米等米类没有浸泡的话，最好选择1:</span><span style=\"font-size: 15px; color: #262626;\">15的比例加水，这样煮出来的粥会比较粘稠。</span>\n    </p>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <br/>\n    </p>\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section data-role=\"paragraph\" class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n            <section style=\"padding: 10px; box-sizing: border-box;\">\n                <section style=\"width: 100%;text-align: center;\">\n                    <section style=\"display: inline-block;width: auto;\">\n                        <section style=\"display: flex;display: -webkit-flex;align-items: center;align-items: center;justify-content: center;justify-content: center;\">\n                            <section>\n                                <section style=\"width: 90px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRt1YByIxia2qcibbVh9RzgZ2nXSSBxZhdc47Vz2KAVlV03DMiaQOJJk0iaQ/0?wx_fmt=png\"/>\n                                </section>\n                                <section style=\"width: 45px;margin: -5px auto 0;\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRibPemnnXKC4a26wdaibWDzMNZBBNkSjmRp6UEzaf03BVGlWmdZJfKz6A/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                            <section>\n                                <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 18px;\" class=\"135brush\">\n                                    <strong><span style=\"font-size: 15px;\">腊八还有这些习俗</span></strong>\n                                </p>\n                            </section>\n                            <section style=\"width: 50px;margin-top: 18px\">\n                                <section style=\"width:50px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfR3qKNJLUHbOPmaiavaq42DL2qUfpmNRfTjJLLcVzJicuwviatKZDsbmhfQ/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                        </section>\n                    </section>\n                </section>\n            </section>\n        </section>\n    </section>\n    <p style=\"line-height: 1.75em; margin: 0em 0.5em; text-align: justify; letter-spacing: 0.5px;\">\n        <span style=\"color: #262626; font-size: 15px; caret-color: red;\">腊八节除了腊八粥，其实腊八节还有很多饮食习俗。</span><br/>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">1、吃冰：据说这天的冰很神奇，吃了它在以后的一年里都不会肚子疼。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">2、腊八蒜：华北大部分地区在腊月初八这天有用醋泡蒜的习俗，叫“腊八蒜”。据老人讲，腊八蒜的蒜字，和“算”字同音，这是各家商号要在这天拢账，把这一年的收支算出来，可以看出盈亏，其中包括外欠和外债，都要在这天算清楚，“腊八算”就是这么回事。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">3、腊八豆：腊八豆是我国湖南省传统食品之一，已有数百年历史，民间多在每年立冬后开始腌制，至腊月八日后食用，故称之为“腊八豆”。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">4、腊八豆腐：是安徽黔县民间风味特产，黔县家家户户都要晒制豆腐，民间将这种自然晒制的豆腐称作“腊八豆腐”。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">5、腊八面：北方一些不产或少产大米的地方，人们不吃腊八粥，而是吃腊八面。隔天用各种果、蔬做成臊子，把面条擀好，到腊月初八早晨全家吃腊八面。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section data-role=\"paragraph\" class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n            <section style=\"padding: 10px; box-sizing: border-box;\">\n                <section style=\"width: 100%;text-align: center;\">\n                    <section style=\"display: inline-block;width: auto;\">\n                        <section style=\"display: flex;display: -webkit-flex;align-items: center;align-items: center;justify-content: center;justify-content: center;\">\n                            <section>\n                                <section style=\"width: 90px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRt1YByIxia2qcibbVh9RzgZ2nXSSBxZhdc47Vz2KAVlV03DMiaQOJJk0iaQ/0?wx_fmt=png\"/>\n                                </section>\n                                <section style=\"width: 45px;margin: -5px auto 0;\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfRibPemnnXKC4a26wdaibWDzMNZBBNkSjmRp6UEzaf03BVGlWmdZJfKz6A/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                            <section>\n                                <p style=\"margin-top: 0px; margin-bottom: 0px; font-size: 18px;\" class=\"135brush\">\n                                    <strong><span style=\"font-size: 15px;\">“瞌睡虫”和“没底锅”</span></strong>\n                                </p>\n                            </section>\n                            <section style=\"width: 50px;margin-top: 18px\">\n                                <section style=\"width:50px\">\n                                    <img src=\"https://mpt.135editor.com/mmbiz_png/uN1LIav7oJ94qPKZ1e56Qa0ASBsUfyfR3qKNJLUHbOPmaiavaq42DL2qUfpmNRfTjJLLcVzJicuwviatKZDsbmhfQ/0?wx_fmt=png\"/>\n                                </section>\n                            </section>\n                        </section>\n                    </section>\n                </section>\n            </section>\n        </section>\n    </section>\n    <p>\n        <br/>\n    </p>\n    <p>\n        <span style=\"font-size: 15px; color: #262626;\">我们还可以和孩子讲讲“瞌睡虫”和“没底锅”的故事。</span>\n    </p>\n    <p>\n        <br/>\n    </p>\n    <section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\">\n        <section style=\"width: 100%;\">\n            <section style=\"width: 100%;border-left: dashed 2px #534a47;background:#f5f5f5;box-sizing: border-box;\">\n                <section class=\"135brush\" style=\"font-size: 14px; text-align: justify; letter-spacing: 1.5px; line-height: 1.75em; color: rgb(63, 62, 63); padding: 0.5em; box-sizing: border-box;\">\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">从前，有一户人家，有爸爸、妈妈和儿子三口人。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">爸爸是个勤快人，60多岁了，还是天天鸡叫起床、天明下地。他常说：“摇钱树，人人有，就是自己两只手。”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">妈妈过日子很节俭，一天三顿精打细算，省吃俭用，她总说：“聚宝盆，不算好，勤俭才是无价宝。”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">他们家粮囤冒尖儿，院子里瓜棚遮天，瓜果蔬菜终年不断。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">老两口这样勤快，可他们的儿子却好吃懒做，整天吃饱了就睡，邻居管他叫“瞌睡虫”。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">老两口年纪越来越大了，爸爸常对儿子说：“要吃饭，得流汗。你不要光睡觉，也得学会种庄稼。”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">妈妈也说：“爹娘只能养你小，不能养你老，你要学会过日子呀！”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">“瞌睡虫”哼两声，这个耳朵听，那个耳朵冒，什么也没有听进去。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">不久，“瞌睡虫”成了家。媳妇和他一样懒：日头不落就睡，日出三竿不起。踢倒了油瓶也不扶，大家叫她“没底锅”。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">过了几年，老两口得了重病，他们把小两口叫到床前，嘱咐说：“要想日子富，鸡叫离床铺，男应勤耕耘，女当多织布……”不久，老两口就先后去世了。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">“瞌睡虫”看着老两口积下的满囤粮食，对媳妇说：“吃不愁，喝不愁，何必种地晒日头？”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">“没底锅”看着老两口留下的满箱衣被，对丈夫说：“冬有棉，夏有单，何必纺织日西偏？”</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">他们俩谁也没把老人的话记在心上，每天照样光吃不干。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">一年一年过去了，老两口留下的地成了荒草园，家里的柴米油盐也用光了。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">这年腊月初八，北方呼啸、大雪封山。小两口没吃没穿，偎在破房子里打哆嗦。这时，他们后悔已经晚了。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">最后，他们找遍了缸底、囤缝、墙边、老鼠洞，好不容易扫出一些黄米、玉米、豆子、花生粒，混在一块煮成粥。谁知端起碗刚想喝，一阵大风刮倒了房子，把两人都压死了。</span>\n                    </p>\n                    <p>\n                        <br/>\n                    </p>\n                    <p>\n                        <span style=\"font-size: 15px; color: #262626;\">后来，每年一到腊八，大人们就熬这么一锅粥给孩子们喝，让他们记住懒人的教训，从小养成勤劳节俭的好习惯。</span>\n                    </p>\n                </section>\n            </section>\n            <section style=\"display: flex;justify-content: flex-end;margin-top: -20px;\">\n                <section style=\"width: 0px; height: 0px; border-bottom: 20px solid rgb(255, 255, 255); border-left: 20px solid transparent; box-sizing: border-box;\">\n                    <br/>\n                </section>\n            </section>\n        </section>\n    </section>\n</section>', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('2', '测试标题2', '测试内容2', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '0', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('3', '测试标题3', '测试内容3', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('4', '测试标题4', '测试内容4', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('5', '测试标题5', '测试内容5', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('6', '测试标题6', '测试内容6', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('7', '测试标题7', '测试内容7', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('8', '测试标题8', '测试内容8', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('9', '测试标题9', '测试内容9', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('10', '测试标题10', '测试内容10', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '1', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('11', '测试banner标题1', '测试banner内容1', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '3', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('12', '测试banner标题2', '测试banner内容2', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '3', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('13', '测试banner标题3', '测试banner内容3', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '4', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('14', '测试banner标题4', '测试banner内容4', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '4', '1', '1', '2019-01-06 15:55:50', null);
INSERT INTO `article` VALUES ('15', '测试后台保存文章', '<p>测试后台保存文章内容<br></p>', '4b7cca5a-9ee8-4389-81bf-6ca6529cf714.jpg', '5', '1', null, '2019-02-14 16:30:36', null);

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(10) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `addr_id` int(11) NOT NULL COMMENT '地址ID',
  `product_id` int(11) NOT NULL COMMENT '商品ID',
  `product_unit_price` double(11,6) DEFAULT NULL COMMENT '成交单价',
  `product_count` int(11) NOT NULL DEFAULT '1' COMMENT '购买数量',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `post_type` varchar(30) DEFAULT NULL COMMENT '快递种类',
  `post_no` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `post_time` datetime DEFAULT NULL COMMENT '快递邮寄时间',
  `status` int(1) DEFAULT '0' COMMENT '订单状态 0-待支付 1-待发货 2-待收货 3-已完成',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '10000001', '1', '1', '1', '8.000000', '1', '2019-01-09 20:19:41', '1', '100000001', '2019-01-09 20:19:53', '3', '1', '1', '2019-01-09 20:20:14', '2019-01-20 17:48:21');
INSERT INTO `orderinfo` VALUES ('2', '10000002', '1', '1', '1', '8.000000', '1', '2019-01-09 20:19:41', '1', '100000001', '2019-01-09 20:19:53', '3', '1', '1', '2019-01-09 20:20:14', '2019-01-20 17:48:25');
INSERT INTO `orderinfo` VALUES ('3', '10000003', '1', '1', '1', '8.000000', '1', '2019-01-09 20:19:41', '1', '100000001', '2019-01-09 20:19:53', '3', '1', '1', '2019-01-09 20:20:14', '2019-01-20 17:48:03');
INSERT INTO `orderinfo` VALUES ('4', '10000004', '1', '1', '1', '8.000000', '1', '2019-01-09 20:19:41', '1', '100000001', '2019-01-09 20:19:53', '3', '1', '1', '2019-01-09 20:20:14', '2019-01-20 17:47:59');
INSERT INTO `orderinfo` VALUES ('5', '10000005', '1', '1', '1', '8.000000', '1', '2019-02-25 23:08:31', '顺丰', '13212312354231', '2019-02-25 23:09:03', '3', '1', '1', '2019-01-17 00:04:06', '2019-01-20 17:45:01');
INSERT INTO `orderinfo` VALUES ('6', '10000006', '1', '4', '8', '8.000000', '1', null, null, null, null, '0', '1', '1', '2019-01-17 21:45:57', '2019-01-17 21:49:26');
INSERT INTO `orderinfo` VALUES ('7', '10000007', '6', '4', '8', '8.000000', '1', null, null, null, null, '0', '0', '1', '2019-01-17 21:46:06', '2019-01-20 17:45:07');
INSERT INTO `orderinfo` VALUES ('8', '10000008', '1', '4', '8', '8.000000', '1', '2019-02-17 22:50:25', '顺丰', '123456789', '2019-02-17 22:50:52', '2', '1', '1', '2019-01-17 21:49:14', '2019-01-20 17:44:51');
INSERT INTO `orderinfo` VALUES ('9', '10000009', '6', '4', '10', '8.000000', '1', '2019-02-23 16:44:31', '', '', null, '1', '1', '1', '2019-02-20 00:03:47', null);
INSERT INTO `orderinfo` VALUES ('10', '10000010', '7', '4', '14', '8.000000', '1', null, null, null, null, '0', '1', '7', '2019-02-26 22:56:14', null);
INSERT INTO `orderinfo` VALUES ('11', '10000011', '7', '4', '10', '8.000000', '2', null, null, null, null, '0', '1', '7', '2019-02-26 23:11:26', null);
INSERT INTO `orderinfo` VALUES ('12', '10000012', '1', '4', '10', '8.000000', '1', null, null, null, null, '0', '1', '1', '2019-03-03 19:08:34', null);
INSERT INTO `orderinfo` VALUES ('13', '10000013', '1', '4', '8', '8.000000', '1', null, null, null, null, '0', '1', '1', '2019-03-03 19:08:54', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_price` double(11,6) DEFAULT NULL COMMENT '价格',
  `preferential_price` double(11,6) DEFAULT NULL COMMENT '优惠价格',
  `img_path` varchar(500) DEFAULT NULL COMMENT '封面图片',
  `img_paths` varchar(1000) DEFAULT NULL COMMENT 'banner图片',
  `specification` varchar(50) DEFAULT NULL COMMENT '规格参数',
  `introduction` varchar(500) DEFAULT NULL COMMENT '商品介绍',
  `content` text COMMENT '图文详情',
  `product_type` int(1) DEFAULT '1' COMMENT '类型 0-推广 1-普通 2-金牌 3-钻石',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '测试商品1', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '0', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('2', '测试商品2', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '0', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('3', '测试商品3', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '1', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('4', '测试商品4', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '71eee3bb-c597-4f3b-bd69-26a901278ec0.png,5bd2b3da-e67b-4bdf-bd44-6d4d959fc087.png,c676caae-1629-4614-ba3d-6130fed62cd1.PNG', '1台', '万能汽车遥控器', '<p><br></p><p><img src=\"/system/sysFile/getFile?fileName=7e894b1d-35ff-480a-ae8a-d52d895080d6.PNG\" style=\"width: 296px;\"></p><p><img src=\"/system/sysFile/getFile?fileName=0af94975-d680-42b7-aa24-831b442f0a0d.PNG\" style=\"width: 296px; float: left;\" class=\"note-float-left\"></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><img src=\"/system/sysFile/getFile?fileName=b498b228-09f9-41a5-814a-535f6f56ed50.PNG\" class=\"note-float-left\" style=\"width: 296px; float: left;\"></p>', '1', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('5', '测试商品5', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '2', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('6', '测试商品6', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '2', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('7', '测试商品7', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '2', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('8', '测试商品8', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '3', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('9', '测试商品9', '10.000000', '8.000000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', null, null, null, null, '3', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('10', '测试商品10', '10.000000', '8.000000', '2d18ded1-70f7-499f-b78d-a67e60f2f02d.jpg', '0fc113ea-0b61-4edf-9b2b-5d69e515d650.jpg,90968ade-57c1-4368-b46f-952de297fe40.png,f2b33a49-705b-4b2f-aca3-89b915796bf4.PNG', '条', '一条两条三条', '<p><img src=\"/system/sysFile/getFile?fileName=8ea48a83-dd0e-4d62-935f-eed1f9f5f29f.png\" style=\"width: 128px;\"></p><p><img src=\"/system/sysFile/getFile?fileName=a9fb0930-26a0-4e13-aa2d-73483e0a4e08.jpg\" style=\"width: 450px;\"></p><p><img src=\"/system/sysFile/getFile?fileName=3baf7481-115d-462f-a953-b53ac630b089.png\" style=\"width: 128px;\"></p><p><img src=\"/system/sysFile/getFile?fileName=979e0102-3618-4852-acc6-32533d15c222.PNG\" style=\"width: 296px;\"><br></p>', '3', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('11', '测试商品11', '10.000000', '8.000000', 'af93e3f2-bed4-4b78-85e5-6121ccd4007c.PNG', '', '', '', '', '0', '1', '1', '2019-01-08 23:12:40', null);
INSERT INTO `product` VALUES ('14', '测试后台保存商品1', '10.000000', '8.000000', 'b9b7bd7f-3ff0-47b2-92e6-74f8908b12e2.jpg', '03619e05-c4a4-4f21-8d8a-1e1fcf1e1dda.jpg,398f518b-08b8-4733-87b5-3d982a6f470a.png,cd03cdec-ad22-43f4-b81a-dc74c54a8c1c.png,5823ea93-ad14-4d93-8f1a-af1fac2ab5f0.PNG', '台', '抢单机器', '<p>图文详情<br><img src=\"/system/sysFile/getFile?fileName=/data/helper/upload/3259923b-61fb-4a0b-aa6d-de119c6a3630.jpg\" style=\"width: 444px;\"><br></p>', '2', '1', '1', '2019-02-16 22:53:23', null);

-- ----------------------------
-- Table structure for systeminfo
-- ----------------------------
DROP TABLE IF EXISTS `systeminfo`;
CREATE TABLE `systeminfo` (
  `_key` varchar(50) NOT NULL DEFAULT '',
  `value` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of systeminfo
-- ----------------------------
INSERT INTO `systeminfo` VALUES ('cashqq1', '123456', '现金QQ1', '1', '1', '2019-01-16 21:56:01', null);
INSERT INTO `systeminfo` VALUES ('cashqq2', '654321', '现金QQ2', '1', '1', '2019-01-16 21:56:11', null);
INSERT INTO `systeminfo` VALUES ('complainqq1', '123456', '投诉QQ1', '1', '1', '2019-01-16 21:56:52', null);
INSERT INTO `systeminfo` VALUES ('complainqq2', '654321', '投诉QQ2', '1', '1', '2019-01-16 21:57:05', null);
INSERT INTO `systeminfo` VALUES ('diamond_castellan_count', '20', '升级钻石城主钻石粉丝的数量', '1', '1', '2019-02-19 22:27:37', null);
INSERT INTO `systeminfo` VALUES ('diamond_castellan_reward', '20', '钻石城主团队新增一名钻石成员，城主获得的奖励', '1', '1', '2019-02-19 22:27:39', null);
INSERT INTO `systeminfo` VALUES ('diamond_invitation_reward', '80', '钻石推广奖励', '1', '1', '2019-02-18 21:38:44', null);
INSERT INTO `systeminfo` VALUES ('diamond_reward_price', '40', '钻石任务奖励', '1', '1', '2019-01-09 23:34:51', null);
INSERT INTO `systeminfo` VALUES ('diamond_task_count', '2', '每日钻石任务限制数量', '1', '1', '2019-01-11 00:30:28', null);
INSERT INTO `systeminfo` VALUES ('file_host', 'http://47.95.215.52:8080', '文件服务地址', '1', '1', '2019-02-23 23:20:26', null);
INSERT INTO `systeminfo` VALUES ('gold_castellan_count', '20', '升级金牌城主粉丝的数量', '1', '1', '2019-02-19 22:27:37', null);
INSERT INTO `systeminfo` VALUES ('gold_castellan_reward', '20', '金牌城主团队新增一名成员，城主获得的奖励', '1', '1', '2019-02-19 22:27:39', null);
INSERT INTO `systeminfo` VALUES ('gold_invitation_reward', '8', '金牌推广奖励', '1', '1', '2019-02-18 21:38:22', null);
INSERT INTO `systeminfo` VALUES ('gold_reward_price', '4', '金牌任务奖励', '1', '1', '2019-01-09 23:33:21', null);
INSERT INTO `systeminfo` VALUES ('gold_task_count', '3', '每日金牌任务限制数量', '1', '1', '2019-01-11 00:30:13', null);
INSERT INTO `systeminfo` VALUES ('orderqq1', '123456', '订单QQ1', '1', '1', '2019-01-16 21:56:28', null);
INSERT INTO `systeminfo` VALUES ('orderqq2', '654321', '订单QQ2', '1', '1', '2019-01-16 21:56:37', null);
INSERT INTO `systeminfo` VALUES ('systemqq1', '123456', '客服QQ1', '1', '1', '2019-01-16 21:49:57', null);
INSERT INTO `systeminfo` VALUES ('systemqq2', '654321', '客服QQ2', '1', '1', '2019-01-16 21:49:57', null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('121', '编码', 'code', 'hobby', '爱好', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('122', '绘画', 'painting', 'hobby', '爱好', null, null, null, null, null, null, '', '');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('110', '0', '/files/d64a62e3-6821-48f1-bac6-a1b9945f4afb.jpg', '2017-10-14 16:20:17');
INSERT INTO `sys_file` VALUES ('111', '0', '/files/aa2c3dc6-495f-48cc-8e12-446eceb2535e.jpg', '2017-10-14 16:20:21');
INSERT INTO `sys_file` VALUES ('118', '0', '/files/a973499e-3ec7-4d43-8a52-b6f6517c77e3.jpg', '2017-10-20 11:53:52');
INSERT INTO `sys_file` VALUES ('132', '0', '/files/e6f13526-e31c-4ebe-a3cf-5fd88dd10be6.jpg', '2017-12-18 20:19:51');
INSERT INTO `sys_file` VALUES ('134', '0', '/files/cd016e72-77f7-4425-afe2-b79dfbdc3ae9.jpeg', '2017-12-18 22:44:07');
INSERT INTO `sys_file` VALUES ('138', '0', '/files/9ec12ee7-65b5-4cc5-9900-d2ec6185b336.jpg', '2017-12-19 19:55:27');
INSERT INTO `sys_file` VALUES ('139', '0', '/files/f6aab9d4-00a1-4bc8-a695-40fc472d4ea9.jpg', '2018-01-02 19:53:24');
INSERT INTO `sys_file` VALUES ('141', '0', '/files/445c5d98-b3b6-4226-a5af-249d1b2a6a34.jpg', '2018-01-09 19:28:53');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', 'sys/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', 'sys/role', 'sys:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0', '2017-08-14 10:51:35', null);
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0', '2017-08-14 10:52:06', null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0', '2017-08-14 10:56:37', null);
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0', '2017-08-14 10:59:32', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null);
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null);
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO `sys_menu` VALUES ('27', '91', '系统日志', 'common/log', 'common:log', '1', 'fa fa-warning', '0', '2017-08-14 22:11:53', null);
INSERT INTO `sys_menu` VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO `sys_menu` VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO `sys_menu` VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '0', '2017-08-14 22:31:02', null);
INSERT INTO `sys_menu` VALUES ('48', '77', '代码生成', 'sys/generator', 'system:generator', '1', 'fa fa-code', '3', null, null);
INSERT INTO `sys_menu` VALUES ('49', '0', '博客管理', '', '', '0', 'fa fa-rss', '6', null, null);
INSERT INTO `sys_menu` VALUES ('50', '49', '文章列表', 'blog/bContent', 'blog:bContent:bContent', '1', 'fa fa-file-image-o', '1', null, null);
INSERT INTO `sys_menu` VALUES ('51', '50', '新增', '', 'blog:bContent:add', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('57', '91', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', null, null);
INSERT INTO `sys_menu` VALUES ('58', '50', '编辑', '', 'blog:bContent:edit', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('59', '50', '删除', '', 'blog:bContent:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('60', '50', '批量删除', '', 'blog:bContent:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('68', '49', '发布文章', '/blog/bContent/add', 'blog:bContent:add', '1', 'fa fa-edit', '0', null, null);
INSERT INTO `sys_menu` VALUES ('71', '1', '文件管理', '/common/sysFile', 'common:sysFile:sysFile', '1', 'fa fa-folder-open', '2', null, null);
INSERT INTO `sys_menu` VALUES ('72', '77', '计划任务', 'system/job', 'common:taskScheduleJob', '1', 'fa fa-hourglass-1', '4', null, null);
INSERT INTO `sys_menu` VALUES ('73', '3', '部门管理', '/system/sysDept', 'system:sysDept:sysDept', '1', 'fa fa-users', '3', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '增加', '/system/sysDept/add', 'system:sysDept:add', '2', null, '1', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '刪除', 'system/sysDept/remove', 'system:sysDept:remove', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '/system/sysDept/edit', 'system:sysDept:edit', '2', null, '3', null, null);
INSERT INTO `sys_menu` VALUES ('77', '0', '系统工具', '', '', '0', 'fa fa-gear', '4', null, null);
INSERT INTO `sys_menu` VALUES ('78', '1', '数据字典', '/common/dict', 'common:dict:dict', '1', 'fa fa-book', '1', null, null);
INSERT INTO `sys_menu` VALUES ('79', '78', '增加', '/common/dict/add', 'common:dict:add', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('80', '78', '编辑', '/common/dict/edit', 'common:dict:edit', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('81', '78', '删除', '/common/dict/remove', 'common:dict:remove', '2', '', '3', null, null);
INSERT INTO `sys_menu` VALUES ('83', '78', '批量删除', '/common/dict/batchRemove', 'common:dict:batchRemove', '2', '', '4', null, null);
INSERT INTO `sys_menu` VALUES ('84', '0', '办公管理', '', '', '0', 'fa fa-laptop', '5', null, null);
INSERT INTO `sys_menu` VALUES ('85', '84', '通知公告', 'oa/notify', 'oa:notify:notify', '1', 'fa fa-pencil-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('86', '85', '新增', 'oa/notify/add', 'oa:notify:add', '2', 'fa fa-plus', '1', null, null);
INSERT INTO `sys_menu` VALUES ('87', '85', '编辑', 'oa/notify/edit', 'oa:notify:edit', '2', 'fa fa-pencil-square-o', '2', null, null);
INSERT INTO `sys_menu` VALUES ('88', '85', '删除', 'oa/notify/remove', 'oa:notify:remove', '2', 'fa fa-minus', null, null, null);
INSERT INTO `sys_menu` VALUES ('89', '85', '批量删除', 'oa/notify/batchRemove', 'oa:notify:batchRemove', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('90', '84', '我的通知', 'oa/notify/selfNotify', '', '1', 'fa fa-envelope-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('91', '0', '系统监控', '', '', '0', 'fa fa-video-camera', '5', null, null);
INSERT INTO `sys_menu` VALUES ('92', '91', '在线用户', 'sys/online', '', '1', 'fa fa-user', null, null, null);
INSERT INTO `sys_menu` VALUES ('93', '0', '工作流程', '', '', '0', 'fa fa-print', '6', null, null);
INSERT INTO `sys_menu` VALUES ('94', '93', '模型管理', 'activiti/model', '', '1', 'fa fa-sort-amount-asc', null, null, null);
INSERT INTO `sys_menu` VALUES ('95', '94', '全部权限', '', 'activiti:model', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('96', '93', '流程管理', 'activiti/process', '', '1', 'fa fa-flag', null, null, null);
INSERT INTO `sys_menu` VALUES ('97', '0', '图表管理', '', '', '0', 'fa fa-bar-chart', '7', null, null);
INSERT INTO `sys_menu` VALUES ('98', '97', '百度chart', '/chart/graph_echarts.html', '', '1', 'fa fa-area-chart', null, null, null);
INSERT INTO `sys_menu` VALUES ('99', '96', '所有权限', '', 'act:process', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('101', '93', '待办任务', 'activiti/task/todo', '', '1', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('104', '77', 'swagger', '/swagger-ui.html', '', '1', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('105', '0', '微广助手', '', '', '0', 'fa fa-hand-paper-o', '8', null, null);
INSERT INTO `sys_menu` VALUES ('106', '105', '账户管理', '/system/accountinfo', '', '1', '', '0', null, null);
INSERT INTO `sys_menu` VALUES ('107', '105', '地址管理', '/system/address', '', '1', '', '1', null, null);
INSERT INTO `sys_menu` VALUES ('108', '105', '内容管理', '/system/article', '', '1', '', '2', null, null);
INSERT INTO `sys_menu` VALUES ('109', '105', '订单管理', '/system/orderinfo', '', '1', '', '3', null, null);
INSERT INTO `sys_menu` VALUES ('110', '105', '商品管理', '/system/product', '', '1', '', '4', null, null);
INSERT INTO `sys_menu` VALUES ('111', '105', '系统配置', '/system/systeminfo', '', '1', '', '5', null, null);
INSERT INTO `sys_menu` VALUES ('112', '105', '任务管理', '/system/task', '', '1', '', '6', null, null);
INSERT INTO `sys_menu` VALUES ('113', '105', '任务记录', '/system/taskRecord', '', '1', '', '7', null, null);
INSERT INTO `sys_menu` VALUES ('114', '105', '会员管理', '/system/userinfo', '', '1', '', '8', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES ('59', '普通用户', null, '基本用户权限', null, null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3545 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '44', '1');
INSERT INTO `sys_role_menu` VALUES ('368', '44', '32');
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33');
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34');
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35');
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28');
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29');
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30');
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38');
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4');
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27');
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38');
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3');
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20');
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21');
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22');
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23');
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11');
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12');
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13');
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14');
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24');
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25');
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26');
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15');
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2');
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6');
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7');
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38');
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42');
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38');
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39');
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40');
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41');
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4');
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32');
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33');
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34');
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35');
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27');
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28');
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29');
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30');
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53');
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2');
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6');
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7');
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3');
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50');
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49');
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1');
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28');
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29');
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30');
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27');
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57');
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71');
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48');
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72');
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1');
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7');
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55');
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56');
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62');
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15');
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2');
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61');
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20');
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21');
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22');
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68');
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60');
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59');
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58');
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51');
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50');
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49');
INSERT INTO `sys_role_menu` VALUES ('2243', '48', '72');
INSERT INTO `sys_role_menu` VALUES ('2247', '63', '-1');
INSERT INTO `sys_role_menu` VALUES ('2248', '63', '84');
INSERT INTO `sys_role_menu` VALUES ('2249', '63', '85');
INSERT INTO `sys_role_menu` VALUES ('2250', '63', '88');
INSERT INTO `sys_role_menu` VALUES ('2251', '63', '87');
INSERT INTO `sys_role_menu` VALUES ('2252', '64', '84');
INSERT INTO `sys_role_menu` VALUES ('2253', '64', '89');
INSERT INTO `sys_role_menu` VALUES ('2254', '64', '88');
INSERT INTO `sys_role_menu` VALUES ('2255', '64', '87');
INSERT INTO `sys_role_menu` VALUES ('2256', '64', '86');
INSERT INTO `sys_role_menu` VALUES ('2257', '64', '85');
INSERT INTO `sys_role_menu` VALUES ('2258', '65', '89');
INSERT INTO `sys_role_menu` VALUES ('2259', '65', '88');
INSERT INTO `sys_role_menu` VALUES ('2260', '65', '86');
INSERT INTO `sys_role_menu` VALUES ('2262', '67', '48');
INSERT INTO `sys_role_menu` VALUES ('2263', '68', '88');
INSERT INTO `sys_role_menu` VALUES ('2264', '68', '87');
INSERT INTO `sys_role_menu` VALUES ('2265', '69', '89');
INSERT INTO `sys_role_menu` VALUES ('2266', '69', '88');
INSERT INTO `sys_role_menu` VALUES ('2267', '69', '86');
INSERT INTO `sys_role_menu` VALUES ('2268', '69', '87');
INSERT INTO `sys_role_menu` VALUES ('2269', '69', '85');
INSERT INTO `sys_role_menu` VALUES ('2270', '69', '84');
INSERT INTO `sys_role_menu` VALUES ('2271', '70', '85');
INSERT INTO `sys_role_menu` VALUES ('2272', '70', '89');
INSERT INTO `sys_role_menu` VALUES ('2273', '70', '88');
INSERT INTO `sys_role_menu` VALUES ('2274', '70', '87');
INSERT INTO `sys_role_menu` VALUES ('2275', '70', '86');
INSERT INTO `sys_role_menu` VALUES ('2276', '70', '84');
INSERT INTO `sys_role_menu` VALUES ('2277', '71', '87');
INSERT INTO `sys_role_menu` VALUES ('2278', '72', '59');
INSERT INTO `sys_role_menu` VALUES ('2279', '73', '48');
INSERT INTO `sys_role_menu` VALUES ('2280', '74', '88');
INSERT INTO `sys_role_menu` VALUES ('2281', '74', '87');
INSERT INTO `sys_role_menu` VALUES ('2282', '75', '88');
INSERT INTO `sys_role_menu` VALUES ('2283', '75', '87');
INSERT INTO `sys_role_menu` VALUES ('2284', '76', '85');
INSERT INTO `sys_role_menu` VALUES ('2285', '76', '89');
INSERT INTO `sys_role_menu` VALUES ('2286', '76', '88');
INSERT INTO `sys_role_menu` VALUES ('2287', '76', '87');
INSERT INTO `sys_role_menu` VALUES ('2288', '76', '86');
INSERT INTO `sys_role_menu` VALUES ('2289', '76', '84');
INSERT INTO `sys_role_menu` VALUES ('2292', '78', '88');
INSERT INTO `sys_role_menu` VALUES ('2293', '78', '87');
INSERT INTO `sys_role_menu` VALUES ('2294', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2295', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2296', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2308', '80', '87');
INSERT INTO `sys_role_menu` VALUES ('2309', '80', '86');
INSERT INTO `sys_role_menu` VALUES ('2310', '80', '-1');
INSERT INTO `sys_role_menu` VALUES ('2311', '80', '84');
INSERT INTO `sys_role_menu` VALUES ('2312', '80', '85');
INSERT INTO `sys_role_menu` VALUES ('2328', '79', '72');
INSERT INTO `sys_role_menu` VALUES ('2329', '79', '48');
INSERT INTO `sys_role_menu` VALUES ('2330', '79', '77');
INSERT INTO `sys_role_menu` VALUES ('2331', '79', '84');
INSERT INTO `sys_role_menu` VALUES ('2332', '79', '89');
INSERT INTO `sys_role_menu` VALUES ('2333', '79', '88');
INSERT INTO `sys_role_menu` VALUES ('2334', '79', '87');
INSERT INTO `sys_role_menu` VALUES ('2335', '79', '86');
INSERT INTO `sys_role_menu` VALUES ('2336', '79', '85');
INSERT INTO `sys_role_menu` VALUES ('2337', '79', '-1');
INSERT INTO `sys_role_menu` VALUES ('2338', '77', '89');
INSERT INTO `sys_role_menu` VALUES ('2339', '77', '88');
INSERT INTO `sys_role_menu` VALUES ('2340', '77', '87');
INSERT INTO `sys_role_menu` VALUES ('2341', '77', '86');
INSERT INTO `sys_role_menu` VALUES ('2342', '77', '85');
INSERT INTO `sys_role_menu` VALUES ('2343', '77', '84');
INSERT INTO `sys_role_menu` VALUES ('2344', '77', '72');
INSERT INTO `sys_role_menu` VALUES ('2345', '77', '-1');
INSERT INTO `sys_role_menu` VALUES ('2346', '77', '77');
INSERT INTO `sys_role_menu` VALUES ('2974', '57', '93');
INSERT INTO `sys_role_menu` VALUES ('2975', '57', '99');
INSERT INTO `sys_role_menu` VALUES ('2976', '57', '95');
INSERT INTO `sys_role_menu` VALUES ('2977', '57', '101');
INSERT INTO `sys_role_menu` VALUES ('2978', '57', '96');
INSERT INTO `sys_role_menu` VALUES ('2979', '57', '94');
INSERT INTO `sys_role_menu` VALUES ('2980', '57', '-1');
INSERT INTO `sys_role_menu` VALUES ('2981', '58', '93');
INSERT INTO `sys_role_menu` VALUES ('2982', '58', '99');
INSERT INTO `sys_role_menu` VALUES ('2983', '58', '95');
INSERT INTO `sys_role_menu` VALUES ('2984', '58', '101');
INSERT INTO `sys_role_menu` VALUES ('2985', '58', '96');
INSERT INTO `sys_role_menu` VALUES ('2986', '58', '94');
INSERT INTO `sys_role_menu` VALUES ('2987', '58', '-1');
INSERT INTO `sys_role_menu` VALUES ('3483', '59', '105');
INSERT INTO `sys_role_menu` VALUES ('3484', '59', '113');
INSERT INTO `sys_role_menu` VALUES ('3485', '59', '112');
INSERT INTO `sys_role_menu` VALUES ('3486', '59', '111');
INSERT INTO `sys_role_menu` VALUES ('3487', '59', '110');
INSERT INTO `sys_role_menu` VALUES ('3488', '59', '109');
INSERT INTO `sys_role_menu` VALUES ('3489', '59', '108');
INSERT INTO `sys_role_menu` VALUES ('3490', '59', '107');
INSERT INTO `sys_role_menu` VALUES ('3491', '59', '106');
INSERT INTO `sys_role_menu` VALUES ('3492', '59', '-1');
INSERT INTO `sys_role_menu` VALUES ('3513', '1', '114');
INSERT INTO `sys_role_menu` VALUES ('3514', '1', '113');
INSERT INTO `sys_role_menu` VALUES ('3515', '1', '112');
INSERT INTO `sys_role_menu` VALUES ('3516', '1', '111');
INSERT INTO `sys_role_menu` VALUES ('3517', '1', '110');
INSERT INTO `sys_role_menu` VALUES ('3518', '1', '109');
INSERT INTO `sys_role_menu` VALUES ('3519', '1', '108');
INSERT INTO `sys_role_menu` VALUES ('3520', '1', '107');
INSERT INTO `sys_role_menu` VALUES ('3521', '1', '106');
INSERT INTO `sys_role_menu` VALUES ('3522', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('3523', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('3524', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('3525', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('3526', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('3527', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('3528', '1', '105');
INSERT INTO `sys_role_menu` VALUES ('3529', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3530', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('3531', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('3532', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('3533', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('3534', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('3535', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('3536', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('3537', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('3538', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('3539', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('3540', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('3541', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('3542', '1', '-1');
INSERT INTO `sys_role_menu` VALUES ('3543', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('3544', '1', '3');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('2', '0 0 0 * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '1', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `img_path` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '$2a$10$wPCGDJB5badWDdSVzg6f0On8tc2yHg83gNJs63WOu85Gw7sliR/TW', '6', 'admin@example.com', '17699999999', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '138', 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区', null);
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@bootdo.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('36', 'ldh', '刘德华', 'bfd9394475754fbe45866eba97738c36', '7', 'ldh@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('123', 'zxy', '张学友', '35174ba93f5fe7267f1fb3c1bf903781', '6', 'zxy@bootdo', null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('124', 'wyf', '吴亦凡', 'e179e6f687bbd57b9d7efc4746c8090a', '6', 'wyf@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('130', 'lh', '鹿晗', '7924710cd673f68967cde70e188bb097', '9', 'lh@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('131', 'lhc', '令狐冲', 'd515538e17ecb570ba40344b5618f5d4', '6', 'lhc@bootdo.com', null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('132', 'lyf', '刘亦菲', '7fdb1d9008f45950c1620ba0864e5fbd', '13', 'lyf@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('134', 'lyh', '李彦宏', 'dc26092b3244d9d432863f2738180e19', '8', 'lyh@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('135', 'wjl', '王健林', '3967697dfced162cf6a34080259b83aa', '6', 'wjl@bootod.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('136', 'gdg', '郭德纲', '3bb1bda86bc02bf6478cd91e42135d2f', '9', 'gdg@bootdo.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_plus
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_plus`;
CREATE TABLE `sys_user_plus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `payment` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_plus
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '59');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');
INSERT INTO `sys_user_role` VALUES ('125', '132', '52');
INSERT INTO `sys_user_role` VALUES ('126', '132', '49');
INSERT INTO `sys_user_role` VALUES ('127', '123', '48');
INSERT INTO `sys_user_role` VALUES ('132', '36', '48');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_type` int(1) DEFAULT '0' COMMENT '任务类型 0-微广推手 1-其他任务',
  `task_title` varchar(100) DEFAULT NULL COMMENT '任务标题',
  `task_content` text COMMENT '任务内容',
  `task_level` int(1) DEFAULT NULL COMMENT '任务等级 2-金牌 3-钻石',
  `reward` double(10,6) DEFAULT NULL COMMENT '任务奖励',
  `img_paths` text COMMENT '任务图片',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='任务表';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '0', '测试金牌任务标题1', '测试金牌任务内容1', '2', '0.200000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '2018-12-01 22:17:31', '2019-02-28 22:17:40', '1', '1', '2019-01-09 22:17:49', null);
INSERT INTO `task` VALUES ('2', '0', '测试钻石任务标题1', '测试钻石任务内容1', '3', '0.500000', '83e73ba4-c438-4671-ae51-549f78df2c11.jpg,a75bbc5e-60e9-463b-bc2e-81dbf337ac41.png,6f24907a-f6db-4264-8222-dddf56903bce.PNG', '2018-12-01 22:18:39', '2019-02-28 22:18:45', '1', '1', '2019-01-09 22:18:51', null);
INSERT INTO `task` VALUES ('3', '0', '测试金牌任务标题2', '测试金牌任务内容1', '2', '0.200000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '2018-12-01 22:17:31', '2019-02-28 22:17:40', '1', '1', '2019-01-09 22:17:49', null);
INSERT INTO `task` VALUES ('4', '0', '测试钻石任务标题2', '测试钻石任务内容1', '3', '0.500000', 'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg,https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6090954ff1039245beb5e70fb795a4a8/b8014a90f603738d0a296fa0be1bb051f919ecd5.jpg', '2018-12-01 22:18:39', '2019-02-28 22:18:45', '1', '1', '2019-01-09 22:18:51', null);

-- ----------------------------
-- Table structure for task_record
-- ----------------------------
DROP TABLE IF EXISTS `task_record`;
CREATE TABLE `task_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `task_id` int(11) NOT NULL COMMENT '任务id',
  `issue` varchar(8) NOT NULL DEFAULT '' COMMENT '任务期数',
  `reward` double(10,6) DEFAULT NULL COMMENT '任务赏金',
  `img_path` varchar(100) DEFAULT NULL COMMENT '任务图片',
  `status` int(1) DEFAULT '0' COMMENT '状态 0-进行中 1-待审核 2-已失效 3-已完成',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`record_id`) USING BTREE,
  UNIQUE KEY `uq_user_task_issue` (`user_id`,`task_id`,`issue`) USING BTREE COMMENT '用户+任务+期数 组合唯一约束'
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='任务记录表';

-- ----------------------------
-- Records of task_record
-- ----------------------------
INSERT INTO `task_record` VALUES ('8', '1', '1', '20190218', '0.200000', '3fab7729-f21f-4b0b-a6a9-1edddfe69236.jpg', '3', '1', '1', '2019-02-18 10:26:19', '2019-02-18 23:31:48');
INSERT INTO `task_record` VALUES ('9', '1', '2', '20190218', '0.500000', null, '0', '1', '1', '2019-02-18 10:26:53', null);
INSERT INTO `task_record` VALUES ('10', '1', '3', '20190218', '0.200000', null, '0', '1', '1', '2019-02-18 23:36:23', null);
INSERT INTO `task_record` VALUES ('11', '1', '4', '20190218', '0.500000', null, '0', '1', '1', '2019-02-18 23:36:53', null);
INSERT INTO `task_record` VALUES ('12', '7', '2', '20190226', '0.500000', null, '0', '1', '7', '2019-02-26 23:30:15', null);
INSERT INTO `task_record` VALUES ('13', '7', '1', '20190226', '0.200000', null, '0', '1', '7', '2019-02-26 23:31:07', null);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `parent_id` int(11) DEFAULT NULL COMMENT '师父id',
  `username` varchar(12) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `account_password` varchar(100) DEFAULT NULL COMMENT '资金密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `img_path` varchar(255) DEFAULT NULL COMMENT '头像',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `rcode` varchar(10) DEFAULT NULL COMMENT '推广码',
  `vip_level` int(1) NOT NULL DEFAULT '1' COMMENT '等级 1-会员 2-金牌 3-钻石',
  `castellan_level` int(1) NOT NULL DEFAULT '0' COMMENT '城主类型 1-非城主 2-金牌城主 3-钻石城主',
  `visible` int(1) DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
  `ops_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '0', '13999999999', '$2a$10$tbLMZdKYkYGb6jwWIKMGG.9oJmQMn0iS5nDO8KArY2AifEmVxMIGa', '$2a$10$tPXFURhcDR8GsifVLi1OBeWR6QIEbIyX6R3tf4cMUfFRG0YMe078q', '超级用户', null, null, '13999999999', '1111', '0', '0', '1', '1', '2019-01-06 15:33:39', null);
INSERT INTO `userinfo` VALUES ('2', '1', '13888888888', '$2a$10$zmJvExgmf2maZsSEsqj19OxIw.UdQjoyhr.QDGF2S1r6oRFzKsil.', null, '一代测试', null, null, '13888888888', '10000002', '3', '0', '1', '1', '2019-01-06 15:51:50', null);
INSERT INTO `userinfo` VALUES ('3', '1', '13777777777', '$2a$10$sq/7D8H44aghdu5X8BG1Supyv4/oXFJGA3TPn6GiCQZtyul3FSHMG', null, '13777777777', null, null, '13777777777', '10000003', '2', '0', '1', null, '2019-01-15 23:10:47', null);
INSERT INTO `userinfo` VALUES ('4', '1', '13666666666', '$2a$10$Z4xzyUCyZGd/d1Ayl3bXZe4D0IQhT7WcxvM3DdjTflbEDk9xYrH6G', null, '13666666666', null, null, '13666666666', '10000004', '3', '0', '1', null, '2019-01-15 23:13:45', null);
INSERT INTO `userinfo` VALUES ('5', '2', '13555555555', '$2a$10$VV3AQu15h/BumBwqEupZFOCrcIlc5Zol2BH4FUYQwYxkG.lu0qxC6', null, '13555555555', null, null, '13555555555', '10000005', '1', '0', '1', null, '2019-01-15 23:14:13', null);
INSERT INTO `userinfo` VALUES ('6', '2', '13555555550', '$2a$10$4an9qMFqjMo9sQ9mVS4d.eOANjSNaCg/6AJXSOQZ642Kiyf1RT9ea', null, '13555555550', null, null, '13555555550', '10000006', '3', '0', '1', null, '2019-02-19 23:51:52', null);
INSERT INTO `userinfo` VALUES ('7', '1', '13936999999', '$2a$10$dDGEBf0cj6tfL4qtWLK6jecDjk7zt0lsJXeTcPWSvQ4s5HxZSQt7W', null, '13936999999', null, null, '13936999999', '10000007', '3', '0', '1', null, '2019-02-26 22:49:21', null);
INSERT INTO `userinfo` VALUES ('8', '1', '13000000000', '$2a$10$KGltwqjQiGXYAMOJDjmUze6cDt0.4HHJxq6raXAlrfxMYGJB19h4G', null, '13000000000', null, null, '13000000000', '10000008', '1', '0', '1', null, '2019-02-26 23:34:42', null);
