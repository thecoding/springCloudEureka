package springcloud.authentication.server.service;

import springcloud.authentication.server.domain.TbPermission;

import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:18:00
 */
public interface TbPermissionService {

    default List<TbPermission> selectByUserId(Long userId){
        return null;
    }

    TbPermission getById(Long id);

    String selectStr();
}
