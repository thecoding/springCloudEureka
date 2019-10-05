package com.user.service;

import com.user.service.fallback.OrderInterfaceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by MengMeng on 2019/9/22.
 *
 * 这里是调用
 */
@FeignClient(name="OrderServer",fallback=OrderInterfaceFallBack.class)
public interface OrderInterface {


    @RequestMapping(value= "/orderApplicationInfo")
    String getOrderInfo();

    @RequestMapping(value = "/getOrderMap")
    Map getOrderMap();

    @RequestMapping(value="/ribbonTest")
    String ribbonTest(@RequestParam("num") int num);
}
