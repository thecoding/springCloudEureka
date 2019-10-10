package com.user.controller;

import com.user.bean.UserInfo;
import com.user.service.OrderInterface;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MengMeng on 2019/9/22.
 */
@RestController
public class UserToOrderClient {

    @Value("${spring.application.name}")
    String applicationName;


    @Autowired
    OrderInterface orderInterface;

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/userInfo")
    public String userApplicationInfo(){
        return "this is user client";
    }

    @RequestMapping("/userToOrder")
    public String userInvokeOrder(){
        return "user invoke order: order application name is " + orderInterface.getOrderInfo();
    }

    @RequestMapping("/userRibbonTest")
    public String userToRibbonTest(){
        for (int i = 0; i < 10; i++) {
            System.out.println(orderInterface.ribbonTest(i));
            //调用输出： 表示是轮询
//            第0次调用，该服务器端口是：2100
//            第1次调用，该服务器端口是：2102
//            第2次调用，该服务器端口是：2100
//            第3次调用，该服务器端口是：2102
//            第4次调用，该服务器端口是：2100
//            第5次调用，该服务器端口是：2102
//            第6次调用，该服务器端口是：2100
//            第7次调用，该服务器端口是：2102
//            第8次调用，该服务器端口是：2100
//            第9次调用，该服务器端口是：2102
        }
        return "调用成功";
    }


    /**
     * 下面是controller接收参数的几种方式
     * 请求地址是：http://localhost:port/getUserInfo/3   -> id = 3
     * 使用get和postd
     * @param id
     * @return
     */
    @RequestMapping("/getUserInfo/{id}")
    public UserInfo getUserInfo(@PathVariable int id) {
        return userInfoService.getUserInfo(id);
    }

    /**
     * 请求地址是：http://localhost:port/getUserInfo?id=3   -> id = 3
     * post方式和get方式都可以，post是form-data格式 参数id=3
     * @param id
     * @return
     */
    @RequestMapping("/getUserInfo2")
    public UserInfo getUserInfo2(@RequestParam("id") int id) {
        return userInfoService.getUserInfo(id);
    }

    /**
     * 请求地址是：http://localhost:port/getUserInfo3?id=3   -> id = 3
     * 这里直接可以将参数装载到UserInfo这个Bean中
     * post方式和get方式都可以，post是form-data格式 参数id=3
     * @param userInfo
     * @return
     */
    @RequestMapping("/getUserInfo3")
    public UserInfo getUserInfo3(UserInfo userInfo) {
        return userInfoService.getUserInfo(userInfo.getId());
    }
}
