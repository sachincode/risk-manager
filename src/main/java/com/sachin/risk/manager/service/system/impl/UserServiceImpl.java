package com.sachin.risk.manager.service.system.impl;

import javax.annotation.Resource;

import com.sachin.risk.manager.dao.system.UserMapper;
import com.sachin.risk.manager.model.system.User;
import com.sachin.risk.manager.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

/**
 * @author shicheng.zhang
 * @date 17-1-17
 * @time 下午4:14
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryByUsername(String username) {
        Preconditions.checkArgument(StringUtils.isNotBlank(username));
        return userMapper.queryByUsername(username);
    }
}
