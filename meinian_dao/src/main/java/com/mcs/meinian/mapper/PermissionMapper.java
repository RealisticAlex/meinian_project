package com.mcs.meinian.mapper;

import com.mcs.meinian.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Author Alex
 * @Date 2021/11/7 16:31
 */
public interface PermissionMapper {

    /**
     * 根据角色id查询角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    Set<Permission> getPermissionsById(@Param("roleId") Integer roleId) throws Exception;
}
