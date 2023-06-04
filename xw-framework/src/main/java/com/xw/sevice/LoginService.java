package com.xw.sevice;

import com.xw.domain.ResponseResult;
import com.xw.domain.entity.User;

/**
 * @Author 小吴
 * @Date 2023/06/03 9:22
 * @Version 1.0
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
