package com.xw.sevice.impl;

import com.xw.domain.ResponseResult;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.User;
import com.xw.sevice.LoginService;
import com.xw.utils.JwtUtil;
import com.xw.utils.RedisCache;
import com.xw.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author 小吴
 * @Date 2023/06/03 9:23
 * @Version 1.0
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();   //获取认证主体
        //把用户信息存入redis
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);   //token
        //把token和userinfo封装 返回   这里也可以使用map封装返回数据
        redisCache.setCacheObject("login:"+userId,loginUser);

        //管理端只需要返回token就可以了
        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前登录的用户id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
}
