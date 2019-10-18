

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

**具体步骤如下：**  
1、修改WebServerSecurityConfig.java  
```java
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
```
这里配置configure为userDetailService()，需要实现UserDetailService方法
```java
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    TbUserService tbUserService;

    @Autowired
    TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询用户信息
        TbUser tbUser = tbUserService.getByUsername(userName);
        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        if (tbUser != null) {
            // 查询用户权限信息
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());

            tbPermissions.forEach(tbPermission -> {
                if (tbPermission != null && tbPermission.getEnname() != null) {
                    grantedAuthorityList.add(new SimpleGrantedAuthority(tbPermission.getEnname()));
                }

            });
        }
        return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorityList);
    }
}
```
最主要是重写了loadUserByUsername方法，通过输入的用户名查询出其用户、密码、权限，这里的表都是自己定义的，需要建立数据库信息，详细表请参考db目录下的createTableAndInsertData.sql   
tb_user存储的是用户信息，请求 http://localhost:8080/login需要的账号密码  
tb_role存储用户角色  
tb_permission存储的是权限信息  
tb_role_permission和tb_user_role是两张中间表  
（这里是通过tb_user的userName查询出该用户的tb_permission信息）  

application.yml配置如下：  
```yaml
server:
  port: 8080


#jdbc 存储
spring:
  application:
    name: Authorization-server
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    jdbc-url: jdbc:mysql://192.168.1.101:3306/oath2?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
    hikari:
      minimum-idle: 5
      idle-timeout: 600000
      maximum-pool-size: 10
      auto-commit: true
      pool-name: MyHikariCP
      max-lifetime: 1800000
      connection-timeout: 30000
      connection-test-query: SELECT 1

mybatis:
  type-aliases-package: springcloud.authentication.server.domain
  mapper-locations: classpath:tk.mybatis.mapper/*.xml
#  或者这样配置
#  mapper-locations: classpath:**mapper/*.xml

```
数据库连接池用的是：hikari  
持久化中间件是tk.mybatis  
mybatis工具类用到的是server包下和tk.mybatis.mapper下的对象，xml配置信息再resource/tk.mybatis.mapper下

2、认证服务器数据同样是存储到数据库，需要更改的代码是AuthorizationServerConfiguration.java
```java
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore(){
        // 基于 JDBC 实现，令牌保存到数据
        return new JdbcTokenStore(getDataSource());
    }

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(getDataSource());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }
```
认证服务，表数据用的是官网提供的，参考table.sql，登录后拿到code后，请求授权
![image](https://github.com/thecoding/springCloudEureka/blob/master/images/oauth2/postman_grant.png)  

账号密码配置在oauth_client_details表中

