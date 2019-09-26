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
}
