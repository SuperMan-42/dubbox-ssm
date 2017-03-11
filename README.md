# Dubbox实践笔记
## [github项目地址https://github.com/SuperMan42/dubbox-ssm](https://github.com/SuperMan42/dubbox-ssm)
## 简介
### SOA（面向服务体系结构）PRC(远程过程调用)
面向服务的体系结构，将应用程序的不同功能单元（称为服务）通过这些服务之间定义良好的接口和契约联系起来。

![](http://f.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=3913d783a4c27d1eb12b33967abcc60b/21a4462309f79052750e22190cf3d7ca7bcbd580.jpg)  
服务消费者（service consumer）可以通过发送消息来调用服务，这些消息由一个服务总线（service bus）转换后发送给适当的服务实现。  

![](http://exceptioncenter.com/attachment/1512/thread/45_1_752751dfc7ef3f9.png)  

Dubbo框架设计一共划分了10个层，最上面的Service层是留给实际想要使用Dubbo开发分布式服务的开发者实现业务逻辑的接口层，图中左边淡蓝背景的为服务消费方使用的接口，右边淡绿色背景为服务提供方使用的接口，位于中轴线上的为双方都用到的接口  
1. 服务接口层（Service）：该层是与实际业务逻辑相关的，根据服务提供方和服务消费方的业务设计对应的接口和实现。  
2. 配置层（Config）：对外配置接口，以ServiceConfig和ReferenceConfig为中心，可以直接new配置类，也可以通过spring解析配置生成配置类  
3. 服务代理层（Proxy）：服务接口透明代理，生成服务的客户端Stub和服务器端Skeleton，以ServiceProxy为中心，扩展接口为ProxyFactory  
4. 服务注册层（Registry)：封装服务地址的注册与发现，以服务URL为中心，扩展接口为RegistryFactory,Registry和RegistryService，可能没有服务注册中心，此时服务提供方直接暴露服务  
5. 集群层（Cluster)：封装多个提供者的路由及负载均衡，并桥接注册中心，以Invoker为中心，扩展接口为Cluster,Directory,Router和LoadBalance。将多个服务提供方组合为一个服务提供方，实现对服务消费方来透明，只需要与一个服务提供方进行交互。  
6. 监控层（Monitor）：RPC调用次数和调用时间监控，以Statistics为中心，扩展接口为MonitorFactory，Monitor和MonitorService。  
7. 远程调用层（Protocol)：封装RPC调用，封将RPC调用，以Invocation和Result为中心，扩展接口为Protocol、Invoker和Exporter。Protocol是服务域，它是Invoker暴露和引用的主功能入口，它负责Invoker的生命周期管理。Invoker是实体域，它是Dubbo的核心模型，其它模型都向它靠扰，或转换成它，它代表一个可执行体，可向它发起invoke调用，它有可能是一个本地的实现，也可能是一个远程的实现，也可能一个集群实现。  
8. 信息交换层（Exchange)：封装请求响应模式，同步转异步，以Request和Response为中心，扩展接口为Exchanger，xchangeChannel，ExchangeClient和ExchangeServer。  
9. 网络传输层（Transport)：抽象mina和netty为统一接口，以Message为中心，扩展接口为  Channel,Transproter,Client,Server和Codec。  
10. 数据序列化层（Serialize)：可复用的一些工具，扩展接口为  Serialization,ObjectInput,ObjectOutput和ThreadPool。  

