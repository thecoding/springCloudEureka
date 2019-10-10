package com.user.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月11日 00:24:00
 */
@Data
public class UserInfo implements Serializable {

    private int id;
    private String name;
    private Integer age;
    private String habit;
}
