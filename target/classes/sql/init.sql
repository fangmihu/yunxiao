# 用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255)   NOT NULL,
  `password` varchar(255)   NOT NULL,
  `salt` varchar(255)  NOT NULL,
  PRIMARY KEY (`id`),
  key key_username(`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# 商品类目表
CREATE TABLE `category` (
  `id` int(11) not null AUTO_INCREMENT,
  `name` varchar(255)   NOT NULL,
  `description` varchar(520),
  PRIMARY KEY (`id`),
  KEY key_name(`name`)
)ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# 商品明细表

# 订单表

# 订单明细表

#购物车待定