**服务调用**  
![](http://exceptioncenter.com/attachment/1512/thread/45_1_ae65b606f44ad87.png)  
上图中，蓝色的表示与业务有交互，绿色的表示只对Dubbo内部交互。上述图所描述的调用流程如下：  
 
1. 服务提供方发布服务到服务注册中心；  
2. 服务消费方从服务注册中心订阅服务；  
3. 服务消费方调用已经注册的可用服务；  

![](http://exceptioncenter.com/attachment/1512/thread/45_1_18ed55ceed4cad6.png)  
注册/注销服务 
服务的注册与注销，是对服务提供方角色而言，那么注册服务与注销服务的时序图，如图所示： 
![](http://exceptioncenter.com/attachment/1512/thread/45_1_ecbc500a030049c.png)  
服务订阅/取消 
为了满足应用系统的需求，服务消费方的可能需要从服务注册中心订阅指定的有服务提供方发布的服务，在得到通知可以使用服务时，就可以直接调用服务。反过来，如果不需要某一个服务了，可以取消该服务。下面看一下对应的时序图，如图所示： 
![](http://exceptioncenter.com/attachment/1512/thread/45_1_c2a7f2740aba122.png)  

Dubbo以包结构来组织各个模块，各个模块及其关系，如图所示： 
![](http://exceptioncenter.com/attachment/1512/thread/45_1_1e1d8d839715f7b.png)  

可以通过Dubbo的代码（使用Maven管理）组织，与上面的模块进行比较。简单说明各个包的情况：  

* **dubbo-common 公共逻辑模块，包括Util类和通用模型。**  
* **dubbo-remoting 远程通讯模块，相当于Dubbo协议的实现，如果RPC用RMI协议则不需要使用此包。**  
* **dubbo-rpc 远程调用模块，抽象各种协议，以及动态代理，只包含一对一的调用，不关心集群的管理。**  
* **dubbo-cluster 集群模块，将多个服务提供方伪装为一个提供方，包括：负载均衡、容错、路由等，集群的地址列表可以是静态配置的，也可以是由注册中心下发。**  
* **dubbo-registry 注册中心模块，基于注册中心下发地址的集群方式，以及对各种注册中心的抽象。**  
* **dubbo-monitor 监控模块，统计服务调用次数，调用时间的，调用链跟踪的服务。**  
* **dubbo-config 配置模块，是Dubbo对外的API，用户通过Config使用Dubbo，隐藏Dubbo所有细节。**  
* **dubbo-container 容器模块，是一个Standalone的容器，以简单的Main加载Spring启动，因为服务通常不需要Tomcat/JBoss等Web容器的特性，没必要用Web容器去加载服务。**  

## dubbox

* 支持REST风格远程调用（HTTP + JSON/XML)：基于非常成熟的JBoss RestEasy框架，在dubbo中实现了REST风格（HTTP + JSON/XML）的远程调用，以显著简化企业内部的跨语言交互，同时显著简化企业对外的Open API、无线API甚至AJAX服务端等等的开发。事实上，这个REST调用也使得Dubbo可以对当今特别流行的“微服务”架构提供基础性支持。 另外，REST调用也达到了比较高的性能，在基准测试下，HTTP + JSON与Dubbo 2.x默认的RPC协议（即TCP + Hessian2二进制序列化）之间只有1.5倍左右的差距，详见文档中的基准测试报告。  
* 支持基于Kryo和FST的Java高效序列化实现：基于当今比较知名的Kryo和FST高性能序列化库，为Dubbo默认的RPC协议添加新的序列化实现，并优化调整了其序列化体系，比较显著的提高了Dubbo RPC的性能，详见文档中的基准测试报告。  
* 支持基于Jackson的JSON序列化：基于业界应用最广泛的Jackson序列化库，为Dubbo默认的RPC协议添加新的JSON序列化实现。  
* 支持基于嵌入式Tomcat的HTTP remoting体系：基于嵌入式tomcat实现dubbo的HTTP remoting体系（即dubbo-remoting-http），用以逐步取代Dubbo中旧版本的嵌入式Jetty，可以显著的提高REST等的远程调用性能，并将Servlet API的支持从2.5升级到3.1。（注：除了REST，dubbo中的WebServices、Hessian、HTTP Invoker等协议都基于这个HTTP remoting体系）。  
* 升级Spring：将dubbo中Spring由2.x升级到目前最常用的3.x版本，减少版本冲突带来的麻烦。  
* 升级ZooKeeper客户端：将dubbo中的zookeeper客户端升级到最新的版本，以修正老版本中包含的bug。  
* 支持完全基于Java代码的Dubbo配置：基于Spring的Java Config，实现完全无XML的纯Java代码方式来配置dubbo  
* 调整Demo应用：暂时将dubbo的demo应用调整并改写以主要演示REST功能、Dubbo协议的新序列化方式、基于Java代码的Spring配置等等。  
* 修正了dubbo的bug 包括配置、序列化、管理界面等等的bug。  

## dubbo
**api项目**
pom.xml  
SerializationOptimizerImpl.java  
User.java  
UserRestService.java  
UserService.java  
**provider项目**
pom.xml  
UserRestServiceImpl.java  
UserServiceImpl.java  
dubbo-demo-provider.xml  
**consumer项目**
pom.xml  
DemoConsumer.java  
dubbo-hello-consumer.xml  
### 框架整合
Springmvc + Mybatis + Shiro（权限） + REST(服务) + WebService(服务) + JMS(消息) + Lucene(搜搜引擎) + Quartz(定时调度) + Bootstrap Html5（支持PC、IOS、Android）

## 安装
* `git clone https://github.com/dangdangdotcom/dubbox`
* 在checkout出来的dubbox目录执行`mvn install -Dmaven.test.skip=true`编译一下dubbo
* 在dubbox/dubbo/target/下 分别运行 `mvn install:install-file -DgroupId=com.alibaba -DartifactId=dubbo-remoting-grizzly -Dversion=2.8.4 -Dpackaging=jar -Dfile=./dubbo-2.8.4.jar`  `mvn install:install-file -DgroupId=com.alibaba -DartifactId=dubbo-remoting-grizzly -Dversion=2.8.4 -Dpackaging=jar -Dfile=./dubbo-2.8.4-sources.jar`  `mvn install:install-file -DgroupId=com.alibaba -DartifactId=dubbo-remoting-grizzly -Dversion=2.8.4 -Dpackaging=jar -Dfile=./original-dubbo-2.8.4.jar`  
* 在checkout出来的dubbox根目录执行`mvn idea:idea`或者`mvn eclipse:eclipse`创建IDE工程文件  
* 将项目导入IDE  
* 下载解压[zookeeper](/Users/hpw/Desktop/Project/Java_Project/dubbox副本/zookeeper-3.4.9),编辑其conf/[zoo.cfg](/Users/hpw/Desktop/Project/Java_Project/dubbox副本/zookeeper-3.4.9/conf/zoo.cfg) 后启动zookeeper用作dubbo注册中心：启动/停止/重启命令 `bin/zkServer.sh start/stop/restart`  启动客户端，创建，查询节点`bin/zkCli.sh -server localhost:2128`
* 用IDE运行/dubbo-demo/dubbo-demo-provider/.../test目录下的DemoProvider启动dubbo服务端，目前他会分别启动dubbo协议（包括用kryo和FST序列化）和REST协议的服务  
* 用IDE运行/dubbo-demo/dubbo-demo-consumer/.../test目录下的DemoConsumer来启动dubbo客户端调用上面的服务端，直接看console的输出即可  
* 用IDE运行/dubbo-demo/dubbo-demo-consumer/.../test目录下的RestClient来启动rest客户端（模拟非dubbo的rest客户端）调用上面的服务端，直接看console的输出即可  
* 可以在浏览器中直接访问http://localhost:8888/services/users/100.xml或者http://localhost:8888/services/users/101.json之类来测试REST服务  
* 注意必须先启动zookeeper，在启动监控后启动admin

### [dubbo-admin部署](http:127.0.0.1/8080)
用户名root 密码 root

### [dubbo-monitor-simple部署](http://127.0.0.1:8180)
打包后，目录dubbo-simple/dubbo-monitor-simple/target中会生成dubbo-monitor-simple-xxx-assembly.tar.gz 用tar -zxvf *.gz解压，解压后有三个子目录[bin](/Users/hpw/Desktop/Project/Java_Project/dubbox/dubbox/dubbo-simple/dubbo-monitor-simple/target/dubbo-monitor-simple-2.8.4/bin),  [conf](/Users/hpw/Desktop/Project/Java_Project/dubbox/dubbox/dubbo-simple/dubbo-monitor-simple/target/dubbo-monitor-simple-2.8.4/conf),  [lib](/Users/hpw/Desktop/Project/Java_Project/dubbox/dubbox/dubbo-simple/dubbo-monitor-simple/target/dubbo-monitor-simple-2.8.4/lib),

配置文件  

```
dubbo.container=log4j,spring,registry,jetty
dubbo.application.name=simple-monitor
dubbo.application.owner=
#dubbo.registry.address=multicast://224.5.6.7:1234
dubbo.registry.address=zookeeper://127.0.0.1:2181
#dubbo.registry.address=redis://127.0.0.1:6379
#dubbo.registry.address=dubbo://127.0.0.1:9090
dubbo.protocol.port=7070
dubbo.jetty.port=8180
dubbo.jetty.directory=${user.home}/monitor
dubbo.charts.directory=${dubbo.jetty.directory}/charts
dubbo.statistics.directory=${user.home}/monitor/statistics
dubbo.log4j.file=logs/dubbo-monitor-simple.log
dubbo.log4j.level=WARN
```
启动：`bin/start.sh`

## 项目介绍
* aubbo-demo-api(服务接口传输对象)  
* dubbo-demo-provider(服务生产者)  
* duboo-demo-consumer(服务消费方)  
* dubbo-admin(dubbo的后台管理，其配置文件在webapp\WEB-INF\底下，名为dubbo.properties)  
* dubbo-monitor(性能监控)

## 参考资料  
[dubbox入门](http://www.cnblogs.com/yjmyzz/p/dubbox-demo.html)  
[ZooKeeper高可用集群安装和配置](http://wosyingjun.iteye.com/blog/2312960)  
[dubbox入门实例](http://www.jianshu.com/p/622cb9370623)
[dubbox监管](http://www.cnblogs.com/yjmyzz/p/dubbo-admin-monitor-deploy.html)  
[dubbo架构设计详解](http://exceptioncenter.com/read.php?tid=340)  
[dubbox实现](http://11936495.blog.51cto.com/11926495/1851192)

## 类似项目
[disconf](https://github.com/knightliao/disconf)

## 参考项目
[ssm-dubbo](https://github.com/wosyingjun/beauty_ssm_dubbo)  
[soa](https://github.com/247687009/soa)  
[iBase4J](https://github.com/suevip/iBase4J)   
[dubbo-spring-boot-mybatis-redis](https://github.com/dearbinge/dubbo-spring-boot-mybatis-redis)  
[xultimate-remoting](https://github.com/daniellitoc/xultimate-remoting)  

## redis
1. 保存后，启动redis：./bin/redis-server etc/redis.conf
2. 查看日志文件：tail -f log-redis.log
3. 关闭 redis-cli shutdown
4. 检查是否启动 redis-cli ping
5. mvn install:install-file -Dfile=umeng-analytics-v6.0.8.jar -DgroupId=com.hpw -DartifactId=umeng -Dversion=6.0.8 -Dpackaging=jar

## http
http://localhost:8081/ssm/service/data?appKey=4f83c5d852701564c0000011&signature=B5:DF:25:B2:DE:EA:C8:00:54:E9:AE:7E:B0:65:CE:73&serial&data={\%22body\%22:{\%22sessions\%22:[{\%22id\%22:\%22581FD330CE6043B16FF93D236D55C110\%22,\%22start_time\%22:\%221484022723497\%22,\%22end_time\%22:\%221484022783289\%22,\%22duration\%22:59792,\%22pages\%22:[{\%22page_name\%22:\%22AnalyticsHome\%22,\%22duration\%22:59870}],\%22autopages\%22:[{\%22page_name\%22:\%22com.umeng.example.analytics.AnalyticsHome\%22,\%22duration\%22:59869}],\%22traffic\%22:{\%22download_traffic\%22:14825,\%22upload_traffic\%22:71488}}],\%22userlevel\%22:\%221\%22,\%22active_user\%22:{\%22provider\%22:\%22_adhoc\%22,\%22puid\%22:\%22example_id\%22}},\%22header\%22:{\%22appkey\%22:\%224f83c5d852701564c0000011\%22,\%22channel\%22:\%22Umeng\%22,\%22display_name\%22:\%22%E5%8F%8B%E7%9B%9FSDK\%22,\%22package_name\%22:\%22com.umeng.example\%22,\%22app_signature\%22:\%22B5:DF:25:B2:DE:EA:C8:00:54:E9:AE:7E:B0:65:CE:73\%22,\%22app_version\%22:\%221.0\%22,\%22version_code\%22:1,\%22sdk_type\%22:\%22Android\%22,\%22sdk_version\%22:\%226.0.8\%22,\%22vertical_type\%22:0,\%22idmd5\%22:\%22302db51a3093534d7df53b9710c15a41\%22,\%22cpu\%22:\%22AArch64%20Processor%20rev%201%20(aarch64)\%22,\%22os\%22:\%22Android\%22,\%22os_version\%22:\%226.0\%22,\%22resolution\%22:\%221920*1080\%22,\%22mc\%22:\%22c8:25:e1:c8:84:5c\%22,\%22device_id\%22:\%22862044036185291\%22,\%22device_model\%22:\%22Le%20X620\%22,\%22device_board\%22:\%22unknown\%22,\%22device_brand\%22:\%22LeEco\%22,\%22device_manutime\%22:1471855745000,\%22device_manufacturer\%22:\%22LeMobile\%22,\%22device_manuid\%22:\%22HEXCNFN5801708221S\%22,\%22device_name\%22:\%22le_x6\%22,\%22access\%22:\%22wifi\%22,\%22mccmnc\%22:\%2246002\%22,\%22country\%22:\%22CN\%22,\%22language\%22:\%22zh\%22,\%22timezone\%22:8,\%22carrier\%22:\%22%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8\%22,\%22successful_requests\%22:89,\%22failed_requests\%22:0,\%22req_time\%22:8,\%22imprint\%22:\%22GxGMB3ByZXRpbWUYDTE0ODQwMjIzODEwNjEWiuiD6rBWGCg3NTE4Y2NiNjA3ZTgwOTEyODU5ZTgx\\nYzE0NTZlNWVlZGQ2NGI2ZjQzAAR1bWlkGCAzMDJkYjUxYTMwOTM1MzRkN2RmNTNiOTcxMGMxNWE0\\nMRaar7rWrVYYKGJkMjkwYjVkYWE2YjVlMjg1YTY0MGRiMDgzNDdkYzYzOTJkNWIyMzYAAXYYBTEt\\nMS0xFpqvutatVhgoYmQyOTBiNWRiMDliNDU3OTQ5ZWJkNzI5ZjE5Yjc2YzY4NWU3NDZjNQAGcGF5\\nX2F0GAoyMDE3LTAxLTA2Fqz2rvutVhgoZTZiZmQ5NWYwZGRiNDQ1NzYxYjAwZTllNjQyMDYyOWRh\\nYWE1ZjRiOQAOcGF5X3VzZXJfbGV2ZWwYATEWrPau+61WGChlNmJmZDk1ZjkxNDFmM2NhYmIxNzA2\\nOGY4NjU4MjY5MzVjODNhODg0AAlwYXlfbGV2ZWwYB3Vua25vd24WrPau+61WGChlNmJmZDk1ZmY2\\nNTg1YTAwOWE0YTFmYWJiZGIwOWQwNDk5NzU3MWI0AAdjaGFubmVsGAVVbWVuZxaar7rWrVYYKGJk\\nMjkwYjVkYzY3OWU3NzBiYTQ3MThiMGYwODY4YWIxMjk0YjQ0YWYAEGluc3RhbGxfZGF0ZXRpbWUY\\nEzIwMTctMDEtMDUgMDM6MDM6MTIW8qqC161WGChjOTI4MWM1ZGRlMzQ5ZjA2YWUzMjM0MTgxYjFi\\nMzllOTYxOGNhMTI1AAZyZWdfYXQYCjIwMTUtMDEtMTkWgt\\/s961WGChiMWY1MTE1ZmNlNWNlZDdh\\nMmRiN2E0NjVjNmY2ZmQ2NDM0NjdjMjVkAAZqaXR0ZXIYATAWsr3j5rBWGCgyOTBkMDBiNjc4NDIw\\nNjYyMjU3YWZjYjEzOTIxZjA2ZTBkMzhhNTg2AAZsYXRlbnQYATMWmq+61q1WGChiZDI5MGI1ZDI4\\nYTYxMTIyMjQyZWVkMzQ2YjYzYjMxY2VhNzk5ZTNhAA9pbnN0YWxsX2NoYW5uZWwYBVVtZW5nFvKq\\ngtetVhgoYzkyODFjNWQzMTQ2NTFiODA2ZTA5NjAwNzg5Y2VhY2QzNGE3NTY5ZQALYXBwX3ZlcnNp\\nb24YAzEuMBaar7rWrVYYKGJkMjkwYjVkM2E1N2Y5ZDhhOWU0YjhkYTQyNzRmYjdmNzc0YmVmNjIA\\nC3BheV92ZXJzaW9uGAMxLjAWrPau+61WGChlNmJmZDk1ZjAzNWYwYTgyNDlmMWQ2Mjg2MDU2MzRm\\nNzcxNTMzNWM0ABNpbnN0YWxsX2FwcF92ZXJzaW9uGAMxLjAW8qqC161WGChjOTI4MWM1ZGFhOWQz\\nODM3ZTM5OTVjZTc3OGQ0ZTUwNjkzN2FmOTkwAApwYXlfc291cmNlGAI3Nxas9q77rVYYKGU2YmZk\\nOTVmZDVkMDVlYmEwOTYyMzI1MDM0MzZjMGNkN2Q2NmQ3YzEACm9zX3ZlcnNpb24YAzYuMBaar7rW\\nrVYYKGJkMjkwYjVkMGU0MDU5ODU3NjkxNWIyOWE5ZTExOTU4M2E3ZGVmZTIAFQIYIDBkOTU4OWNi\\nNTU0MTY2NmI2NWM3YjM4YTgyOTJlOTE1AA==\\n\%22,\%22id_tracking\%22:\%22GweMBXV0ZGlkGBhWMkVmWVJRWWw2a0RBQzhhQzdPMzczWEoWyp261q1WFQIABGlkZmEYJDgxYTEw\\nMGVhLTlhN2ItNGFlZC1iMzJjLWRmYjczZDk5MzNiNhbynbrWrVYVAgAGc2VyaWFsGBBMRTY3QTA2\\nMjIwMzMyOTQ3FtSdutatVhUCAARpbWVpGA84NjIwNDQwMzYxODUyOTEWzp261q1WFQIABWlkbWQ1\\nGCAzMDJkYjUxYTMwOTM1MzRkN2RmNTNiOTcxMGMxNWE0MRbUnbrWrVYVAgAKYW5kcm9pZF9pZBgQ\\nMzRiZjc1MGQzMDI2MGI5NBbWnbrWrVYVAgADbWFjGBFjODoyNTplMTpjODo4NDo1YxbYnLrWrVYV\\nAgAZDAA=\\n\%22}}

