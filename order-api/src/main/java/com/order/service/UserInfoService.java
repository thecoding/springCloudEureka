package com.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月11日 01:20:00
 */
@FeignClient(value="UserClient")
public interface UserInfoService {

    @RequestMapping(value="getUserInfo2")
    String getUserInfo(@RequestParam("id") int id);
}
