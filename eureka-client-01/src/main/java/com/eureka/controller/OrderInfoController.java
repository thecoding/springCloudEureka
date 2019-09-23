package com.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MengMeng on 2019/9/22.
 */
@RestController
public class OrderInfoController {


    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping("/orderApplicationInfo")
    public String getOrderInfo(){
        return "this is " + applicationName;
    }

    @RequestMapping("/")
    public String home(){
        return "service is success";
    }
}
