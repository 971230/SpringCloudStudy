## 注意事项:
```
(1) 使用nacos启动可能会报错, 有时候是因为nacos会定期检查ip地址是否一致,
    使用家庭的普通网络,由于IP是租的不是固定ip,会报错误，每天删除data/protocol文件夹即可
(2) seata-develop是SeaTa框架源码,需要使用seata-develop/script/config-center/nacos/nacos-config-interactive.sh导入SeaTa配置
(3) 有集群配置,IDEA工具里面记得添加 Edit Configuration 再从原来的服务里面复制添加一个book服务、一个user服务
    之后也可以统一放入文件夹 
自定义文件夹(1)->    book(1)环境变量 server.port=7070;spring.cloud.nacos.discovery.cluster-name=Hangzhou
自定义文件夹(1)->    book(2)环境变量 server.port=7071;spring.cloud.nacos.discovery.cluster-name=Guangzhou
自定义文件夹(2)->    user(1)环境变量 server.port=7073;spring.cloud.nacos.discovery.cluster-name=Hangzhou
自定义文件夹(2)->    user(2)环境变量 server.port=7074;spring.cloud.nacos.discovery.cluster-name=Guangzhou
自定义文件夹(2)->    user(3)环境变量 server.port=7075;spring.cloud.nacos.discovery.cluster-name=Hangzhou
这样就有集群配置,而且可以测试负载均衡

(4) 服务启动有顺序: 最好是 Sentinel -> Nacos -> Seata -> book/user -> borrow
(5) nacos记得用standalone模式启动,默认是cluster集群模式,要测试集群需要服务器
```
