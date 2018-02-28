CREATE TABLE `w_version_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_type` int(11) DEFAULT NULL COMMENT '手机端 1：iOS 2：Android',
  `version_info` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新信息',
  `version_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '版本号名称',
  `version_no` int(11) DEFAULT NULL COMMENT '版本号',
  `is_force` int(11) DEFAULT NULL COMMENT '是否强制升级',
  `package_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '包地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci