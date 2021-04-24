/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : Movie

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 19/02/2021 13:49:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for movie_table
-- ----------------------------
DROP TABLE IF EXISTS `movie_table`;
CREATE TABLE `movie_table`  (
                              `movie_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电影id',
                              `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '中文名',
                              `english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '英文名',
                              `directors` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '导演',
                              `writer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编剧',
                              `actors` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员',
                              `date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日期',
                              `style1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '风格1',
                              `style2` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风格2',
                              `style3` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风格3',
                              `country` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家地区',
                              `rate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分',
                              `dataID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据id',
                              `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
                              `duration` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时长',
                              `introduction` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
                              `pic_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片链接',
                              `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '豆瓣链接',
                              PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie_table
-- ----------------------------

# 通过doubanMovies.csv导入

-- ----------------------------
-- Table structure for movie_vector_table
-- ----------------------------
DROP TABLE IF EXISTS `movie_vector_table`;
CREATE TABLE `movie_vector_table`  (
                                `movie_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电影id',
                                `movie_vector` json not NULL COMMENT '电影向量',
                                PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影向量表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie_vector_table
-- ----------------------------

# 通过doubanMovies.csv导入

-- ----------------------------
-- Table structure for movie_top_table
-- ----------------------------
DROP TABLE IF EXISTS `movie_top_table`;
CREATE TABLE `movie_top_table`  (
                                       `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电影id',
                                       `top1` varchar(20) NULL default NULL COMMENT 'top1',
                                       `top2` varchar(20) NULL default NULL COMMENT 'top2',
                                       `top3` varchar(20) NULL default NULL COMMENT 'top3',
                                       `top4` varchar(20) NULL default NULL COMMENT 'top4',
                                       `top5` varchar(20) NULL default NULL COMMENT 'top5',
                                       `top6` varchar(20) NULL default NULL COMMENT 'top6',
                                       `top7` varchar(20) NULL default NULL COMMENT 'top7',
                                       `top8` varchar(20) NULL default NULL COMMENT 'top8',
                                       `top9` varchar(20) NULL default NULL COMMENT 'top9',
                                       `top10` varchar(20) NULL default NULL COMMENT 'top10',
                                       PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '推荐电影表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie_vector_table
-- ----------------------------




-- ----------------------------
-- Table structure for map_table
-- ----------------------------
DROP TABLE IF EXISTS `map_table`;
CREATE TABLE `map_table`  (
                                       `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                       `movie_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电影id',
                                       `comment_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论id',
                                       PRIMARY KEY (`user_id`,`movie_id`,`comment_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户电影评论映射表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie_vector_table
-- ----------------------------



-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
                              `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                              `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                              PRIMARY KEY (`user_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户id名字映射' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie_vector_table
-- ----------------------------