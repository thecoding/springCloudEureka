package com.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengMeng on 2019/9/22.
 */
@RestController
public class OrderInfoController {


    @Value("${spring.application.name}")
    String applicationName;

    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/orderApplicationInfo")
    public String getOrderInfo(){
        return "this is " + applicationName+" and port is " + serverPort;
    }

    @RequestMapping("/")
    public String home(){
        return "service is success";
    }


    @RequestMapping("/getOrderMap")
    public Map getOrderMap(){
        return new HashMap<>();
    }


    @RequestMapping("/ribbonTest")
    public String ribbonTest(@RequestParam int num){
        return "第" + num + "次调用，该服务器端口是：" + serverPort;
    }
}
