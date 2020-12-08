# ProgrammingTest
笔试项目

PositionManagement

#### 1.设计思路

##### 1.1数据建模

CREATE TABLE `shop_report` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_id` bigint(20) DEFAULT NULL,
  `version` varchar(2) DEFAULT NULL,
  `security_code` varchar(5) DEFAULT NULL,
  `quantity` decimal(20,0) DEFAULT NULL,
  `op_type` varchar(10) DEFAULT NULL,
  `sellbuy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

##### 1.2逻辑设计

1.首先加载所有的流水记录

2.针对SecurityCode字段进行分组，分为多种操作类型

3.针对每一个分组完成的List，再针对Version做排序

4.排序好的List，做一个策略模式，

 	4.1先判断`op_type`类型是什么，如果是Insert/Update，在判断`sellbuy`的类型，如果是buy则取出`quantity`进行累加，如果是sell则累减

​	4.2如果是Cancel，则重置`quantity`为0

5.最终合并所有SecurityCode结果集，输出Map数据统计

##### 1.3实现

​	针对本次实验，所有数据采用mock形式，不连接数据库操作

##### 1.4demo演示

1.拉取项目

2.运行ReportServiceTest单元案例

3.查看输出log

![image-20201208143357321](C:\Users\12420\Desktop\test\ProgrammingTest\image-20201208143357321.png)