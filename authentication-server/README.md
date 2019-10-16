

### spring security 内存模式


首先pom.xml中配置依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-security</artifactId>
</dependency>
```

配置认证服务器

```java
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientId")
                .secret(passwordEncoder.encode("secret"))
                // 授权类型
                .authorizedGrantTypes("authorization_code")
                // 授权范围
                .scopes("app")
                // 注册回调地址
                .redirectUris("https://www.dogedoge.com/");
    }
}
```

服务器安全配置
```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("username").password(passwordEncoder().encode("123456")).roles("USER")
                .and().withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN");
    }
}
```
就成功配置了,需要注意的是:密码必须要加密,所以注入了PasswordEncoder

测试：  
  访问地址：http://localhost:8080/oauth/authorize?client_id=client&response_type=code  
  输入用户名和密码（WebServerSecurityConfig写死的，比如username@123456）![image](https://github.com/thecoding/springCloudEureka/blob/master/images/oauth2/login.png)  
  如果成功就会跳转到 https://www.dogedoge.com/?code=fKZFPt  
  拿到code，通过postman请求![image](https://github.com/thecoding/springCloudEureka/blob/master/images/oauth2/postman_grant.png)
  如果如图所示，表示成功


### spring security jdbc模式