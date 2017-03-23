package com.sachin.risk.manager.service.system;

import com.sachin.risk.manager.model.system.User;

/**
 * @author shicheng.zhang
 * @date 17-1-17
 * @time 下午4:14
 * @Description:
 */
public interface UserService {

    User queryByUsername(String username);
}
