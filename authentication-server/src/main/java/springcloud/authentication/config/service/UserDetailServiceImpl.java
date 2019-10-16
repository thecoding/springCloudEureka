package springcloud.authentication.config.service;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springcloud.authentication.server.domain.TbPermission;
import springcloud.authentication.server.domain.TbUser;
import springcloud.authentication.server.service.TbPermissionService;
import springcloud.authentication.server.service.TbUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月15日 15:37:00
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    TbUserService tbUserService;

    @Autowired
    TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TbPermission tbPermissions2 = tbPermissionService.getById(37L);

        // 查询用户信息
        TbUser tbUser = tbUserService.getByUsername(userName);
        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        if (tbUser != null) {
            // 查询用户权限信息
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());

            tbPermissions.forEach(tbPermission -> {
                if (tbPermission != null && tbPermission.getEnname() != null) {
                    grantedAuthorityList.add(new SimpleGrantedAuthority(tbPermission.getEnname()));
                }

            });
        }
        return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorityList);
    }

}
