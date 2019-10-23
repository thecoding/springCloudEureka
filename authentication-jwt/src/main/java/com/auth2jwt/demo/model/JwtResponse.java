package com.auth2jwt.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月23日 01:34:00
 */
@Data
public class JwtResponse implements Serializable {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
