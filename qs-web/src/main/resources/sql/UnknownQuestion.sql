-- 建表语句
CREATE TABLE `qa_unknown_question` (
`id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
`title` VARCHAR(32)  DEFAULT NULL  COMMENT '未知问题',
`status` INTEGER(11)  DEFAULT NULL  COMMENT '是否解答：0未解答，1已解答，2已删除',
`create_time` datetime  DEFAULT NULL  COMMENT '创建时间',
`content` VARCHAR(32)  DEFAULT NULL  COMMENT '解答内容',
PRIMARY KEY (`id`)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新增字段
ALTER TABLE `qa_unknown_question` ADD COLUMN id BIGINT(20)  DEFAULT NULL ;
ALTER TABLE `qa_unknown_question` ADD COLUMN title VARCHAR(32)  DEFAULT NULL  COMMENT '未知问题';
ALTER TABLE `qa_unknown_question` ADD COLUMN status INTEGER(11)  DEFAULT NULL  COMMENT '是否解答：0未解答，1已解答，2已删除';
ALTER TABLE `qa_unknown_question` ADD COLUMN create_time datetime  DEFAULT NULL  COMMENT '创建时间';
ALTER TABLE `qa_unknown_question` ADD COLUMN content VARCHAR(32)  DEFAULT NULL  COMMENT '解答内容';

