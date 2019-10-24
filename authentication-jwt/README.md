

### spring security + jwt 验证登录
参考：https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world

#### spring security + jwt 验证登录实现
##### 实现原理  
![image](https://github.com/thecoding/springCloudEureka/tree/master/images/authentication-jwt/yuanli.jpg)  
    1. 客户端post请求到 /authenticate 并且带上我们设定的用户名和密码.  
    2. 验证通过以后，我们返回能够包含用户信息的JWT格式字符串。  
    3. 客户端拿到jwt字符串以后，把放到请求头header里面，用来正常请求我们的业务数据。

##### 验证调用过程
![image](https://github.com/thecoding/springCloudEureka/tree/master/images/authentication-jwt/request_follows.jpg)
    1. 当客户请求过来时，服务端首先通过拦截器来检查是否有token。如果没有，就会进入security的拦截器。  
    2. security如果配置了authorizeRequests，能够匹配上就放行，此时就会进入到controller方法
    3. 进入controller后，首先会进行用户名、密码校验。封装AuthenticationManager的对象和设置db中查询出来的userDetails进行校验
    4. 检验通过后，jwtTokenUtil就会生成token返回给客户端
    ![image](https://github.com/thecoding/springCloudEureka/tree/master/images/authentication-jwt/authentication.jpg)
    
##### 业务请求调用过程

![image](https://github.com/thecoding/springCloudEureka/tree/master/images/authentication-jwt/validating_jwt.jpg)
    1. client拿到token后，在header中加入Authorization，请求到后台。
    2. jwtRequestFilter检验token的正确性
    3. 如果通过，则请求到controller业务层
        
    
    
    
