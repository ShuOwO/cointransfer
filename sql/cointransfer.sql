DROP DATABASE IF EXISTS `cointransfer`;

CREATE DATABASE  `cointransfer` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `cointransfer`;

-- ----------------------------
-- 幣別轉換表
-- ----------------------------
DROP TABLE IF EXISTS currency;
CREATE TABLE currency (
  currency_id     bigint           not null auto_increment,
  currency_code   varchar(3)       not null                   comment '幣別代碼',
  currency_name   varchar(10)      not null                   comment '幣別名稱',
  exchange_rate   decimal(15, 3)   not null                   comment '匯率(基於比特幣)',
  create_time     datetime                                    comment '創建時間',
  update_time     datetime                                    comment '更新時間',

  primary key (currency_id)
) engine=innodb auto_increment=20 comment = '幣別轉換表';
-- ----------------------------
-- Records of currency
-- ----------------------------
INSERT INTO currency (currency_id, currency_code, currency_name, exchange_rate, create_time, update_time)
VALUES
    (1, 'USD', '美金', 63837.612, NOW(), null),
    (2, 'TWD', '新台幣', 1915128.36, NOW(), null),
    (3, 'CNY', '人民幣', 274500.73, NOW(), null),
    (4, 'JPY', '日圓', 7021137.32, NOW(), null),
    (5, 'EUR', '歐元', 57196.777, NOW(), null),
    (6, 'GBP', '英鎊', 48348.245, NOW(), null);