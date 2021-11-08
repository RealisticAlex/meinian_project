package com.mcs.meinian.mapper;

import com.mcs.meinian.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Author Alex
 * @Date 2021/11/7 16:30
 */
public interface RoleMapper {

    /**
     * 根据用户id查询角色信息
     * @param id
     * @return
     * @throws Exception
     */
    Set<Role> getRolesByUserId(@Param("id") Integer id) throws Exception;
}
