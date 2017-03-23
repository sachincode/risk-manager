package com.sachin.risk.manager.dao.system;

import com.sachin.risk.manager.model.system.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author shicheng.zhang
 * @date 17-1-17
 * @time 下午4:04
 * @Description:
 */
public interface UserMapper {

    public User queryByUsername(@Param("username") String username);
}
