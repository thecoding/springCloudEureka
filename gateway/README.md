# springCloud-gateway



#### 基本用法
    server:
      port: 5000
    
    spring:
      application:
        name: springCloud-gateway
    
    spring:
      cloud:
        gateway:
          routes:
          - id: user_route
            uri: http://localhost:2101  # 目的地址
            predicates:
            - Path=/user/**  #请求地址：http://localhost:5000/user/**
            filters:
            - StripPrefix=1  #过滤规则：请求地址为：http://localhost:5000/user/** -> http://localhost:2101/userInfo 去掉一个路径
    
    #上面这段配置的意思是，配置了一个 id 为 user_route 的路由规则，
    #当访问地址 http://localhost:5000/user/* 时会自动转发到地址：http://localhost:2101/*
    #配置完成启动项目即可在浏览器访问进行测试，这里是没有 加入eureka注册中心的
    
    
##### 下面是加入注册中心
   pom.xml中加入eureka-client依赖
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
   application.yml中加入eureka配置信息
    
    eureka:
      client:
        service-url:
          defaultZone: http://name:admin123@localhost:7000/eureka/
    
    
    spring:
      cloud:
        gateway:
          routes:
          - id: user_route
            uri: lb://UserClient  # 目的地址
            predicates:
            - Path=/user/**  #请求地址：http://localhost:5000/user/**
            filters:
            - StripPrefix=1
            
##### 待续未完的还有：


返回报文统一、认证、限流、熔断、动态配置路由和过滤器 参考：https://www.cnblogs.com/qianwei/p/10127700.html



gateway处理的事情：路由请求、鉴权、监控、缓存、限流等功能  
1、统一接入  
	智能路由  
	AB测试、灰度测试  
	负载均衡、容灾处理  
	日志埋点（类似nginx日志）  
2、流量监控  
	限流处理  
	服务降级  
3、安全防护  
	鉴权处理  
	监控  
	机器网络隔离  