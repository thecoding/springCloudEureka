package com.auth2jwt.demo.config.service;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月23日 01:33:00
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 这里现在固定写死
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(username) && username.equals("username")) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            List list = new ArrayList();
            list.add(grantedAuthority);
            return new User("username",passwordEncoder.encode("123456"), list);
        }
        return null;
    }
}
