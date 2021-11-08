package com.mcs.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mcs.meinian.mapper.UserMapper;
import com.mcs.meinian.pojo.User;
import com.mcs.meinian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alex
 * @Date 2021/11/7 16:28
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) throws Exception {

        return userMapper.getUserByUsername(username);
    }
}
