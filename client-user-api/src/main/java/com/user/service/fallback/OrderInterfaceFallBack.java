package com.user.service.fallback;

import com.user.service.OrderInterface;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by MengMeng on 2019/9/22.
 */
@Component
public class OrderInterfaceFallBack implements OrderInterface {

    @Override
    public String getOrderInfo() {
        return "error !!! order interface invoke fall back!! ";
    }

    @Override
    public Map getOrderMap() {
        return null;
    }

    @Override
    public String ribbonTest(int num) {
        return "ribbonTest 调用失败";
    }
}
