package com.auth2jwt.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月23日 01:32:00
 */
@Data
public class JwtRequest implements Serializable {

    private String username;
    private String password;
}
