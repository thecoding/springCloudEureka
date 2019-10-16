package springcloud.authentication.server.mapper;


import org.apache.ibatis.annotations.Param;
import springcloud.authentication.server.domain.TbPermission;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {

    List<TbPermission> selectByUserId(@Param("userId") Long userId);
}