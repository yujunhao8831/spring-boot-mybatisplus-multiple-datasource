CREATE DATABASE `aidijing-1`;

CREATE TABLE `aidijing_user` (
  `id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64)  NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `email`    VARCHAR(128) NULL,
  PRIMARY KEY (`id`)
)
  DEFAULT CHARACTER SET = utf8;


INSERT INTO `aidijing_user` (`id`, `username`, `password`, `email`) VALUES ('1', '披荆斩棘', '123456', 'yujunhao_8831@yahoo.com');


