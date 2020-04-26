-- 建表语句
CREATE TABLE `qa_city` (
`id` BIGINT(20)  NOT NULL AUTO_INCREMENT  COMMENT '编码',
`name` VARCHAR(32)  DEFAULT NULL  COMMENT '名称',
PRIMARY KEY (`id`)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 新增字段
ALTER TABLE `qa_city` ADD COLUMN id BIGINT(20)  DEFAULT NULL  COMMENT '编码';
ALTER TABLE `qa_city` ADD COLUMN name VARCHAR(32)  DEFAULT NULL  COMMENT '名称';

