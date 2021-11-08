package com.mcs.meinian.mapper;

import com.mcs.meinian.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Alex
 * @Date 2021/11/7 16:29
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(@Param("username") String username) throws Exception;
}
