package com.xw.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.constants.SystemConstants;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.User;
import com.xw.mapper.MenuMapper;
import com.xw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author 小吴
 * @Date 2023/05/30 15:17
 * @Version 1.0
 * 实现UserDetailsService接口，使登陆接口去查询数据库
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //判断是否查询到用户，如果没有查询到，直接抛异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        //TODO 查询权限信息封装
        //如果是后台用户，才需要查询权限的封装
        if(user.getType().equals(SystemConstants.ADMIN)){
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,list);
        }
        return new LoginUser(user,null);
    }
}
