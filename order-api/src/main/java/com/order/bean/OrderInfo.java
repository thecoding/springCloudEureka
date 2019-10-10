package com.order.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月11日 01:28:00
 */
@Data
public class OrderInfo implements Serializable {

    private long orderId;
    private Integer createUser;
    private String createMan;

}
