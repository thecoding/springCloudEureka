package com.user.service;

import com.user.service.fallback.OrderInterfaceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MengMeng on 2019/9/22.
 *
 * 这里是调用
 */
@FeignClient(name="OrderServer",fallback=OrderInterfaceFallBack.class)
public interface OrderInterface {


    @RequestMapping(value= "/orderApplicationInfo")
    String getOrderInfo();
}
