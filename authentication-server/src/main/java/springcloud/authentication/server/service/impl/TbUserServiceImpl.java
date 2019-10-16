package springcloud.authentication.server.service.impl;

import org.springframework.stereotype.Service;
import springcloud.authentication.server.domain.TbUser;
import springcloud.authentication.server.mapper.TbUserMapper;
import springcloud.authentication.server.service.TbUserService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:17:00
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        return tbUserMapper.selectOneByExample(example);
    }
}
