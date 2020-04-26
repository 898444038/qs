-- 建表语句
CREATE TABLE `qa_answer` (
`id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
`question_id` BIGINT(20)  DEFAULT NULL  COMMENT '问题ID',
`content` VARCHAR(32)  DEFAULT NULL  COMMENT '回答内容',
`create_time` datetime  DEFAULT NULL  COMMENT '创建时间',
`del_flag` TINYINT(1)  DEFAULT NULL  COMMENT '删除标识(0：未删除，1：已删除)',
PRIMARY KEY (`id`)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新增字段
ALTER TABLE `qa_answer` ADD COLUMN id BIGINT(20)  DEFAULT NULL ;
ALTER TABLE `qa_answer` ADD COLUMN question_id BIGINT(20)  DEFAULT NULL  COMMENT '问题ID';
ALTER TABLE `qa_answer` ADD COLUMN content VARCHAR(32)  DEFAULT NULL  COMMENT '回答内容';
ALTER TABLE `qa_answer` ADD COLUMN create_time datetime  DEFAULT NULL  COMMENT '创建时间';
ALTER TABLE `qa_answer` ADD COLUMN del_flag TINYINT(1)  DEFAULT NULL  COMMENT '删除标识(0：未删除，1：已删除)';

