package springcloud.authentication.server.service;

import springcloud.authentication.server.domain.TbPermission;

import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月16日 14:18:00
 */
public interface TbPermissionService {

    List<TbPermission> selectByUserId(Long userId);

    TbPermission getById(Long id);
}
