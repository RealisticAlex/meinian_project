package com.mcs.meinian.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mcs.meinian.pojo.Permission;
import com.mcs.meinian.pojo.Role;
import com.mcs.meinian.pojo.User;
import com.mcs.meinian.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Alex
 * @Date 2021/11/7 16:25
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            //根据username查询用户信息
            User user = userService.getUserByUsername(username);
            //判断user是否为null
            if (user == null) {
                //根据用户名未查询到用户信息
                return null;
            }
            //表示根据用户名查询到了用户信息,然后授权
            Set<GrantedAuthority> set = new HashSet<>();
            //获取用户拥有的角色
            Set<Role> roles = user.getRoles();
            //对roles进行循环,获取每个角色所对应的权限
            for (Role role : roles) {
                //将用户所对应的角色授权
                //set.add(new SimpleGrantedAuthority(role.getKeyword()));
                //获取每个角色的权限
                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    set.add(new SimpleGrantedAuthority(permission.getKeyword()));
                }
            }
            return new org.springframework.security.core.userdetails.User(username,user.getPassword(),set);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
