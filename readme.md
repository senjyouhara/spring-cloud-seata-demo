# spring-cloud-Seata-demo

该项目是用于个人学习seata试验的一个demo，主要集成了mybatis-plus,spring-cloud,
基于seata-server 1.1.0版本进行开发构建的

项目之初主要是分布式事务不回滚，看了些资料后知道进行rpc调用或http调用后需要对Xid进行传递，让对应的服务进行接收传递以便于xid的链式传递，始终保持为同一个xid

## 准备工作

在运行此示例之前，你需要先完成如下几步准备工作：

1. 运行db.sql

1. 启动 Seata Server


### 启动 Seata Server

点击这个页面 [https://github.com/seata/seata/releases](https://github.com/seata/seata/releases)，下载最新版本的 Seata Server 端.


## 运行示例

分别运行 `account-server`、`order-service`、`storage-service` 和 `business-service` 这三个应用的 Main 函数，启动示例。

启动示例后，通过 HTTP 的 GET 方法访问如下URL，可以分别验证在 `business-service` 中 通过 RestTemplate 和 FeignClient 调用其他服务的场景。

```
http://localhost:18081/seata/feign
```