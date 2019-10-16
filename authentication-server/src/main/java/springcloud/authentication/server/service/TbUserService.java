package springcloud.authentication.server.service;

import springcloud.authentication.server.domain.TbUser;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:17:00
 */
public interface TbUserService {

    default TbUser getByUsername(String username) {
        return null;
    }
}
