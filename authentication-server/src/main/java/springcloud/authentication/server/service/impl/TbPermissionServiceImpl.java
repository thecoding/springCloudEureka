package springcloud.authentication.server.service.impl;

import org.springframework.stereotype.Service;
import springcloud.authentication.server.domain.TbPermission;
import springcloud.authentication.server.mapper.TbPermissionMapper;
import springcloud.authentication.server.service.TbPermissionService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:18:00
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Resource
    private TbPermissionMapper tbPermissionMapper;


    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }


    @Override
    public TbPermission getById(Long id) {
        Example example = new Example(TbPermission.class);
        example.createCriteria().andEqualTo("id", id);
        return tbPermissionMapper.selectOneByExample(example);
    }

    @Override
    public String selectStr() {
        return tbPermissionMapper.selectStr();
    }
}
