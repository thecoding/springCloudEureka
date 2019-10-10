package com.user.service.impl;

import com.user.bean.UserInfo;
import com.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author Mirko
 * @Description TODO
 * @createTime 2019年10月11日 00:30:00
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("jack");
        userInfo.setAge(19);
        userInfo.setHabit("football and swim");
        return userInfo;
    }
}
