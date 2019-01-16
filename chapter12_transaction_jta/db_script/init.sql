CREATE DATABASE `test-order-db` /*!40100 DEFAULT CHARACTER SET utf8 */;
use test-order-db;
CREATE TABLE `table_order` (
  `order_id` varchar(255) NOT NULL COMMENT '订单号',
  `user_id` varchar(255) NOT NULL COMMENT '用户编号',
  `order_content` varchar(255) NOT NULL COMMENT '订单内容(买了哪些东西，送货地址)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';


CREATE DATABASE `test-dispatch-db` /*!40100 DEFAULT CHARACTER SET utf8 */;
use test-dispatch-db;
CREATE TABLE `table_dispatch` (
  `dispatch_seq` varchar(255) NOT NULL COMMENT '调度流水号',
  `order_id` varchar(255) NOT NULL COMMENT '订单编号',
  `dispatch_status` varchar(255) DEFAULT NULL COMMENT '调度状态',
  `dispatch_content` varchar(255) DEFAULT NULL COMMENT '调度内容(送餐员，路线)',
  PRIMARY KEY (`dispatch_seq`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度信息表';