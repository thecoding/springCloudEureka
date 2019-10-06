package com.user.controller;

import com.user.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
