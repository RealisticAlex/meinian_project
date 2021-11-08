package com.mcs.meinian.service;

import com.mcs.meinian.pojo.User;

/**
 * @Author Alex
 * @Date 2021/11/7 16:28
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username) throws Exception;
}
