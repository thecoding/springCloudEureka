package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by MengMeng on 2019/10/5.
 */
@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GateWayApplication.class, args);
        System.out.println("-----gateway start-----");
    }
}
