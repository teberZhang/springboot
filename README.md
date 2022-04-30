Mybatis
======   

快速开始
-----

##### 内容
* JWT-TOKEN登陆
* Mybatis
* 切面日志

##### 表结构
````
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `age` int(10) NOT NULL DEFAULT '0',
  `username` varchar(32) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `name` varchar(255) NOT NULL COMMENT '管理员名称',
  `password` varchar(255) NOT NULL COMMENT '管理员密码',
  `type` varchar(255) NOT NULL COMMENT '管理员类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL COMMENT '新闻标题',
  `content` varchar(255) DEFAULT NULL COMMENT '新闻内容',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态:1-正常，-1-删除',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_sn` varchar(64) NOT NULL COMMENT '订单编号',
  `doctor_id` int(10) NOT NULL DEFAULT '0' COMMENT '医生ID',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `total_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `status` varchar(20) NOT NULL COMMENT '订单状态',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE KEY `order_sn` (`order_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

````

##### 启动程序
* SpringbootApplication
````
19:59:13.962 [main] DEBUG cn.hutool.log.LogFactory - Use [Slf4j] Logger As Default.
19:59:13.964 [main] INFO com.example.springboot.SpringbootApplication - com.example.springboot.SpringbootApplication开始启动...

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.6)

2022-04-30 19:59:14.376  INFO 19967 --- [           main] c.e.springboot.SpringbootApplication     : Starting SpringbootApplication using Java 1.8.0_311 on zhangsandeMacBook-Pro.local with PID 19967 (/Users/zhangsan/springboot/target/classes started by zhangshengyong in /Users/zhangsan/springboot)
2022-04-30 19:59:14.377  INFO 19967 --- [           main] c.e.springboot.SpringbootApplication     : The following profiles are active: dev
2022-04-30 19:59:14.906  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2022-04-30 19:59:14.907  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2022-04-30 19:59:14.917  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 8 ms. Found 0 JDBC repository interfaces.
2022-04-30 19:59:14.925  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2022-04-30 19:59:14.926  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2022-04-30 19:59:14.935  INFO 19967 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 4 ms. Found 0 Redis repository interfaces.
2022-04-30 19:59:15.469  INFO 19967 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-04-30 19:59:15.476  INFO 19967 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-04-30 19:59:15.476  INFO 19967 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.54]
2022-04-30 19:59:15.524  INFO 19967 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-04-30 19:59:15.525  INFO 19967 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1109 ms
2022-04-30 19:59:16.804  INFO 19967 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-04-30 19:59:16.939  INFO 19967 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-04-30 19:59:17.178  INFO 19967 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-04-30 19:59:17.188  INFO 19967 --- [           main] c.e.springboot.SpringbootApplication     : Started SpringbootApplication in 3.116 seconds (JVM running for 3.691)
2022-04-30 19:59:17.190  INFO 19967 --- [           main] c.e.springboot.SpringbootApplication     : com.example.springboot.SpringbootApplication启动成功！
````

##### 访问
POST **http://localhost:8080/user/login**
* params
````
{
    "name":"admin",
    "password":"123sha"
}

````
* response
````
{
    "code": 20000,
    "msg": "SUCCESS",
    "data": {
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjUxMzYyMDk5fQ.DZ5nY4KbuGJJ-TGjOHF3a6Bk6Ect88EswJ-2CEfH22I"
    },
    "count": null,
    "ok": true
}
````