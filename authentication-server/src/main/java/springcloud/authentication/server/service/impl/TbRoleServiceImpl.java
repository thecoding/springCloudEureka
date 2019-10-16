package springcloud.authentication.server.service.impl;

import org.springframework.stereotype.Service;
import springcloud.authentication.server.mapper.TbRoleMapper;
import springcloud.authentication.server.service.TbRoleService;

import javax.annotation.Resource;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:17:00
 */
@Service
public class TbRoleServiceImpl implements TbRoleService {

    @Resource
    TbRoleMapper tbRoleMapper;
}
