package com.xw.sevice;

import com.xw.domain.ResponseResult;
import com.xw.domain.entity.User;

/**
 * @Author 小吴
 * @Date 2023/05/30 15:02
 * @Version 1.0
 */
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
