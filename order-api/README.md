# client order api

####完成功能

##### 加入actuator监控，显示Dashboard仪表盘
1、首先引入jar包\
    
    <!-- 仪表盘监控依赖 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    
2、配置需要监控的请求，application.yml配置\

    #监控请求流
    management:
      endpoints:
        web:
          exposure:
            include: "*"
    
3、加入servlet配置，我是在ClientApplication文件中加入了

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
  
启动后，请求http://localhost:2100/hystrix.stream就能在页面上看到一直在ping，但是ping一直是空的，是不是漏了什么呢？
其实这里还是要打开feign中的hystrix，在配置文件中application.yml中配置

    feign:
      hystrix:
        enabled: true #是否开启断路器